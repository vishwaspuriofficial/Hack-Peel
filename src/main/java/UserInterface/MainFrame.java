package UserInterface;

import Execution.Main;
import Scripts.DateStorage;
import Scripts.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.lang.reflect.Executable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MainFrame extends JFrame{
    private CardLayout cardLayout = new CardLayout();
    private JPanel content;
    private CalendarPanel calendarPanel;
    private DatePanel datePanel, suggestion1, suggestion2, suggestion3;
    private LinkedList<Event>[] solutions;
    private String solutionDate;

    public MainFrame() throws ParseException, CloneNotSupportedException {
        this.setSize(new Dimension(1200, 800));
        this.setResizable(false);
        this.setTitle("Optistrads");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    //TODO: save file method here
                    Execution.Main.saveEvent();
                    Execution.Main.saveRepeatedEvent();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        this.setVisible(true);

        calendarPanel = new CalendarPanel();
        datePanel = new DatePanel();
        suggestion1 = new DatePanel();
        suggestion2 = new DatePanel();
        suggestion3 = new DatePanel();

        content = new JPanel();
        content.setPreferredSize(new Dimension(700, 600));
        content.setLayout(cardLayout);

        content.add(calendarPanel, "1");
        content.add(datePanel, "2");
        content.add(suggestion1, "3");
        content.add(suggestion2, "4");
        content.add(suggestion3, "5");

        cardLayout.show(content, "1");
        calendarPanel.updateList();

        this.add(content);
        this.setVisible(true);
//        test(); //TEST CASE
    }



    public void changePanel(String target) {
        cardLayout.show(content, target);
    }

    public void showDate(String date, LinkedList<Event> plans, boolean isSetSolution) {
        datePanel.setDate(date, plans, isSetSolution);
        this.changePanel("2");
    }

    public DatePanel getDatePanel() {
        return datePanel;
    }
    //Test Case
    public static void test() throws ParseException, CloneNotSupportedException {
        LinkedList<Event> event= new LinkedList<Event>();
        Event _test = new Event("EyLmao","30/05/2022","00:00","00:30",new ArrayList<>(Arrays.asList("1","2")),4, false);
        Event _test2 = new Event("GYY","30/05/2022","00:30","01:00",new ArrayList<>(Arrays.asList("1","2")),4,true);
        Event _test3 = new Event("ABruv","30/05/2022","01:30","02:00",new ArrayList<>(Arrays.asList("1","2")),4,true);
        Event testToAdd = new Event("lool","30/05/2022","5:00","5:30",new ArrayList<>(Arrays.asList("1","2")),10,true);

        event.add(_test);
        event.add(_test2);
        event.add(_test3);

        DateStorage.addEventToDate(event);
        LinkedList<Event>[] solutions = DateStorage.getSuggestions("30/05/2022",testToAdd);
//        System.out.println("I made it bruv");
        for (Event e : solutions[0]) {
//            System.out.println(e.getTitle()+" "+e.getStartTime()+" "+e.getEndTime());
        }
    }
//

    public void addSuggested() {
        suggestion1.setDate(solutionDate, solutions[0], false);
        suggestion1.setSuggestionNumber("1");
        suggestion1.setNextSuggestion("4");
        suggestion1.setPreviousSuggestion("5");
        for (Event event : solutions[0]) {
            System.out.println(event.getTitle());
        }
        for (Event event : solutions[1]) {
            System.out.println(event.getTitle());
        }
        for (Event event : solutions[2]) {
            System.out.println(event.getTitle());
        }

        suggestion2.setDate(solutionDate, solutions[1], false);
        suggestion2.setSuggestionNumber("2");
        suggestion2.setNextSuggestion("5");
        suggestion2.setPreviousSuggestion("3");

        suggestion3.setDate(solutionDate, solutions[2], false);
        suggestion3.setSuggestionNumber("3");
        suggestion3.setNextSuggestion("3");
        suggestion3.setPreviousSuggestion("4");

        cardLayout.show(content, "3");
    }

    public LinkedList<Event>[] getSolutions() {
        return solutions;
    }

    public void setSolutions(LinkedList<Event>[] solutions) {
        this.solutions = solutions;
    }

    public String getSolutionDate() {
        return solutionDate;
    }

    public void setSolutionDate(String solutionDate) {
        this.solutionDate = solutionDate;
    }

    public CalendarPanel getCalendarPanel() {
        return calendarPanel;
    }
}
