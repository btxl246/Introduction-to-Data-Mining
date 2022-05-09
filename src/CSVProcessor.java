import java.io.*;
import com.opencsv.CSVWriter;
public class CSVProcessor {
    public static void main(String[] args) {
        try {
            FileReader inputFile = new FileReader("./data.csv");
            BufferedReader br = new BufferedReader(inputFile);
            String line = "";
            String[] tempArr;

            FileWriter outputFile = new FileWriter("./new_data.csv");
            CSVWriter writer = new CSVWriter(outputFile, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            while ((line = br.readLine()) != null) {
                tempArr = line.split(",");
                for (int i = 0; i < tempArr.length; i++) {
                    tempArr[i] = tempArr[i].replaceAll("'","_");
                    tempArr[i] = tempArr[i].replaceAll("\"","__");
                    System.out.print(tempArr[i] + " ");
                }
                writer.writeNext(tempArr);
                System.out.println();
            }
            br.close();
            writer.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
