package Execution;

import Scripts.Event;
import UserInterface.MainFrame;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.io.*;

public class Main {
    private static MainFrame gui;
    private static HashMap<String, LinkedList<Event>> plannedDatesData = new HashMap<>();

    public static void main(String[] args) throws IOException {
        loadMainSave();
        gui = new MainFrame();
    }

    public static MainFrame getGui() {
        return gui;
    }

    public static HashMap<String, LinkedList<Event>> getPlannedDatesData() {
        return plannedDatesData;
    }

    public static void loadMainSave() throws IOException {
        File file = new File("src/main/java/Databases/mainsave.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));


        String line = br.readLine();
        while (line!= null) {
            String currentDate = "";
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

    public LinkedList<Event> loadRepeatedDays() throws IOException {
        LinkedList<Event> dates = null;
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
                dates.add(event);
            }
        }
        return dates;
    }
}
