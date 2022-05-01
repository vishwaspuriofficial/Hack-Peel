package Execution;

import Scripts.Event;
import UserInterface.CalendarPanel;
import UserInterface.MainFrame;
import java.io.File;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.*;
import java.io.*;

public class Main {
    private static MainFrame gui;
    private static HashMap<String, LinkedList<Event>> plannedDatesData = new HashMap<>();
    private static LinkedList<Event> repeatingEvents = new LinkedList<>();

    public static void main(String[] args) throws IOException, ParseException, CloneNotSupportedException {
        loadMainSave();
        loadRepeatedDays();
//        gui = new MainFrame();
        MainFrame.test();
    }

    public static MainFrame getGui() {
        return gui;
    }

    public static HashMap<String, LinkedList<Event>> getPlannedDatesData() {
        return plannedDatesData;
    }

    public static LinkedList<Event> getRepeatingEvents() {
        return repeatingEvents;
    }


    public static void loadMainSave() throws IOException {
        File file = new File("src/main/java/Databases/mainsave.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        String currentDate = "";
//        while (line!= null) {
//            if (line.charAt(0)=='#') {
//                plannedDatesData.put(line.replace("#", ""), new LinkedList<>());
//                currentDate = line.replace("#", "");
//            } else {
//                String[] attributes = line.split("∂");
//                ArrayList<String> attr3 = new ArrayList<>(Arrays.asList(attributes[3].split(",")));
//                plannedDatesData.get(currentDate).add(new Event(attributes[0], currentDate, attributes[1], attributes[2], attr3, Integer.parseInt(attributes[4]), Boolean.parseBoolean(attributes[5])));
//            }
//            line = br.readLine();
//        }
        while (line != null) {
            if (line.charAt(0) == '#') {
                plannedDatesData.put(line.replace("#", ""), new LinkedList<>());
                currentDate = line.replace("#", "");
                line = br.readLine();
                String[] attributes = line.split("∂");
                ArrayList<String> attr3 = new ArrayList<>(Arrays.asList(attributes[3].split(",")));
                plannedDatesData.get(currentDate).add(new Event(attributes[0], currentDate, attributes[1], attributes[2], attr3, Integer.parseInt(attributes[4]), Boolean.parseBoolean(attributes[5])));
            }
            line = br.readLine();

        }
        br.close();
    }


    public static void loadRepeatedDays() throws IOException {
        String path = "src/main/java/Databases/repeat.txt";
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = br.readLine();
        while (line!= null) {
            if (line.charAt(0)=='#') {
                String eventName = line.replace("#", "");
                String[] data = br.readLine().split("∂");
                String[] repeat = data[3].split(",");
                ArrayList<String> repeatedDays = new ArrayList<>(Arrays.asList(repeat));
                Event event = new Event(eventName,
                        data[0],
                        data[1],
                        data[2],
                        repeatedDays,
                        Integer.parseInt(data[4]),
                        Boolean.parseBoolean(data[5]));
                repeatingEvents.add(event);
            }
            line = br.readLine();
        }
        br.close();
    }


    public static void saveEvent() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/Databases/mainsave.txt"));
        for (String key : plannedDatesData.keySet()) {
            bw.write("#"+key+"\n");
            for (Event event : plannedDatesData.get(key)) {
                String repeatPattern = String.join(",", event.getRepeatDate().toArray(new String[0]));
                String jointData = event.getTitle()+"∂"+event.getStartTime()+"∂"+event.getEndTime()+"∂"+repeatPattern+"∂"+event.getStressLevel()+"∂"+event.getDynamic();
                bw.write(jointData +"\n");
            }
        }
        bw.close();
    }


    public static void saveRepeatedEvent() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/Databases/repeat.txt"));
        for (Event event : repeatingEvents) {
            bw.write("#"+event.getTitle()+"\n");
            String repeatPattern = String.join(",", event.getRepeatDate().toArray(new String[0]));
            String jointData = event.getDate()+"∂"+event.getStartTime()+"∂"+event.getEndTime()+"∂"+repeatPattern+"∂"+event.getStressLevel()+"∂"+event.getDynamic();
            bw.write(jointData);
        }
        bw.close();
    }
}
