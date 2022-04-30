import UserInterface.MainFrame;
import java.io.File;
import java.util.LinkedList;
import java.io.*;

public class Main {
    private static MainFrame gui;

    public static void main(String[] args) {
        gui = new MainFrame();
    }

    public static MainFrame getGui() {
        return gui;
    }

    public LinkedList<String> loadSingleDays() throws IOException {
        LinkedList<String> dates = null;
        String path = "../Databases/mainsave.txt";
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));

        int r = 0;
        while ((r = br.read()) != -1) {
            dates.add(br.readLine());
        }
        return dates;
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
