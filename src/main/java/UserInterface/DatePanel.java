package UserInterface;

import Execution.Main;
import Scripts.Event;
import static Assets.Pallete.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Executable;
import java.security.DigestException;
import java.util.LinkedList;

public class DatePanel extends JPanel implements ActionListener{
    private JPanel mainContentPanel, headerPanel, backButtonContainer,centerContainer, timeLineContainer, eventList, eventListContainer,
            eastButtonContainer, gridButtonContainer, vertScrollMarginContainer, suggestionContainer;
    private JPanel backNorthMargin, backSouthMargin, backWestMargin, westMargin1, eastMargin1, westMargin2, southMargin1, northMargin3,
            eastButtonMargin, westButtonMargin, southButtonMargin, northSuggestionMargin, southSuggestionMargin;
    private JLayeredPane mainLayeredPane;
    private JLabel title, message, suggestionNumber;
    private JScrollPane timeLineScrollPane, eventListScrollPane;
    private JButton back, addEvent, selectSolution, nextSuggestion, previousSuggestion;
    private boolean selected, menuUp = false;

    private JPanel menuPanel, solidMenu, translucentMenu;

    public DatePanel() {
        this.setLayout(null);

        back = new JButton("Back to Calendar");
        back.setBorderPainted(false);
        back.setFont(new Font("Georgia", Font.PLAIN, 18));
        back.setPreferredSize(new Dimension(180, 70));
        back.setOpaque(true);
        back.addActionListener(this);

        backNorthMargin = new JPanel();
        backNorthMargin.setPreferredSize(new Dimension(0, 50));

        backSouthMargin = new JPanel();
        backSouthMargin.setPreferredSize(new Dimension(0, 50));

        backWestMargin = new JPanel();
        backWestMargin.setPreferredSize(new Dimension(40, 0));

        backButtonContainer = new JPanel();
        backButtonContainer.setLayout(new BorderLayout());
        backButtonContainer.setPreferredSize(new Dimension(220, 170));
        backButtonContainer.add(backNorthMargin, BorderLayout.NORTH);
        backButtonContainer.add(backSouthMargin, BorderLayout.SOUTH);
        backButtonContainer.add(backWestMargin, BorderLayout.WEST);
        backButtonContainer.add(back, BorderLayout.CENTER);

        title = new JLabel();
        title.setPreferredSize(new Dimension(200, 170));
        title.setFont(new Font("Trebuchet MS", Font.BOLD, 48));

        northSuggestionMargin = new JPanel();
        northSuggestionMargin.setPreferredSize(new Dimension(0, 55));

        southSuggestionMargin = new JPanel();
        southSuggestionMargin.setPreferredSize(new Dimension(0, 55));

        nextSuggestion = new JButton("Next  -->");
        nextSuggestion.setBorderPainted(false);
        nextSuggestion.setFont(new Font("Georgia", Font.PLAIN, 16));
        nextSuggestion.setPreferredSize(new Dimension(160, 60));
        nextSuggestion.setOpaque(true);
        nextSuggestion.addActionListener(this);

        previousSuggestion = new JButton("<-- Previous");
        previousSuggestion.setBorderPainted(false);
        previousSuggestion.setFont(new Font("Georgia", Font.PLAIN, 16));
        previousSuggestion.setPreferredSize(new Dimension(160, 60));
        previousSuggestion.setOpaque(true);
        previousSuggestion.addActionListener(this);

        suggestionNumber = new JLabel("suggestion 2", SwingConstants.CENTER);
        suggestionNumber.setPreferredSize(new Dimension(160, 60));
        suggestionNumber.setFont(new Font("Trebuchet MS", Font.BOLD, 20));

        suggestionContainer = new JPanel();
        suggestionContainer.setLayout(new BorderLayout());
        suggestionContainer.setPreferredSize(new Dimension(600, 170));
        suggestionContainer.add(previousSuggestion, BorderLayout.WEST);
        suggestionContainer.add(nextSuggestion, BorderLayout.EAST);
        suggestionContainer.add(suggestionNumber, BorderLayout.CENTER);
        suggestionContainer.add(northSuggestionMargin, BorderLayout.NORTH);
        suggestionContainer.add(southSuggestionMargin, BorderLayout.SOUTH);

        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(1200, 170));
        headerPanel.add(backButtonContainer, BorderLayout.WEST);
        headerPanel.add(title, BorderLayout.CENTER);
        headerPanel.add(suggestionContainer, BorderLayout.EAST);
        headerPanel.setOpaque(true);

