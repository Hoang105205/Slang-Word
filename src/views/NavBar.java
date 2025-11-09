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
        setPreferredSize(new Dimension(200, 0));

        JButton btnHome = new JButton("Search for a Slang");
        btnHome.addActionListener(e -> {
            frame.setContent(new SearchBySlangWordPanel(controller));
        });

        JButton btnSearchByDefinition = new JButton("Search By Definition");
        btnSearchByDefinition.addActionListener(e -> {
            frame.setContent(new SearchByKeywordPanel(controller));
        });

        JButton btnViewHistory = new JButton("View History");
        btnViewHistory.addActionListener(e -> {
            frame.setContent(new ViewHistory(controller));
        });

        JButton btnAddSlang= new JButton("Add Slang");
        btnAddSlang.addActionListener(e -> {
           frame.setContent(new AddSlangPanel(controller));
        });

        JButton btnEditSlang= new JButton("Edit Slang");
        JButton btnDeleteSlang= new JButton("Delete Slang");
        JButton btnRefetchRootData= new JButton("Refetch Root Data");
        JButton btnRandomSlang= new JButton("On this day slang word");
        JButton btnQuiz = new JButton("Play a quiz!");


        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(e -> {
            controller.saveData();
            System.exit(0);
        });

        add(btnHome);
        add(btnSearchByDefinition);
        add(btnViewHistory);
        add(btnAddSlang);
        add(btnEditSlang);
        add(btnDeleteSlang);
        add(btnRefetchRootData);
        add(btnRandomSlang);
        add(btnQuiz);
        add(btnExit);
    }
}
