package libs;

import javax.swing.*;
import java.awt.*;

/**
 * @author Hoang
 * @date 11/9/2025
 */
public class Helper {
    public static JTextField createTextField() {
        JTextField field = new JTextField(20);
        field.setFont(new Font("Arial", Font.PLAIN, 18));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        return field;
    }

    public static JTextField createTextField(String text) {
        JTextField field = createTextField();
        field.setText(text);
        return field;
    }

    public static JLabel createLabel(String text, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, size));
        return label;
    }

    public static JButton createButton(String text) {
        return new JButton(text);
    }
}
