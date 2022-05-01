package UserInterface;

import Scripts.Event;

import static Assets.Pallete.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Executable;
import java.util.HashMap;
import java.util.LinkedList;

public class CalendarPanel extends JPanel implements ActionListener {
    private JPanel centerPanel, searchHeaderPanel, scrollPaneContainer, plannedDatesList;
    private JPanel westMargin1, eastMargin1, northMargin1, southMargin1, westMargin2, eastMargin2;
    private JButton searchButton;
    private JTextField searchbox;
    private JScrollPane scrollList;
    private JLabel message;

    public CalendarPanel() {
        this.setSize(1200, 800);
        this.setLayout(new BorderLayout());

        searchbox = new JTextField();
        searchbox.setFont(new Font("", Font.PLAIN, 24));
        searchbox.setPreferredSize(new Dimension(620, 70));
        searchbox.setText("Which day do you like to plan?");
        searchbox.addActionListener(this);

        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Georgia", Font.PLAIN, 28));
        searchButton.setBorderPainted(false);
        searchButton.setOpaque(true);
        searchButton.setPreferredSize(new Dimension(160, 70));
        searchButton.addActionListener(this);

        searchHeaderPanel = new JPanel();
        searchHeaderPanel.setLayout(new FlowLayout(0, 40, 80));
        searchHeaderPanel.add(searchbox);
        searchHeaderPanel.add(searchButton);

        plannedDatesList = new JPanel();

        scrollPaneContainer = new JPanel();
        scrollPaneContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        scrollPaneContainer.add(plannedDatesList);

        message = new JLabel("No planned dates found. Try adding a new plan!", SwingConstants.CENTER);
        message.setFont(new Font("Georgia", Font.BOLD, 30));
        message.setOpaque(true);

        scrollList = new JScrollPane(scrollPaneContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollList.setPreferredSize(new Dimension(800, 450));
        scrollList.getVerticalScrollBar().setUnitIncrement(10);

        westMargin2 = new JPanel();
        westMargin2.setPreferredSize(new Dimension(50, 0));

        eastMargin2 = new JPanel();
        eastMargin2.setPreferredSize(new Dimension(50, 0));

        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(searchHeaderPanel, BorderLayout.NORTH);
        centerPanel.add(scrollList, BorderLayout.CENTER);
        centerPanel.add(westMargin2, BorderLayout.WEST);
        centerPanel.add(eastMargin2, BorderLayout.EAST);

        northMargin1 = new JPanel();
        northMargin1.setPreferredSize(new Dimension(0, 50));

        southMargin1 = new JPanel();
        southMargin1.setPreferredSize(new Dimension(0, 100));

        westMargin1 = new JPanel();
        westMargin1.setPreferredSize(new Dimension(150, 0));

        eastMargin1 = new JPanel();
        eastMargin1.setPreferredSize(new Dimension(150, 0));

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(northMargin1, BorderLayout.NORTH);
        this.add(westMargin1, BorderLayout.WEST);
        this.add(eastMargin1, BorderLayout.EAST);
        this.add(southMargin1, BorderLayout.SOUTH);

        this.colorTheme();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== searchButton || e.getSource()==searchbox) {
            String dateInputed = searchbox.getText().trim();
            LinkedList<Event> list = Scripts.DateStorage.getMerge(dateInputed);
            Execution.Main.getGui().showDate(dateInputed, list,true);
        }
    }

    public void updateList() {
        HashMap<String, LinkedList<Event>> dataMap = Execution.Main.getPlannedDatesData();
        int hmSize = dataMap.size();
        plannedDatesList.removeAll();
        plannedDatesList.setPreferredSize(new Dimension(800, dataMap.size()==0? 450 : 120*hmSize));
        scrollPaneContainer.setPreferredSize(new Dimension(800, 120*hmSize));
        if (hmSize==0) {
            plannedDatesList.add(message);
            plannedDatesList.setLayout(new FlowLayout(0, 25, 180));
        } else {
            for (String date : dataMap.keySet()) {
                PlannedDate panelItem = new PlannedDate(date, dataMap.get(date));
                panelItem.colorTheme();
                plannedDatesList.setLayout(new FlowLayout(0, 0, 0));
                plannedDatesList.add(panelItem);
            }
        }
    }

    public void colorTheme() {
        this.setBackground(dmainBackColor);
        westMargin1.setBackground(dmainBackColor);
        eastMargin1.setBackground(dmainBackColor);
        northMargin1.setBackground(dmainBackColor);
        southMargin1.setBackground(dmainBackColor);
        searchbox.setForeground(dtextFieldForeColor);
        searchbox.setCaretColor(dtextFieldCaretColor);
        searchbox.setBackground(dtextFieldBackColor);
        searchButton.setBackground(dtextFieldBackColor);
        searchButton.setForeground(dlabelForeColor);
        searchHeaderPanel.setBackground(dmainBackColor);
        scrollPaneContainer.setBackground(dlistBackColor);
        westMargin2.setBackground(dmainBackColor);
        eastMargin2.setBackground(dmainBackColor);
        message.setBackground(dlistBackColor);
        message.setForeground(dlabelForeColor);
        plannedDatesList.setBackground(dlistBackColor);
    }

    public static class PlannedDate extends JPanel {
        private JLabel dateLabel, numOfPlans;
        private JButton getDate;
        public PlannedDate(String date, LinkedList<Event> plans) {
            this.setLayout(new GridBagLayout());
            this.setPreferredSize(new Dimension(800, 120));
            dateLabel = new JLabel(date.replace("#", ""));
            dateLabel.setPreferredSize(new Dimension(250, 120));
            dateLabel.setFont(new Font("Georgia", Font.PLAIN, 24));
            numOfPlans = new JLabel(plans.size() + " plans");
            numOfPlans.setPreferredSize(new Dimension(250, 120));
            numOfPlans.setFont(new Font("Georgia", Font.PLAIN, 24));
            getDate = new JButton("navigate");
            getDate.setFont(new Font("Georgia", Font.PLAIN, 24));
            getDate.setBorderPainted(false);
            getDate.setOpaque(true);
            getDate.setPreferredSize(new Dimension(140, 60));
            getDate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Execution.Main.getGui().showDate(date, plans, true);
                }
            });
            this.add(dateLabel);
            this.add(numOfPlans);
            this.add(getDate);
            this.colorTheme();
        }

        public void colorTheme() {
            this.setBackground(dlistBackColor);
            dateLabel.setForeground(dlabelForeColor);
            numOfPlans.setForeground(dlabelForeColor);
            getDate.setForeground(dlabelForeColor);
            getDate.setBackground(dbuttonBackColor);
        }
    }
}
