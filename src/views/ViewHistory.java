package views;

import controllers.AppController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Hoang
 * @date 11/5/2025
 */
public class ViewHistory extends JPanel {
    public ViewHistory(AppController controller) {
        setLayout(new BorderLayout());

        // Page_start
        JPanel topPanel = new JPanel();


        JLabel topLabel = new JLabel("Your Searching History");
        topLabel.setFont(new Font("Arial", Font.BOLD, 25));

        topPanel.add(topLabel);

        // Page_center
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 20));

        List<String> searchedSlangs = controller.getSearchHistory();
        if  (searchedSlangs != null) {
            StringBuilder sb = new StringBuilder("  Your Search History:\n\n");

            for (String searchedSlang : searchedSlangs) {
                sb.append("    • ").append(searchedSlang).append("\n");
            }

            resultArea.setText(sb.toString());
            resultArea.setCaretPosition(0);

            JScrollPane resultScroll = new JScrollPane(resultArea);
            resultScroll.getVerticalScrollBar().setUnitIncrement(40);

            centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            centerPanel.add(resultScroll);
        }
        else{
            resultArea.setText("  No history found.");
            resultArea.setCaretPosition(0);

            centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            centerPanel.add(resultArea);
        }

        add(topPanel, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);
    }
}
