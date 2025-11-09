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
public class SearchBySlangWordPanel extends JPanel {
    private final JTextArea resultArea = new JTextArea();
    private final JPanel centerPanel = new JPanel();
    private final JTextField searchTextField = Helper.createTextField();

    public SearchBySlangWordPanel(AppController controller) {
        setLayout(new BorderLayout());

        // Page_start
        JPanel searchPanel = buildTopPanel(controller);

        // Page_center
        buildCenterPanel();

        add(searchPanel, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);
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
        }

        resultArea.setCaretPosition(0);
    }
}
