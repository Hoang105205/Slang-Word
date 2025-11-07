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

        JTextField searchText = new JTextField(20);
        searchText.setFont(new Font("Arial", Font.PLAIN, 20));
        JButton btnSearch = new JButton("Search");
        searchText.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel searchLabel = new JLabel("Enter the definition: ");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        searchPanel.add(searchLabel);
        searchPanel.add(searchText);
        searchPanel.add(btnSearch);


        // Page_center
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        resultPanel.setOpaque(false);

        btnSearch.addActionListener(e -> {
            resultPanel.removeAll();

            String slang = searchText.getText().trim();
            if (slang.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter the definition",
                        "Input required",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            List<String> meanings = controller.searchBySlang(slang);
            if (meanings == null || meanings.isEmpty()) {
                JLabel lbl = new JLabel("No result found for: " + slang);
                lbl.setFont(new Font("Arial", Font.PLAIN, 20));
                resultPanel.add(lbl);
            } else {
                JLabel lblTitle = new JLabel("Meanings for \"" + slang + "\":");
                lblTitle.setFont(new Font("Arial", Font.BOLD, 25));
                resultPanel.add(lblTitle);

                for (String meaning : meanings) {
                    JLabel element = new JLabel("• " + meaning);
                    element.setFont(new Font("Arial", Font.PLAIN, 20));
                    element.setBorder(BorderFactory.createEmptyBorder(2, 15, 2, 0));
                    resultPanel.add(element);
                }
            }

            resultPanel.revalidate();
            resultPanel.repaint();
        });


        add(searchPanel, BorderLayout.PAGE_START);

        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        centerPanel.add(separator);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(resultPanel);
        add(centerPanel, BorderLayout.CENTER);
    }
}
