package views;

import controllers.AppController;

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

    public AddSlangPanel(AppController controller) {
        setLayout(new BorderLayout());

        // Page_start
        JPanel topPanel = new JPanel();

        JLabel topLabel = new JLabel("Enter a slang:");
        topLabel.setFont(new Font("Arial", Font.BOLD, 25));
        JTextField slangTextField = new JTextField(10);
        slangTextField.setFont(new Font("Arial", Font.PLAIN, 20));

        topPanel.add(topLabel);
        topPanel.add(slangTextField);

        // Page_center
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JTextField firstMeaning = createMeaningField();
        meaningFields.add(firstMeaning);
        centerPanel.add(firstMeaning);

        JButton btnAddMeaning = new JButton("+ Add another meaning");
        btnAddMeaning.setFont(new Font("Arial", Font.BOLD, 15));
        btnAddMeaning.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnAddMeaning.addActionListener(e -> {
           JTextField newField =  createMeaningField();
           meaningFields.add(newField);

           int index = centerPanel.getComponentCount() - 1;

           centerPanel.add(Box.createVerticalStrut(5), index);
           centerPanel.add(newField, index);
           centerPanel.revalidate();
           centerPanel.repaint();
        });

        centerPanel.add(Box.createVerticalStrut(5));
        centerPanel.add(btnAddMeaning);

        // Bottom panel

        JPanel bottomPanel = new JPanel();
        JButton btnAddButton = new JButton("Add Slang");
        btnAddButton.setFont(new Font("Arial", Font.BOLD, 15));
        bottomPanel.add(btnAddButton);

        btnAddButton.addActionListener(e -> {
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

        });



        add(topPanel, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);
    }

    public JTextField createMeaningField() {
        JTextField field = new JTextField(20);
        field.setFont(new Font("Arial", Font.PLAIN, 18));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        return field;
    }
}
