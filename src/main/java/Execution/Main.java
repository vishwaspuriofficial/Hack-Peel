package Execution;

import Scripts.Event;
import UserInterface.MainFrame;
import java.io.File;
import java.util.ArrayList;
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
                //FIXME: ask shaya about the arraylist and linkedlist
//                Arraylist<String> attr5 =
                plannedDatesData.get(currentDate).add(new Event(attributes[0], line.replace("#", ""), attributes[1], attributes[2], new ArrayList<>(), new LinkedList<>(), Integer.parseInt(attributes[5])));
            }
        }
    }

    public LinkedList<String> loadRepeatedDays() throws IOException {
        LinkedList<String> dates = null;
        String path = "../Databases/repeat.txt";
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));

        int r = 0;
        while ((r = br.read()) != -1) {
            dates.add(br.readLine());
        }
        return dates;
    }
}
