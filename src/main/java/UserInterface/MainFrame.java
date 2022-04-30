package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{
    private CardLayout cardLayout = new CardLayout();
    private JPanel content;
    private CalendarPanel calendarPanel;
    private DatePanel datePanel;

    public MainFrame() {
        this.setSize(new Dimension(1200, 800));
        this.setResizable(false);
        this.setTitle("Day Planner");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        calendarPanel = new CalendarPanel();
        datePanel = new DatePanel();

        content = new JPanel();
        content.setPreferredSize(new Dimension(700, 600));
        content.setLayout(cardLayout);

        content.add(calendarPanel, "1");
        content.add(datePanel, "2");

        cardLayout.show(content, "1");
        calendarPanel.updateList();

        this.add(content);
        this.setVisible(true);
    }

    public void changePanel(String target) {
        cardLayout.show(content, target);
    }

    //FIXME: give the method correct param
    public void showDate() {
        datePanel.setDate();
        this.changePanel("2");
    }

    public void colorTheme() {

    }

}
