package views;

import controllers.AppController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Hoang
 * @date 11/5/2025
 */
public class SearchByKeywordPanel extends JPanel {
    public SearchByKeywordPanel(AppController controller) {
        setLayout(new BorderLayout());

        // Page_start
        JPanel searchPanel = new JPanel();

        JLabel searchLabel = new JLabel("Enter the definition: ");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField searchText = new JTextField(20);
        searchText.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton btnSearch = new JButton("Search");


        searchPanel.add(searchLabel);
        searchPanel.add(searchText);
        searchPanel.add(btnSearch);


        // Page_center
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 20));


        btnSearch.addActionListener(e -> {
            resultArea.setText("");

            String keyword = searchText.getText().trim();
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
        });
        JScrollPane resultScroll = new JScrollPane(resultArea);
        resultScroll.getVerticalScrollBar().setUnitIncrement(40);

        add(searchPanel, BorderLayout.PAGE_START);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(resultScroll);

        add(centerPanel, BorderLayout.CENTER);
    }
}
