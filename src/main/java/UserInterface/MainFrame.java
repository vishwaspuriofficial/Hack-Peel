package UserInterface;

import Execution.Main;
import Scripts.DateStorage;
import Scripts.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MainFrame extends JFrame{
    private CardLayout cardLayout = new CardLayout();
    private JPanel content;
    private CalendarPanel calendarPanel;
    private DatePanel datePanel;

    public MainFrame() throws ParseException, CloneNotSupportedException {
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
        test(); //TEST CASE
    }

    public void changePanel(String target) {
        cardLayout.show(content, target);
    }

    //FIXME: give the method correct param
    public void showDate(LinkedList<Event> plans) {
        datePanel.setDate(plans);
        this.changePanel("2");
    }

    public void colorTheme() {

    }

    //Test Case
//    public void test() throws ParseException {
//        System.out.println(Main.getPlannedDatesData());
//        LinkedList<Event> event= new LinkedList<Event>();
//        Event test = new Event("Gyy","30/05/2022","10:00am","10:45am",new ArrayList<>(Arrays.asList("1","2")),new ArrayList<>(Arrays.asList("Shower")),4);
//        event.add(test);
//
//        DateStorage.addEventToDate(event);
//        System.out.println(Main.getPlannedDatesData());
//        DateStorage.deleteEventAtDate("30/05/2022",event);
//        System.out.println(Main.getPlannedDatesData());
//    }

    public void test() throws ParseException, CloneNotSupportedException {
        LinkedList<Event> event= new LinkedList<Event>();
        Event _test = new Event("EyLmao","30/05/2022","00:00",":30",new ArrayList<>(Arrays.asList("1","2")),new ArrayList<>(Arrays.asList("Shower")),4);
        Event testToAdd = new Event("GYY","30/05/2022","10:00","10:30",new ArrayList<>(Arrays.asList("1","2")),new ArrayList<>(Arrays.asList("Shower")),4);
        event.add(_test);
        DateStorage.addEventToDate(event);
        LinkedList<Event>[] solutions = DateStorage.getSuggestions("30/05/2022",testToAdd);
        System.out.println("I made it bruv");
        for (Event e : solutions[0]) {
           System.out.println(e.getTitle()+" "+e.getStartTime()+" "+e.getEndTime());
        }

    }


}