        westMargin1 = new JPanel();
        westMargin1.setPreferredSize(new Dimension(50, 0));

        eastMargin1 = new JPanel();
        eastMargin1.setPreferredSize(new Dimension(50, 0));

        southMargin1 = new JPanel();
        southMargin1.setPreferredSize(new Dimension(0, 70));

        timeLineContainer = new JPanel();

        timeLineScrollPane = new JScrollPane(timeLineContainer, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        timeLineScrollPane.setPreferredSize(new Dimension(1100, 180));
        timeLineScrollPane.getVerticalScrollBar().setUnitIncrement(10);

        message = new JLabel("No events are planned yet", SwingConstants.CENTER);
        message.setFont(new Font("Georgia", Font.BOLD, 30));
        message.setOpaque(true);

        eventList = new JPanel();

        eventListContainer = new JPanel();
        eventListContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        eventListContainer.add(eventList);

        eventListScrollPane = new JScrollPane(eventListContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        eventListScrollPane.setPreferredSize(new Dimension(700, 310));
        eventListScrollPane.getVerticalScrollBar().setUnitIncrement(10);

        northMargin3 = new JPanel();
        northMargin3.setPreferredSize(new Dimension(0, 50));

        westButtonMargin = new JPanel();
        westButtonMargin.setPreferredSize(new Dimension(75, 0));

        eastButtonMargin = new JPanel();
        eastButtonMargin.setPreferredSize(new Dimension(40, 0));

        southButtonMargin = new JPanel();
        southButtonMargin.setPreferredSize(new Dimension(0, 150));

        addEvent = new JButton("Add new event");
        addEvent.setBorderPainted(false);
        addEvent.setFont(new Font("Georgia", Font.BOLD, 18));
        addEvent.setOpaque(true);
        addEvent.addActionListener(this);

        selectSolution = new JButton();
        selectSolution.setBorderPainted(false);
        selectSolution.setFont(new Font("Georgia", Font.BOLD, 18));
        selectSolution.setOpaque(true);
        selectSolution.addActionListener(this);

        GridLayout gl = new GridLayout(2, 1);
        gl.setVgap(30);
        gridButtonContainer = new JPanel();
        gridButtonContainer.setLayout(gl);
        gridButtonContainer.setPreferredSize(new Dimension(185, 160));
        gridButtonContainer.add(selectSolution);
        gridButtonContainer.add(addEvent);

        eastButtonContainer = new JPanel();
        eastButtonContainer.setPreferredSize(new Dimension(300, 310));
        eastButtonContainer.setLayout(new BorderLayout());
        eastButtonContainer.add(westButtonMargin, BorderLayout.WEST);
        eastButtonContainer.add(eastButtonMargin, BorderLayout.EAST);
        eastButtonContainer.add(southButtonMargin, BorderLayout.SOUTH);
        eastButtonContainer.add(gridButtonContainer, BorderLayout.CENTER);

        vertScrollMarginContainer = new JPanel();
        vertScrollMarginContainer.setLayout(new BorderLayout());
        vertScrollMarginContainer.setPreferredSize(new Dimension(1060, 360));
        vertScrollMarginContainer.add(eventListScrollPane, BorderLayout.CENTER);
        vertScrollMarginContainer.add(eastButtonContainer, BorderLayout.EAST);
        vertScrollMarginContainer.add(northMargin3, BorderLayout.NORTH);

        westMargin2 = new JPanel();
        westMargin2.setPreferredSize(new Dimension(40, 0));

        centerContainer = new JPanel();
        centerContainer.setLayout(new BorderLayout());
        centerContainer.setPreferredSize(new Dimension(1100, 560));
        centerContainer.add(timeLineScrollPane, BorderLayout.NORTH);
        centerContainer.add(vertScrollMarginContainer, BorderLayout.CENTER);
        centerContainer.add(westMargin2, BorderLayout.WEST);
        centerContainer.add(southMargin1, BorderLayout.SOUTH);

        mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BorderLayout());
        mainContentPanel.setOpaque(true);
        mainContentPanel.setBounds(0, 0, 1200, 800);

        mainContentPanel.add(headerPanel, BorderLayout.NORTH);
        mainContentPanel.add(westMargin1, BorderLayout.WEST);
        mainContentPanel.add(eastMargin1, BorderLayout.EAST);
        mainContentPanel.add(centerContainer, BorderLayout.CENTER);



        solidMenu = new JPanel();
        solidMenu.setBackground(new Color(21, 20, 20));
        solidMenu.setPreferredSize(new Dimension(510, 800));

        translucentMenu = new JPanel();
        translucentMenu.setBackground(new Color(26, 25, 25, 180));
        translucentMenu.setPreferredSize(new Dimension(690, 800));

        menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.setBounds(0, 0, 1200, 800);
        menuPanel.add(solidMenu, BorderLayout.EAST);
        menuPanel.add(translucentMenu, BorderLayout.WEST);
        menuPanel.setOpaque(false);
        menuPanel.setVisible(menuUp);

        mainLayeredPane = new JLayeredPane();
        mainLayeredPane.setBounds(0, 0, 1200, 800);
        //TODO: do the menu later
        mainLayeredPane.add(menuPanel, Integer.valueOf(2));
        mainLayeredPane.add(mainContentPanel, Integer.valueOf(1));

        this.add(mainLayeredPane);

        this.colorTheme();

    }

