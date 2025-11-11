package views;

import controllers.AppController;

import javax.swing.*;
import java.awt.*;

/**
 * @author Hoang
 * @date 11/10/2025
 */
public class PlayQuizPanel extends JPanel {
    public PlayQuizPanel(AppController controller, MainFrame mainFrame) {
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Find the correct");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnByDefinitions = new JButton("Definition for a slang");
        JButton btnBySlang = new JButton("Slang with the given definitions");

        btnByDefinitions.setFont(new Font("Arial", Font.BOLD, 25));
        btnBySlang.setFont(new Font("Arial", Font.BOLD, 25));

        btnByDefinitions.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        btnByDefinitions.addActionListener(e -> {
            mainFrame.setContent(new ChooseDefinitionPanel(controller, mainFrame));
        });

        btnBySlang.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        btnBySlang.addActionListener(e -> {
            mainFrame.setContent(new ChooseSlangPanel(controller, mainFrame));
        });

        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(btnByDefinitions);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(btnBySlang);

        add(centerPanel, BorderLayout.CENTER);
    }
}
