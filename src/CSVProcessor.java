import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.opencsv.CSVWriter;

/**
 * Processes data.csv
 */
public class CSVProcessor {
    private List<List<String>> list;

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

    /**
     * Turns a .csv file into a List
     * @param inputCSV fileName
     */
    public List<List<String>> loadCSV(String inputCSV) {
        List<List<String>> list = new ArrayList<>();

        try (
            BufferedReader br = new BufferedReader(new FileReader(inputCSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                list.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.list = list;

        return list;
    }

    /**
     * Removes a column from List
     * @param column Name of column
     * @return List
     */
    public List<List<String>> removeColumn(String column) {
        List<List<String>> list = new ArrayList<>();
        int index = this.list.get(0).indexOf(column);   // Get index from name
        for (int i = 0; i < this.list.size(); i++) {    // Every row
            List<String> strings = new ArrayList<>();
            for (int j = 0; j < this.list.get(i).size(); j++) {     // Every string
                if (j == index) {   // Skip if index
                    continue;
                }
                strings.add(this.list.get(i).get(j));   // Add string
            }
            list.add(strings);  // Add row without column
        }

        this.list = list;

        return list;
    }

    /**
     * Exports List to a .csv file
     * @param outputCSV
     */
    public void saveListToFile(String outputCSV) {
        try {
            FileWriter outputFile = new FileWriter(outputCSV);
            CSVWriter writer = new CSVWriter(outputFile, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            for (List<String> strings : this.list) {
                String[] arr = new String[strings.size()];  // Turn List to Array
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = strings.get(i);
                }
                writer.writeNext(arr);   // InvoiceNo, [StockCodes]
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
