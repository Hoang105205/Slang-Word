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
public class SearchByKeywordPanel extends JPanel {
    private final JTextArea resultArea = new JTextArea();
    private final JPanel centerPanel = new JPanel();
    private final JTextField searchTextField = Helper.createTextField();

    public SearchByKeywordPanel(AppController controller) {
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

        JLabel searchLabel = Helper.createLabel("Enter the definition: ", 20);

        JButton btnSearch = new JButton("Search");

        btnSearch.addActionListener(e -> handleSearch(controller));

        searchPanel.add(searchLabel);
        searchPanel.add(searchTextField);
        searchPanel.add(btnSearch);

        return searchPanel;
    }

    private void buildCenterPanel(){
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

        String keyword = searchTextField.getText().trim();
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter the definition",
                    "Input required",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<String> slangs = controller.searchByDefinition(keyword);
        if (slangs == null || slangs.isEmpty()) {
            resultArea.setText("No result found for: " + keyword);
        } else {
            StringBuilder sb = new StringBuilder("  Some slang words have \"" + keyword + "\" in their definition:\n\n");
            for (String slang : slangs) {
                sb.append("    • ").append(slang).append("\n");
            }
            resultArea.setText(sb.toString());
        }

        resultArea.setCaretPosition(0);
    };


}
