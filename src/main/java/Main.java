import java.io.File;
import java.util.LinkedList;
import java.io.*;

public class Main {

    public static void main(String[] agrs)  {
    }
    LinkedList<String> dates;
    String path = "../Databases/mainsave.txt";
    public LinkedList<String> loadDates() throws IOException {
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));

        int r=0;
        while((r=br.read())!=-1) {
            dates.add(br.readLine());
        }
        return dates;


    }

}
