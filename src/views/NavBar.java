package views;

import controllers.AppController;

import javax.swing.*;
import java.awt.*;

/**
 * @author Hoang
 * @date 11/5/2025
 */
public class NavBar extends JPanel {
    public NavBar(MainFrame frame, AppController controller) {
        setLayout(new GridLayout(0, 1, 5, 5));
        setPreferredSize(new Dimension(150, 0));

        JButton btnHome = new JButton("Home");
        btnHome.addActionListener(e -> {
            frame.setContent(new SearchBySlangWordPanel(controller));
        });

        JButton btnSearchByDefinition = new JButton("Search By Definition");
        btnSearchByDefinition.addActionListener(e -> {
            frame.setContent(new SearchByKeywordPanel(controller));
        });

        JButton btnViewHistory = new JButton("View History");

        JButton btnRefetchRootData= new JButton("Refetch Root Data");

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(e -> {
            System.exit(0);
        });

        add(btnHome);
        add(btnSearchByDefinition);
        add(btnViewHistory);
        add(btnRefetchRootData);
        add(btnExit);
    }
}
