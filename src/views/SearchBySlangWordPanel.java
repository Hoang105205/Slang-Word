package views;

import controllers.AppController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Hoang
 * @date 11/5/2025
 */
public class SearchBySlangWordPanel extends JPanel {
    public SearchBySlangWordPanel(AppController controller) {
        setLayout(new BorderLayout());

        // Page_start
        JPanel searchPanel = new JPanel();

        JTextField searchText = new JTextField(20);
        searchText.setFont(new Font("Arial", Font.PLAIN, 20));
        JButton btnSearch = new JButton("Search");
        searchText.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel searchLabel = new JLabel("Enter your slang word: ");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 20));

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

            String slang = searchText.getText().trim();
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
        });

        JScrollPane resultScroll = new JScrollPane(resultArea);
        resultScroll.getVerticalScrollBar().setUnitIncrement(40);


        add(searchPanel, BorderLayout.PAGE_START);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(resultScroll);

        add(centerPanel, BorderLayout.CENTER);
    }
}
