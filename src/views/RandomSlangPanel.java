package views;

import controllers.AppController;
import libs.Helper;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * @author Hoang
 * @date 11/10/2025
 */
public class RandomSlangPanel extends JPanel {
    private JTextField slangField = Helper.createTextField();
    private JPanel centerPanel = new JPanel();
    private Map.Entry<String, List<String>> randomSlang;

    public RandomSlangPanel(AppController controller) {
        setLayout(new BorderLayout());

        randomSlang = controller.getRandomSlang();

        JPanel topPanel = buildTopPanel();

        buildCenterPanel();

        add(topPanel, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);
    }

    private JPanel buildTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel label = Helper.createLabel("On this day slang word: ", 25);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        slangField.setEditable(false);
        slangField.setFont(new Font("Arial", Font.BOLD, 25));
        slangField.setForeground(Color.BLUE);
        slangField.setText(randomSlang.getKey());
        slangField.setHorizontalAlignment(JTextField.CENTER);
        slangField.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(label);
        topPanel.add(slangField);

        return topPanel;
    }

    private void buildCenterPanel() {
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        JTextArea resultArea = new JTextArea();

        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 20));

        StringBuilder sb = new StringBuilder("  Meanings for \"" + randomSlang.getKey() + "\":\n\n");

        for (String meaning : randomSlang.getValue()) {
            sb.append("    • ").append(meaning).append("\n");
        }

        resultArea.setText(sb.toString());

        JScrollPane resultScroll = new JScrollPane(resultArea);
        resultScroll.getVerticalScrollBar().setUnitIncrement(40);

        resultArea.setCaretPosition(0);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(resultScroll);
    }

}
