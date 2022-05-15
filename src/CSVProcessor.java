import java.io.*;
import com.opencsv.CSVWriter;

public class CSVProcessor {
    public CSVProcessor() {
    }

    public void createCSV(String inputCSV, String outputCSV) {
        try {
            FileReader inputFile = new FileReader(inputCSV);
            BufferedReader br = new BufferedReader(inputFile);
            String line = "";
            String[] tempArr;

            FileWriter outputFile = new FileWriter(outputCSV);
            CSVWriter writer = new CSVWriter(outputFile, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

            while ((line = br.readLine()) != null) {
                tempArr = line.split(",");

                for (int i = 0; i < tempArr.length; i++) {
                    tempArr[i] = tempArr[i].replaceAll("'","");
                    tempArr[i] = tempArr[i].replaceAll("\"","");
                }

                writer.writeNext(tempArr);
            }

            br.close();
            writer.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
