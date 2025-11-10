package views;

import controllers.AppController;
import libs.Helper;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author Hoang
 * @date 11/10/2025
 */
public class ChooseDefinitionPanel extends JPanel {
    private JButton btnBack = Helper.createButton("Back");
    private JPanel centerPanel = new JPanel();
    private Map.Entry<String, List<String>> correctSlang;
    private String correctAnswer;
    private MainFrame mainFrame;
    private AppController controller;
    private final Random random = new Random();
    private List<String> definitions;


    public ChooseDefinitionPanel(AppController controller, MainFrame mainFrame) {
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout());
        this.mainFrame = mainFrame;
        this.controller = controller;

        // generate
        correctSlang = controller.getRandomSlang();
        correctAnswer = correctSlang.getValue().get(random.nextInt(correctSlang.getValue().size()));

        Set<String> options = new HashSet<>();
        options.add(correctAnswer);

        while(options.size() < 4) {
            Map.Entry<String, List<String>> randomSlang = controller.getRandomSlang();

            if (randomSlang.getKey().equals(correctSlang.getKey())) continue;

            String randomMeaning =  randomSlang.getValue().get(random.nextInt(randomSlang.getValue().size()));

            options.add(randomMeaning);
        }

        definitions = new ArrayList<>(options);
        Collections.shuffle(definitions);



        JPanel topPanel = buildTopPanel();

        buildCenterPanel();

        add(topPanel, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);
    }

    private JPanel buildTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        btnBack.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        btnBack.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnBack.addActionListener(e -> {
            this.mainFrame.setContent(new PlayQuizPanel(controller, mainFrame));
        });

        topPanel.add(btnBack);

        return topPanel;
    }

    private void buildCenterPanel() {
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel headPanel = new JPanel();
        JLabel slangLabel = new JLabel(correctSlang.getKey());
        slangLabel.setFont(new Font("Arial", Font.BOLD, 25));
        slangLabel.setForeground(Color.BLUE);
        headPanel.add(slangLabel);
        headPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(2, 2, 10, 10));

        for (String definition : definitions) {
            JButton btnOption = Helper.createButton("<html><body style='text-align:center;'>" + definition + "</body></html>");
            btnOption.setFont(new Font("Arial", Font.BOLD, 15));
            btnOption.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
            btnOption.setPreferredSize(new Dimension(100, 20));

            btnOption.putClientProperty("value", definition);

            btnOption.addActionListener(e -> {
                String selected = (String) btnOption.getClientProperty("value");

                if (selected.equals(correctAnswer)) {
                    JOptionPane.showMessageDialog(this, "You're correct!\n",
                            "Correct Answer", JOptionPane.INFORMATION_MESSAGE);

                    mainFrame.setContent(new PlayQuizPanel(controller, mainFrame));
                }
                else {
                    JOptionPane.showMessageDialog(this, "Wrong Answer\n" +
                            "Please try again!", "Wrong Answer", JOptionPane.WARNING_MESSAGE);
                }
            });

            contentPanel.add(btnOption);
        }



        centerPanel.add(headPanel, BorderLayout.PAGE_START);
        centerPanel.add(contentPanel, BorderLayout.CENTER);
    }

}
