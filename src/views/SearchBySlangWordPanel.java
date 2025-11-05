package views;

import controllers.AppController;

import javax.swing.*;
import java.awt.*;

/**
 * @author Hoang
 * @date 11/5/2025
 */
public class SearchBySlangWordPanel extends JPanel {
    public SearchBySlangWordPanel(AppController controller) {
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();


        JTextField searchText = new JTextField(20);
        JButton btnSearch = new JButton("Search");
        searchPanel.add(new JLabel("Enter your slang word: "));
        searchPanel.add(searchText);
        searchPanel.add(btnSearch);

        JPanel contentPanel = new JPanel();




        add(searchPanel, BorderLayout.PAGE_START);
        add(contentPanel, BorderLayout.CENTER);
    }
}
