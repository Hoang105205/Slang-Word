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
public class EditSlangPanel extends JPanel {
    private final List<JTextField> meaningFields = new ArrayList<>();
    private final JPanel centerPanel = new JPanel();
    private final JButton btnAddMeaning = Helper.createButton("+ Add another meaning");
    private final JTextField slangSearchField = Helper.createTextField();
    private final JButton btnSave = Helper.createButton("Save");


    public EditSlangPanel(AppController controller) {
        setLayout(new BorderLayout());

        // Page_start
        JPanel topPanel = buildTopPanel(controller);

        // Page_center
        buildCenterPanel();

        // Bottom panel
        JPanel bottomPanel = buildBottomPanel(controller);
        btnSave.setEnabled(false);

        add(topPanel, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);
    }

    private JPanel buildTopPanel(AppController controller) {
        JPanel topPanel = new JPanel();

        JLabel topLabel = Helper.createLabel("Search a slang", 20);

        JButton searchButton = Helper.createButton("Search");

        searchButton.addActionListener(e -> handleSearch(controller));

        topPanel.add(topLabel);
        topPanel.add(slangSearchField);
        topPanel.add(searchButton);

        return topPanel;
    }

    private void buildCenterPanel() {
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        btnAddMeaning.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnAddMeaning.setEnabled(false);

        btnAddMeaning.addActionListener(e -> handleAddMeaning());
    }

    private JPanel buildBottomPanel(AppController controller) {
        JPanel panel = new JPanel();

        panel.add(btnSave);

        btnSave.addActionListener(e -> handleSave(controller));

        return panel;
    }

    private void handleSearch(AppController controller){
        String slang = slangSearchField.getText();

        if (slang.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a slang",
                    "Input required",
                    JOptionPane.WARNING_MESSAGE);
            slangSearchField.setText("");
            return;
        }

        List<String> meanings = controller.searchBySlang(slang);

        centerPanel.removeAll();
        meaningFields.clear();

        if (meanings == null || meanings.isEmpty()) {
            JLabel lbl = new JLabel("No result found for: " + slang);
            lbl.setFont(new Font("Arial", Font.PLAIN, 20));
            centerPanel.add(lbl);
            btnAddMeaning.setEnabled(false);
        }
        else{

            for (String meaning : meanings) {
                JTextField  meaningField = Helper.createTextField(meaning);

                centerPanel.add(Box.createVerticalStrut(5));
                centerPanel.add(meaningField);

                meaningFields.add(meaningField);
            }
            btnAddMeaning.setEnabled(true);

            btnSave.setEnabled(true);
        }

        centerPanel.add(Box.createVerticalStrut(5));
        centerPanel.add(btnAddMeaning);

        centerPanel.revalidate();
        centerPanel.repaint();
    }

    private void handleAddMeaning() {
        JTextField newField =  Helper.createTextField();
        meaningFields.add(newField);

        int index = centerPanel.getComponentCount() - 1;
        centerPanel.add(Box.createVerticalStrut(5), index);
        centerPanel.add(newField, index);

        centerPanel.revalidate();
        centerPanel.repaint();
    }

    private void handleSave(AppController controller) {
        List<String> meanings = new ArrayList<>();

        for (JTextField meaningField : meaningFields) {
            String text = meaningField.getText().trim();
            if (text.isEmpty()) {
                continue;
            }
            meanings.add(text);
        }

        if (slangSearchField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Do not know which slang to be edited");
            return;
        }

        if (meanings.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter at least one definition!");
            return;
        }

        controller.editSlang(slangSearchField.getText().trim(), meanings);
        JOptionPane.showMessageDialog(this, "Slang has been edited!");
    }


}
