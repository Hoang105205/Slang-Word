package views;

import controllers.AppController;

import javax.swing.*;
import java.awt.*;

/**
 * @author Hoang
 * @date 11/5/2025
 */
public class MainFrame extends JFrame {
    private JPanel contentPanel;
    private AppController controller;

    public MainFrame(AppController controller) {
        this.controller = controller;

        setDefaultLookAndFeelDecorated(true);
        setTitle("Slang Dictionary App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(new NavBar(this, controller), BorderLayout.LINE_START);

        // Panel nội dung
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        // Load màn hình đầu tiên
        setContent(new SearchBySlangWordPanel(controller));

        setVisible(true);
    }

    // Switch panels
    public void setContent(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
