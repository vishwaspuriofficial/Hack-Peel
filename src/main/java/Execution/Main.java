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
        gui = new MainFrame();
//        saveEvent();
//        saveRepeatedEvent();
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
        while (line!= null) {
            if (line.charAt(0)=='#') {
                plannedDatesData.put(line.replace("#", ""), new LinkedList<>());
                currentDate = line.replace("#", "");
            } else {
                String[] attributes = line.split("∂");
                ArrayList<String> attr3 = new ArrayList<>(Arrays.asList(attributes[3].split(",")));
                plannedDatesData.get(currentDate).add(new Event(attributes[0], line.replace("#", ""), attributes[1], attributes[2], attr3, Integer.parseInt(attributes[4]), Boolean.parseBoolean(attributes[5])));
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
                String eventName = line.split("# ")[0];
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
        //Deletes original content of file
        ArrayList <String> toSave = new ArrayList<>();

        for (String key : plannedDatesData.keySet()) {
            toSave.add("#" + key);
            for (Event event : plannedDatesData.get(key)) {
                String[] repeatDates = event.getRepeatDate().toArray(new String[0]);
                String repeat = String.join(",", repeatDates);
                toSave.add(toSave.size()-1,"\n" + event.getTitle() + "∂" + event.getStartTime() + "∂" + event.getEndTime() + "∂" + repeat + "∂" + event.getStressLevel() + "∂" + event.getDynamic() + "\n");
            }
        }

                PrintWriter pw = new PrintWriter(new FileOutputStream("src/main/java/Databases/mainsave.txt", false));
//                //Overwrites the content
                FileWriter file = new FileWriter("src/main/java/Databases/mainsave.txt", true);
                PrintWriter write = new PrintWriter(file);
                for (String e : toSave) {
                    write.print(e);

                }
                write.close();
//
//
//                write.print("\n" + event.getTitle() + "∂" + event.getStartTime() + "∂" + event.getEndTime() + "∂" + repeat + "∂" + event.getStressLevel() + "∂" + event.getDynamic() + "\n");
//
//                write.close();
    }


    public static void saveRepeatedEvent() throws IOException {

        for (Event event : repeatingEvents) {

            //Deletes original content of file
            PrintWriter pw = new PrintWriter(new FileOutputStream("src/main/java/Databases/repeat.txt", false));

            //Overwrites the content
            FileWriter file = new FileWriter("src/main/java/Databases/repeat.txt", true);
            PrintWriter write = new PrintWriter(file);

            write.print("#" + event.getTitle());


            String[] repeatDates = event.getRepeatDate().toArray(new String[0]);
            String repeat = String.join(",", repeatDates);

            write.print("\n" + event.getDate() + "∂" + event.getStartTime() + "∂" + event.getEndTime() + "∂" + repeat + "∂" + event.getStressLevel() + "∂" + event.getDynamic() + "\n");

            write.close();
        }
    }




}
