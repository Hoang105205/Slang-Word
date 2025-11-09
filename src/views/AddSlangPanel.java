package views;

import controllers.AppController;
import libs.Helper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hoang
 * @date 11/5/2025
 */
public class AddSlangPanel extends JPanel {
    private final List<JTextField> meaningFields = new ArrayList<>();
    private final JPanel centerPanel = new JPanel();
    private final JButton btnAddMeaning = Helper.createButton("+ Add another meaning");
    private final JTextField slangTextField  = Helper.createTextField();
    private final JButton btnAddButton = Helper.createButton("Add Slang");

    public AddSlangPanel(AppController controller) {
        setLayout(new BorderLayout());

        // Page_start
        JPanel topPanel = buildTopPanel();

        // Page_center
        buildCenterPanel();

        // Bottom panel
        JPanel bottomPanel = buildBottomPanel(controller);


        add(topPanel, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);
    }

    private JPanel buildTopPanel() {
        JPanel topPanel = new JPanel();

        JLabel topLabel = Helper.createLabel("Enter a slang:", 20);

        topPanel.add(topLabel);
        topPanel.add(slangTextField);

        return topPanel;
    }

    private void buildCenterPanel() {
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JTextField firstMeaning = Helper.createTextField();
        meaningFields.add(firstMeaning);
        centerPanel.add(firstMeaning);

        btnAddMeaning.setFont(new Font("Arial", Font.BOLD, 15));
        btnAddMeaning.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnAddMeaning.addActionListener(e -> handleAddMeaning());

        centerPanel.add(Box.createVerticalStrut(5));
        centerPanel.add(btnAddMeaning);
    }

    private JPanel buildBottomPanel(AppController controller) {
        JPanel bottomPanel = new JPanel();

        btnAddButton.setFont(new Font("Arial", Font.BOLD, 15));
        bottomPanel.add(btnAddButton);

        btnAddButton.addActionListener(e -> handleAddSlang(controller));

        return bottomPanel;
    }

    private void handleAddMeaning() {
        JTextField newField = Helper.createTextField();
        meaningFields.add(newField);

        int index = centerPanel.getComponentCount() - 1;

        centerPanel.add(Box.createVerticalStrut(5), index);
        centerPanel.add(newField, index);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    private void handleAddSlang(AppController controller) {
        String slang = slangTextField.getText().trim();

        if (slang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a slang word!");
            return;
        }

        List<String> meanings = new ArrayList<>();

        for (JTextField meaningField : meaningFields) {
            String text = meaningField.getText().trim();
            if (text.isEmpty()) {
                continue;
            }
            meanings.add(text);
        }

        if (meanings.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter at least one definition!");
            return;
        }

        if (controller.isSlangExist(slang)) {
            int choice = JOptionPane.showOptionDialog(
                    this,
                    "The slang \"" + slang + "\" already exists.\n" +
                            "Do you want to overwrite it or add as a duplicate ?",
                    "Duplicate Slang Detected",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new String[]{"Overwrite", "Duplicate", "Cancel"},
                    "Cancel"
            );

            if (choice == 0){
                controller.addSlang(slang, meanings);
                JOptionPane.showMessageDialog(this, "Slang has been overwritten!");
            }
            else if (choice == 1){
                controller.addDefinitions(slang, meanings);
                JOptionPane.showMessageDialog(this, "Definitions has been added!");
            }
        }
        else{
            controller.addSlang(slang, meanings);
            JOptionPane.showMessageDialog(this, "Slang has been added!");
        }
    }


}
