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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class DatePanel extends JPanel implements ActionListener{
    private String date;
    private LinkedList<Event> plans;

    private JPanel mainContentPanel, headerPanel, backButtonContainer,centerContainer, timeLineContainer, eventList, eventListContainer,
            eastButtonContainer, gridButtonContainer, vertScrollMarginContainer, suggestionContainer;
    private JPanel backNorthMargin, backSouthMargin, backWestMargin, westMargin1, eastMargin1, westMargin2, southMargin1, northMargin3,
            eastButtonMargin, westButtonMargin, southButtonMargin, northSuggestionMargin, southSuggestionMargin;
    private JLayeredPane mainLayeredPane;
    private JLabel title, message, suggestionNumber;
    private JScrollPane timeLineScrollPane, eventListScrollPane;
    private JButton back, addEvent, selectSolution, nextSuggestion, previousSuggestion;
    private boolean selected, menuUp = false;

    private JPanel menuPanel, solidMenu, translucentMenu, menuContentPanel;
    private JLabel menuTitle, addEventTitle, addEventType, addEventStart, addEventEnd, addEventRepeat, addEventLevel;
    private JScrollPane menuScrollPane;
    private int heightV=50, marginV=10, sectionV=20, heightC=0, marginC=1, sectionC=0;
    private JTextField inEventTitle, inEventStart, inEventEnd;
    private JRadioButton staticEvent, dynamicEvent, levelOne, levelTwo, levelThree, levelFour, levelFive;
    private ButtonGroup eventType, eventLevel;
    private JCheckBox mon, tue, wed, thu, fri, sat, sun;
    private JButton cancel, confirm;

    private Color[] colorList;

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

        previousSuggestion = new JButton("<-- Previous");
        previousSuggestion.setBorderPainted(false);
        previousSuggestion.setFont(new Font("Georgia", Font.PLAIN, 16));
        previousSuggestion.setPreferredSize(new Dimension(160, 60));
        previousSuggestion.setOpaque(true);

        suggestionNumber = new JLabel("", SwingConstants.CENTER);
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




        menuTitle = new JLabel("    Add new event: ");
        menuTitle.setPreferredSize(new Dimension(510, 100));
        menuTitle.setFont(new Font("Trebuchet MS", Font.BOLD, 36));

        addEventTitle = new JLabel("Event Title:");
        addEventTitle.setFont(new Font("Georgia", Font.PLAIN, 24));
        addEventTitle.setBounds(50, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC, 350, this.heightV);
        heightC++; marginC++;

        inEventTitle = new JTextField();
        inEventTitle.setBounds(50, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC, 400, this.heightV);
        inEventTitle.setFont(new Font("", Font.PLAIN, 18));
        marginC++; heightC++; sectionC++;

        addEventType = new JLabel("Event Type:");
        addEventType.setFont(new Font("Georgia", Font.PLAIN, 24));
        addEventType.setBounds(50, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC, 350, this.heightV);
        heightC++; marginC++;

        staticEvent = new JRadioButton("static event");
        staticEvent.setFont(new Font("Georgia", Font.PLAIN, 18));
        staticEvent.setBounds(50, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC, 150, this.heightV);
        staticEvent.addActionListener(this);

        dynamicEvent = new JRadioButton("dynamic event");
        dynamicEvent.setFont(new Font("Georgia", Font.PLAIN, 20));
        dynamicEvent.setBounds(200, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC, 170, this.heightV);
        dynamicEvent.addActionListener(this);
        heightC++; marginC++; sectionC++;

        eventType = new ButtonGroup();
        eventType.add(staticEvent);
        eventType.add(dynamicEvent);
        eventType.setSelected(staticEvent.getModel(), true);

        addEventStart = new JLabel("Event Start Time (hh:mm):");
        addEventStart.setFont(new Font("Georgia", Font.PLAIN, 24));
        addEventStart.setBounds(50, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC, 450, this.heightV);
        heightC++; marginC++;

        inEventStart = new JTextField();
        inEventStart.setBounds(50, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC, 400, this.heightV);
        inEventStart.setFont(new Font("", Font.PLAIN, 18));
        inEventStart.setText("hh:mm (24 hours time)");
        marginC++; heightC++; sectionC++;

        addEventEnd = new JLabel("Event End Time (hh:mm):");
        addEventEnd.setFont(new Font("Georgia", Font.PLAIN, 24));
        addEventEnd.setBounds(50, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC, 450, this.heightV);
        heightC++; marginC++;

        inEventEnd = new JTextField();
        inEventEnd.setBounds(50, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC, 400, this.heightV);
        inEventEnd.setFont(new Font("", Font.PLAIN, 18));
        inEventEnd.setText("hh:mm (24 hours time)");
        marginC++; heightC++; sectionC++;

        addEventRepeat = new JLabel("Repeat Pattern:");
        addEventRepeat.setFont(new Font("Georgia", Font.PLAIN, 24));
        addEventRepeat.setBounds(50, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC, 350, this.heightV);
        heightC++; marginC++;

        mon = new JCheckBox("Monday");
        mon.setFont(new Font("Georgia", Font.PLAIN, 20));
        mon.setFocusable(false);
        mon.setBounds(50, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC, 350, this.heightV);
        heightC++;

        tue = new JCheckBox("Tuesday");
        tue.setFont(new Font("Georgia", Font.PLAIN, 20));
        tue.setFocusable(false);
        tue.setBounds(50, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC, 350, this.heightV);
        heightC++;

        wed = new JCheckBox("Wednesday");
        wed.setFont(new Font("Georgia", Font.PLAIN, 20));
        wed.setFocusable(false);
        wed.setBounds(50, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC, 350, this.heightV);
        heightC++;

        thu = new JCheckBox("Thursday");
        thu.setFont(new Font("Georgia", Font.PLAIN, 20));
        thu.setFocusable(false);
        thu.setBounds(50, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC, 350, this.heightV);
        heightC++;

        fri = new JCheckBox("Friday");
        fri.setFont(new Font("Georgia", Font.PLAIN, 20));
        fri.setFocusable(false);
        fri.setBounds(50, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC, 350, this.heightV);
        heightC++;

        sat = new JCheckBox("Saturday");
        sat.setFont(new Font("Georgia", Font.PLAIN, 20));
        sat.setFocusable(false);
        sat.setBounds(50, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC, 350, this.heightV);
        heightC++;

        sun = new JCheckBox("Sunday");
        sun.setFont(new Font("Georgia", Font.PLAIN, 20));
        sun.setFocusable(false);
        sun.setBounds(50, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC, 350, this.heightV);
        heightC++; marginC++; sectionC++;

        addEventLevel = new JLabel("Priority Level:");
        addEventLevel.setFont(new Font("Georgia", Font.PLAIN, 24));
        addEventLevel.setBounds(50, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC, 350, this.heightV);
        heightC++; marginC++;

        levelOne = new JRadioButton("1");
        levelOne.setFont(new Font("Georgia", Font.PLAIN, 18));
        levelOne.setBounds(50, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC, 50, this.heightV);

        levelTwo = new JRadioButton("2");
        levelTwo.setFont(new Font("Georgia", Font.PLAIN, 18));
        levelTwo.setBounds(110, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC, 50, this.heightV);

        levelThree = new JRadioButton("3");
        levelThree.setFont(new Font("Georgia", Font.PLAIN, 18));
        levelThree.setBounds(170, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC, 50, this.heightV);

        levelFour = new JRadioButton("4");
        levelFour.setFont(new Font("Georgia", Font.PLAIN, 18));
        levelFour.setBounds(230, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC, 50, this.heightV);

        levelFive = new JRadioButton("5");
        levelFive.setFont(new Font("Georgia", Font.PLAIN, 18));
        levelFive.setBounds(290, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC, 50, this.heightV);

        eventLevel = new ButtonGroup();
        eventLevel.add(levelOne);
        eventLevel.add(levelTwo);
        eventLevel.add(levelThree);
        eventLevel.add(levelFour);
        eventLevel.add(levelFive);
        eventLevel.setSelected(levelOne.getModel(), true);
        heightC++; marginC++; sectionC++;

        cancel = new JButton("cancel");
        cancel.setBorderPainted(false);
        cancel.setFont(new Font("Georgia", Font.PLAIN, 18));
        cancel.setPreferredSize(new Dimension(180, 70));
        cancel.setOpaque(true);
        cancel.setBounds(160, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC+20, 110, this.heightV);
        cancel.addActionListener(this);

        confirm = new JButton("confirm");
        confirm.setBorderPainted(false);
        confirm.setFont(new Font("Georgia", Font.PLAIN, 18));
        confirm.setPreferredSize(new Dimension(180, 70));
        confirm.setOpaque(true);
        confirm.setBounds(290, this.marginV*marginC+this.heightV*heightC+this.sectionV*sectionC+20, 110, this.heightV);
        confirm.addActionListener(this);


        menuContentPanel = new JPanel();
        menuContentPanel.setPreferredSize(new Dimension(510, 1300));
        menuContentPanel.setLayout(null);
        menuContentPanel.add(addEventTitle);
        menuContentPanel.add(inEventTitle);
        menuContentPanel.add(addEventType);
        menuContentPanel.add(staticEvent);
        menuContentPanel.add(dynamicEvent);
        menuContentPanel.add(addEventStart);
        menuContentPanel.add(inEventStart);
        menuContentPanel.add(addEventEnd);
        menuContentPanel.add(inEventEnd);
        menuContentPanel.add(addEventRepeat);
        menuContentPanel.add(mon);
        menuContentPanel.add(tue);
        menuContentPanel.add(wed);
        menuContentPanel.add(thu);
        menuContentPanel.add(fri);
        menuContentPanel.add(sat);
        menuContentPanel.add(sun);
        menuContentPanel.add(addEventLevel);
        menuContentPanel.add(levelOne);
        menuContentPanel.add(levelTwo);
        menuContentPanel.add(levelThree);
        menuContentPanel.add(levelFour);
        menuContentPanel.add(levelFive);
        menuContentPanel.add(cancel);
        menuContentPanel.add(confirm);

        menuScrollPane = new JScrollPane(menuContentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        menuScrollPane.setPreferredSize(new Dimension(510, 700));
        menuScrollPane.setBorder(null);
        menuScrollPane.getVerticalScrollBar().setUnitIncrement(10);

        solidMenu = new JPanel();
        solidMenu.setPreferredSize(new Dimension(510, 800));
        solidMenu.setLayout(new BorderLayout());
        solidMenu.add(menuTitle, BorderLayout.NORTH);
        solidMenu.add(menuScrollPane, BorderLayout.CENTER);

        translucentMenu = new JPanel();
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

    //
    public void updateTimeLine() {

    }

    public void updateVertList(LinkedList<Event> events) {
        eventList.removeAll();
        eventList.setPreferredSize(new Dimension(700, events.size()==0 ? 300 : 80*events.size()));
        eventListContainer.setPreferredSize(new Dimension(700, 80* events.size()));
        int index = 0;
        if (events.size()==0) {
            eventList.add(message);
            eventList.setLayout(new FlowLayout(0, 150, 140));
        } else {
            for (Event e : events) {
                //TODO: use random color generator later
                EventListItem eventListItem = new EventListItem(e.getTitle(), e.getStartTime(), e.getEndTime(), this.colorList[index]);
                eventListItem.colorTheme();
                eventList.setLayout(new FlowLayout(0, 0, 0));
                eventList.add(eventListItem);
                index++;
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
        this.plans = plans;
        this.date = date.replace("#", "");
        this.colorList = new Color[plans.size()];
        generateColor();
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

    public void generateColor() {
        Random random = new Random();
        for (int i=0; i<this.colorList.length; i++) {
            float r = (float) (random.nextFloat() / 2f + 0.5);
            float g = (float) (random.nextFloat() / 2f + 0.5);
            float b = (float) (random.nextFloat() / 2f + 0.5);
            Color randomColor = new Color(r, g, b);
            this.colorList[i] = randomColor;
        }
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


        menuTitle.setForeground(dlabelForeColor);
        addEventTitle.setForeground(dlabelForeColor);
        inEventTitle.setForeground(dtextFieldForeColor);
        inEventTitle.setBackground(dtextFieldBackColor);
        inEventTitle.setCaretColor(dtextFieldCaretColor);
        addEventType.setForeground(dlabelForeColor);
        staticEvent.setForeground(dlabelForeColor);
        staticEvent.setBackground(new Color(21, 20, 20));
        dynamicEvent.setForeground(dlabelForeColor);
        dynamicEvent.setBackground(new Color(21, 20, 20));
        addEventType.setBackground(new Color(21, 20, 20));
        addEventStart.setForeground(dlabelForeColor);
        inEventStart.setForeground(dtextFieldForeColor);
        inEventStart.setBackground(dtextFieldBackColor);
        inEventStart.setCaretColor(dtextFieldCaretColor);
        addEventEnd.setForeground(dlabelForeColor);
        inEventEnd.setForeground(dtextFieldForeColor);
        inEventEnd.setBackground(dtextFieldBackColor);
        inEventEnd.setCaretColor(dtextFieldCaretColor);
        addEventRepeat.setForeground(dtitleForeColor);
        mon.setForeground(dlabelForeColor);
        mon.setBackground(new Color(21, 20, 20));
        tue.setForeground(dlabelForeColor);
        tue.setBackground(new Color(21, 20, 20));
        wed.setForeground(dlabelForeColor);
        wed.setBackground(new Color(21, 20, 20));
        thu.setForeground(dlabelForeColor);
        thu.setBackground(new Color(21, 20, 20));
        fri.setForeground(dlabelForeColor);
        fri.setBackground(new Color(21, 20, 20));
        sat.setForeground(dlabelForeColor);
        sat.setBackground(new Color(21, 20, 20));
        sun.setForeground(dlabelForeColor);
        sun.setBackground(new Color(21, 20, 20));
        addEventLevel.setForeground(dlabelForeColor);
        addEventEnd.setBackground(new Color(21, 20, 20));
        levelOne.setForeground(dlabelForeColor);
        levelOne.setBackground(new Color(21, 20, 20));
        levelTwo.setForeground(dlabelForeColor);
        levelTwo.setBackground(new Color(21, 20, 20));
        levelThree.setForeground(dlabelForeColor);
        levelThree.setBackground(new Color(21, 20, 20));
        levelFour.setForeground(dlabelForeColor);
        levelFour.setBackground(new Color(21, 20, 20));
        levelFive.setForeground(dlabelForeColor);
        levelFive.setBackground(new Color(21, 20, 20));
        cancel.setBackground(dbuttonBackColor);
        cancel.setForeground(dlabelForeColor);
        confirm.setBackground(dbuttonBackColor);
        confirm.setForeground(dlabelForeColor);
        solidMenu.setBackground(new Color(21, 20, 20));
        translucentMenu.setBackground(new Color(26, 25, 25, 180));
        menuContentPanel.setBackground(new Color(21, 20, 20));
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
                Execution.Main.getPlannedDatesData().replace(this.date, this.plans);
            }
        } else if (e.getSource()==back && !menuUp) {
            Execution.Main.getGui().changePanel("1");
            Execution.Main.getGui().getCalendarPanel().updateList();
        } else if (e.getSource()==cancel && menuUp) {
            menuPanel.setVisible(menuUp = false);
        } else if (e.getSource()==confirm) {
            ArrayList<String> repeatList = new ArrayList<>();
            if (mon.isSelected()) repeatList.add("1");
            if (tue.isSelected()) repeatList.add("2");
            if (wed.isSelected()) repeatList.add("3");
            if (thu.isSelected()) repeatList.add("4");
            if (fri.isSelected()) repeatList.add("5");
            if (sat.isSelected()) repeatList.add("6");
            if (sun.isSelected()) repeatList.add("7");
            int level = 1;
            if (levelOne.isSelected()) level = 1;
            if (levelTwo.isSelected()) level = 2;
            if (levelThree.isSelected()) level = 3;
            if (levelFour.isSelected()) level = 4;
            if (levelFive.isSelected()) level = 5;
            boolean dyn;
            dyn = dynamicEvent.isSelected();
            if (dyn) {
                inEventStart.setText("00:00");
            }

            Event newEvent = new Event(inEventTitle.getText(), date, inEventStart.getText(), inEventEnd.getText(), repeatList, level, dyn);
            if (repeatList.isEmpty()) {Execution.Main.getRepeatingEvents().add(newEvent);}
            menuPanel.setVisible(menuUp = false);
            try {
                Execution.Main.getGui().setSolutions(Scripts.DateStorage.getSuggestions(date, newEvent));
                Execution.Main.getGui().setSolutionDate(date);
                Execution.Main.getGui().addSuggested();
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource()==staticEvent && menuUp) {
            inEventStart.setText("hh:mm (24 hours time)");
            addEventEnd.setText("Event End Time:");
            inEventEnd.setText("hh:mm (24 hours time)");
            inEventStart.setEnabled(true);
            inEventEnd.setEnabled(true);
        } else if (e.getSource()==dynamicEvent && menuUp) {
            inEventStart.setText("Disabled due to dynamic event type");
            addEventEnd.setText("Length of event:");
            inEventEnd.setText("enter the length of event in hh:mm format");
            inEventStart.setEnabled(false);
        }

    }

    public void setPreviousSuggestion(String targetPage) {
        this.previousSuggestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Execution.Main.getGui().changePanel(targetPage);
            }
        });
    }

    public void setNextSuggestion(String targetPage) {
        this.nextSuggestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Execution.Main.getGui().changePanel(targetPage);
            }
        });
    }

    public void setSuggestionNumber(String text) {
        this.suggestionNumber.setText("suggestion " + text);
    }
}
