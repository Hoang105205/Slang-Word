package views;

import controllers.AppController;
import libs.Helper;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Hoang
 * @date 11/5/2025
 */
public class DeleteSlangPanel extends JPanel {
    private final JTextArea resultArea = new JTextArea();
    private final JPanel centerPanel = new JPanel();
    private final JTextField searchTextField = Helper.createTextField();
    private final JButton btnDelete = Helper.createButton("Delete");

    public DeleteSlangPanel(AppController controller) {
        setLayout(new BorderLayout());

        // Page_start
        JPanel searchPanel = buildTopPanel(controller);

        // Page_center
        buildCenterPanel();

        // Page_end
        JPanel bottomPanel = buildBottomPanel(controller);

        add(searchPanel, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);
    }

    private JPanel buildTopPanel(AppController controller) {
        JPanel searchPanel = new JPanel();

        JLabel searchLabel = Helper.createLabel("Enter your slang word: ", 20);

        JButton btnSearch = new JButton("Search");

        btnSearch.addActionListener(e -> handleSearch(controller));

        searchPanel.add(searchLabel);
        searchPanel.add(searchTextField);
        searchPanel.add(btnSearch);

        return searchPanel;
    }

    private void buildCenterPanel() {
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 20));

        JScrollPane resultScroll = new JScrollPane(resultArea);
        resultScroll.getVerticalScrollBar().setUnitIncrement(40);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(resultScroll);
    }

    private JPanel buildBottomPanel(AppController controller) {
        JPanel bottomPanel = new JPanel();

        btnDelete.setFont(new Font("Arial", Font.BOLD, 20));
        btnDelete.setEnabled(false);

        btnDelete.addActionListener(e -> handleDelete(controller));

        bottomPanel.add(btnDelete);


        return bottomPanel;
    }

    private void handleSearch(AppController controller) {
        resultArea.setText("");

        String slang = searchTextField.getText().trim();
        if (slang.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a slang word",
                    "Input required",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<String> meanings = controller.searchBySlang(slang);
        if (meanings == null || meanings.isEmpty()) {
            resultArea.setText("No result found for: " + slang);
        } else {
            StringBuilder sb = new StringBuilder("  Meanings for \"" + slang + "\":\n\n");

            for (String meaning : meanings) {
                sb.append("    • ").append(meaning).append("\n");
            }

            resultArea.setText(sb.toString());
            btnDelete.setEnabled(true);
        }

        resultArea.setCaretPosition(0);
    }

    private void handleDelete(AppController controller) {
        String slang = searchTextField.getText().trim();
        if (slang.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter the slang word",
                    "Input required",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this slang?\n" +
                        "This is a permanent action!",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (choice == JOptionPane.YES_OPTION) {
            controller.deleteSlang(slang);
            searchTextField.setText("");
            resultArea.setText("");
            btnDelete.setEnabled(false);

            centerPanel.revalidate();
            centerPanel.repaint();
            JOptionPane.showMessageDialog(this, "Slang has been successfully deleted!", "Delete Success", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
