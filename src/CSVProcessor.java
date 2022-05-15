import java.io.*;
import java.util.Arrays;
import java.util.Locale;

import com.opencsv.CSVWriter;

/**
 * Processes data.csv
 */
public class CSVProcessor {

    /**
     * Empty constructor
     */
    public CSVProcessor() {
    }

    /**
     * Processes a .csv file and export
     * Skips row if InvoiceNo contains A or C, Description is empty, Quantity <= 0, UnitPrice <= 0
     * Replaces single quotes, double quotes, and commas
     * @param inputCSV fileName
     * @param outputCSV fileName
     */
    public void createCSV(String inputCSV, String outputCSV) {
        try {
            FileReader inputFile = new FileReader(inputCSV);
            BufferedReader br = new BufferedReader(inputFile);
            String line = "";
            String[] tempArr;
            String[] header = br.readLine().split(",");
            int x = Arrays.asList(header).indexOf("InvoiceNo");
            int y = Arrays.asList(header).indexOf("Description");
            int a = Arrays.asList(header).indexOf("Quantity");
            int b = Arrays.asList(header).indexOf("UnitPrice");

            FileWriter outputFile = new FileWriter(outputCSV);
            CSVWriter writer = new CSVWriter(outputFile, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            writer.writeNext(header);

            while ((line = br.readLine()) != null) {
                tempArr = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // Do not split between double quotes

                if (tempArr[x].contains("A")    // InvoiceNo contains A
                        || tempArr[x].contains("C")     // InvoiceNo contains C
                        || (tempArr[y].length() == 0)   // Empty description
                        || (Float.parseFloat(tempArr[a]) <= 0)  // Quantity <= 0
                        || (Float.parseFloat(tempArr[b]) <= 0)) {   // UnitPrice <= 0
                    continue;   // Skip this iteration
                }

                tempArr[y] = tempArr[y].toLowerCase(Locale.ROOT);

                for (int i = 0; i < tempArr.length; i++) {
                    tempArr[i] = tempArr[i].replaceAll("'","");
                    // System.out.print(tempArr[i]);
                    tempArr[i] = tempArr[i].replaceAll("\"","");
                    // System.out.print(tempArr[i]);
                    tempArr[i] = tempArr[i].replaceAll(","," ");
                    // System.out.print(tempArr[i]);
                    // System.out.println();
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
