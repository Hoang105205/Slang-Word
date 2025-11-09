package views;

import controllers.AppController;
import libs.Helper;

import javax.swing.*;
import java.awt.*;

/**
 * @author Hoang
 * @date 11/5/2025
 */
public class RefetchRootDataPanel extends JPanel {
    public RefetchRootDataPanel(AppController controller) {
        setLayout(new BorderLayout());

        // Page_start
        JPanel topPanel = new JPanel();

        JButton btnRefetch = Helper.createButton("Refetch");

        btnRefetch.setFont(new Font("Arial", Font.BOLD, 25));


        btnRefetch.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to refetch the original data?\n" +
                            "All history will be deleted!",
                    "Confirm Refetch",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (choice == JOptionPane.YES_OPTION) {
                controller.resetToRootData();
                JOptionPane.showMessageDialog(this, "Dictionary has been reset to original data!");
            }
        });


        topPanel.add(btnRefetch);

        add(topPanel, BorderLayout.PAGE_START);

    }
}
