import java.io.File;
import java.util.LinkedList;
import java.io.*;

public class Main {

    public static void main(String[] agrs)  {
    }

    public LinkedList<String> loadSingleDays() throws IOException {
        LinkedList<String> dates = null;
        String path = "../Databases/mainsave.txt";
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));

        int r=0;
        while((r=br.read())!=-1) {
            dates.add(br.readLine());
        }
        return dates;
    }

    public LinkedList<String> loadRepeatedDays() throws IOException {
        LinkedList<String> dates = null;
        String path = "../Databases/repeat.txt";
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));

        int r=0;
        while((r=br.read())!=-1) {
            dates.add(br.readLine());
        }
        return dates;
    }

}