    public void updateTimeLine() {

    }

    public void updateVertList(LinkedList<Event> events) {
        eventList.removeAll();
        eventList.setPreferredSize(new Dimension(700, events.size()==0 ? 300 : 80*events.size()));
        eventListContainer.setPreferredSize(new Dimension(700, 80* events.size()));
        if (events.size()==0) {
            eventList.add(message);
            eventList.setLayout(new FlowLayout(0, 150, 140));
        } else {
            for (Event e : events) {
                //TODO: use random color generator later
                EventListItem eventListItem = new EventListItem(e.getTitle(), e.getStartTime(), e.getEndTime(), Color.blue);
                eventListItem.colorTheme();
                eventList.setLayout(new FlowLayout(0, 0, 0));
                eventList.add(eventListItem);
            }
        }



    }

    public static class EventListItem extends JPanel {
        private JLabel eventName, time;
        private JButton remove;
        private Color color;
        public EventListItem(String eventName, String startTime, String endTime, Color color) {
            this.setLayout(new GridBagLayout());
            this.setPreferredSize(new Dimension(700, 80));
            this.eventName = new JLabel(eventName, SwingConstants.CENTER);
            this.eventName.setPreferredSize(new Dimension(150, 80));
            this.eventName.setFont(new Font("Georgia", Font.BOLD, 24));
            this.color = color;
            this.time = new JLabel(startTime+" - "+endTime, SwingConstants.CENTER);
            this.time.setPreferredSize(new Dimension(430, 80));
            this.time.setFont(new Font("Georgia", Font.PLAIN, 24));
            this.remove = new JButton("Remove");
            this.remove.setPreferredSize(new Dimension(120, 60));
            this.remove.setFont(new Font("Georgia", Font.PLAIN, 20));
            this.remove.setBorderPainted(false);
            this.remove.setOpaque(true);
            this.remove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!Main.getGui().getDatePanel().menuUp) {
                        //TODO: do something here when the method is set up
                    }

                }
            });
            this.add(this.eventName);
            this.add(time);
            this.add(remove);
            this.colorTheme();
        }

        public void colorTheme() {
            this.setBackground(dlistBackColor);
            eventName.setForeground(this.color);
            time.setForeground(this.color);
            remove.setForeground(dlabelForeColor);
            remove.setBackground(dbuttonBackColor);
        }
    }

    //TODO: this method is not done, need to add timeline update here
    public void setDate(String date, LinkedList<Event> plans, boolean isSetSolution) {
        title.setText("     " + date.replace("#", ""));
        if (isSetSolution) {
            selected = true;
            selectSolution.setText("Solution selected");
            selectSolution.setForeground(new Color(140, 140, 140));
            selectSolution.setBackground(new Color(25, 25, 25));
            selectSolution.setBorderPainted(true);
            selectSolution.setBorder(BorderFactory.createEtchedBorder(0));
            suggestionContainer.setVisible(false);
        } else {
            selected = false;
            selectSolution.setText("Select solution");
        }
        updateVertList(plans);
    }

    public void colorTheme() {
        mainContentPanel.setBackground(dmainBackColor);
        title.setForeground(dtitleForeColor);
        backWestMargin.setBackground(dmainBackColor);
        backSouthMargin.setBackground(dmainBackColor);
        backNorthMargin.setBackground(dmainBackColor);
        back.setBackground(dbuttonBackColor);
        back.setForeground(dlabelForeColor);
        backButtonContainer.setBackground(dmainBackColor);
        suggestionNumber.setForeground(dlabelForeColor);
        northSuggestionMargin.setBackground(dmainBackColor);
        southSuggestionMargin.setBackground(dmainBackColor);
        previousSuggestion.setForeground(dlabelForeColor);
        previousSuggestion.setBackground(dbuttonBackColor);
        nextSuggestion.setForeground(dlabelForeColor);
        nextSuggestion.setBackground(dbuttonBackColor);
        suggestionContainer.setBackground(dmainBackColor);
        headerPanel.setBackground(dmainBackColor);
        westMargin1.setBackground(dmainBackColor);
        eastMargin1.setBackground(dmainBackColor);
        southMargin1.setBackground(dmainBackColor);
        centerContainer.setBackground(dmainBackColor);
        timeLineContainer.setBackground(dlistBackColor);
        eastButtonContainer.setBackground(dmainBackColor);
        addEvent.setBackground(dnavButtonBackColor);
        addEvent.setForeground(dnavButtonForeColor);
        selectSolution.setBackground(dnavButtonBackColor);
        selectSolution.setForeground(dnavButtonForeColor);
        gridButtonContainer.setBackground(dmainBackColor);
        eastButtonMargin.setBackground(dmainBackColor);
        westButtonMargin.setBackground(dmainBackColor);
        southButtonMargin.setBackground(dmainBackColor);
        westMargin2.setBackground(dmainBackColor);
        northMargin3.setBackground(dmainBackColor);
        message.setBackground(dlistBackColor);
        message.setForeground(dlabelForeColor);
        eventListContainer.setBackground(dlistBackColor);
        eventList.setBackground(dlistBackColor);
    }

//  TODO: finish the action listener alex
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addEvent && !menuUp) {
            menuPanel.setVisible(menuUp = true);
        } else if (e.getSource()==selectSolution && !menuUp) {
            if (!selected) {
                selected = true;
                selectSolution.setText("Solution selected");
                selectSolution.setForeground(new Color(140, 140, 140));
                selectSolution.setBackground(new Color(25, 25, 25));
                selectSolution.setBorderPainted(true);
                selectSolution.setBorder(BorderFactory.createEtchedBorder(0));
                suggestionContainer.setVisible(false);
            }
        } else if (e.getSource()==back && !menuUp) {
            Execution.Main.getGui().changePanel("1");
        }
    }
}
