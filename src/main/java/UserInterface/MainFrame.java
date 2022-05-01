package UserInterface;

import Scripts.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class MainFrame extends JFrame{
    private CardLayout cardLayout = new CardLayout();
    private JPanel content;
    private CalendarPanel calendarPanel;
    private DatePanel datePanel;

    public MainFrame() {
        this.setSize(new Dimension(1200, 800));
        this.setResizable(false);
        this.setTitle("Optistrads");
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

    public void showDate(String date, LinkedList<Event> plans, boolean isSetSolution) {
        datePanel.setDate(date, plans, isSetSolution);
        this.changePanel("2");
    }

    public void colorTheme() {

    }

    public DatePanel getDatePanel() {
        return datePanel;
    }
}
