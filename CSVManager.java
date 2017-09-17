import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CSVManager {

    public static ArrayList<String> loadLines(String fileName) {
        BufferedReader br = null;
        ArrayList<String> lines = new ArrayList<String>();

        try {
            br = new BufferedReader(new FileReader(fileName));

            String newLine;
            int index = 1;

            while((newLine = br.readLine()) != null) {
                String line = index + "," + newLine;
                lines.add(line);
                index++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("nie znaleziono pliku!");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("IOException");
        }
        return lines;
    }

    public static void main(String args[]){
        ArrayList<String> lines = loadLines("applicants.csv");
        for (String line : lines){
            System.out.println(line);
        }
    }
}
