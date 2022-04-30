package UserInterface;

import static Assets.Pallete.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        searchbox.setFont(new Font("", Font.PLAIN, 56));
        searchbox.setPreferredSize(new Dimension(620, 100));
        searchbox.addActionListener(this);

        searchButton = new JButton("search");
        searchButton.setFont(new Font("Georgia", Font.PLAIN, 24));
        searchButton.setBorderPainted(false);
        searchButton.setOpaque(true);
        searchButton.setPreferredSize(new Dimension(160, 90));
        searchButton.addActionListener(this);

        searchHeaderPanel = new JPanel();
        searchHeaderPanel.setLayout(new FlowLayout(0, 40, 50));
        searchHeaderPanel.add(searchbox);
        searchHeaderPanel.add(searchButton);

        plannedDatesList = new JPanel();

        scrollPaneContainer = new JPanel();
        scrollPaneContainer.setPreferredSize(new Dimension(800, 800));
        scrollPaneContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        scrollPaneContainer.add(plannedDatesList);

        message = new JLabel("No planned dates found. Try adding a new plan!", SwingConstants.CENTER);
        message.setFont(new Font("Georgia", Font.BOLD, 30));
        message.setOpaque(true);

        scrollList = new JScrollPane(scrollPaneContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollList.setLayout(new ScrollPaneLayout());
        scrollList.setPreferredSize(new Dimension(800, 500));
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
            System.out.println("success");
        }
    }

    //FIXME: update the updatelist method later when the list is acquired
    public void updateList() {
        plannedDatesList.removeAll();
        plannedDatesList.setPreferredSize(new Dimension(800, 120*0));
        if (0==0) {
            //shift down with margin
            scrollPaneContainer.add(message);
        } else {
            for (char needDateHere : "haha".toCharArray()) {
                //Date current = needDateHere;
                PlannedDate panelItem = new PlannedDate();
                panelItem.colorTheme();
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
    }

    public static class PlannedDate extends JPanel {
        private JLabel date, numOfPlans;
        private JButton getDate;
        //FIXME: give the constructor correct param
        public PlannedDate() {
            this.setLayout(new GridBagLayout());
            this.setPreferredSize(new Dimension(800, 120));
            date = new JLabel("mm/dd/yyyy");
            date.setPreferredSize(new Dimension(200, 120));
            numOfPlans = new JLabel(0 + "plans");
            numOfPlans.setPreferredSize(new Dimension(200, 120));
            getDate = new JButton("navigate");
            getDate.setFont(new Font("Georigia", Font.PLAIN, 18));
            getDate.setBorderPainted(false);
            getDate.setOpaque(true);
            getDate.setPreferredSize(new Dimension(200, 100));
            getDate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //FIXME: pass the correct param
                        //also the Main isn't working
//                    Main.getGui.showDate();
                }
            });
            this.add(date);
            this.add(numOfPlans);
            this.add(getDate);
            this.colorTheme();
        }

        public void colorTheme() {
            this.setBackground(dlistBackColor);
            date.setForeground(dlabelForeColor);
            numOfPlans.setForeground(dlabelForeColor);
            getDate.setForeground(dlabelForeColor);
            getDate.setBackground(dbuttonBackColor);
        }
    }
}
