package Execution;

import Scripts.Event;
import UserInterface.MainFrame;
import java.io.File;
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
        Event test = new Event("Gym","30/04/2022","10:00","10:30",new ArrayList<>(Arrays.asList("1","2")),new ArrayList<>(Arrays.asList("Shower")),4);
        //saveEvent(test);
        //saveRepeatedEvent(test);
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
                plannedDatesData.put(line, new LinkedList<>());
                currentDate = line;
            } else {
                String[] attributes = line.split("∂");
                ArrayList<String> attr3 = new ArrayList<>(Arrays.asList(attributes[3].split(",")));
                ArrayList<String> attr4 = new ArrayList<>(Arrays.asList(attributes[4].split(",")));
                plannedDatesData.get(currentDate).add(new Event(attributes[0], line.replace("#", ""), attributes[1], attributes[2], attr3, attr4, Integer.parseInt(attributes[5])));
            }
            line = br.readLine();
        }

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
                String[] link = data[4].split(",");
                ArrayList<String> linkedEvents = new ArrayList<>(Arrays.asList(link));
                Event event = new Event(eventName,data[0],data[1],data[2],repeatedDays,linkedEvents,Integer.parseInt(data[5]));
                repeatingEvents.add(event);
            }
            line = br.readLine();
        }
    }
    public static void saveEvent(Event event) throws IOException {

        //Deletes original content of file
        PrintWriter pw = new PrintWriter(new FileOutputStream("src/main/java/Databases/mainsave.txt", false));

        //Overwrites the content
        FileWriter file = new FileWriter("src/main/java/Databases/mainsave.txt",true);
        PrintWriter write = new PrintWriter(file);
        write.print("#"+event.getDate());

        String[] repeatDates = event.getRepeatDate().toArray(new String[0]);
        String repeat = String.join(",", repeatDates);

        String[] linkedEvents = event.getLinkedEvents().toArray(new String[0]);
        String events = String.join(",", linkedEvents);

        write.print("\n"+event.getTitle()+"∂"+event.getStartTime()+"∂"+event.getEndTime()+"∂"+repeat+"∂"+events+"∂"+event.getStressLevel()+"\n");

        write.close();
    }


    public static void saveRepeatedEvent(Event event) throws IOException {
        //Deletes original content of file
        PrintWriter pw = new PrintWriter(new FileOutputStream("src/main/java/Databases/repeat.txt", false));

        //Overwrites the content
        FileWriter file = new FileWriter("src/main/java/Databases/repeat.txt",true);
        PrintWriter write = new PrintWriter(file);

        write.print("#"+event.getTitle());


        String[] repeatDates = event.getRepeatDate().toArray(new String[0]);
        String repeat = String.join(",", repeatDates);

        String[] linkedEvents = event.getLinkedEvents().toArray(new String[0]);
        String events = String.join(",", linkedEvents);

        write.print("\n"+event.getDate()+"∂"+event.getStartTime()+"∂"+event.getEndTime()+"∂"+repeat+"∂"+events+"∂"+event.getStressLevel()+"\n");

        write.close();
    }


}
