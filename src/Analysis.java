import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * Makes charts and statistics
 */
public class Analysis {
    public static final String INVOICEDATE = "InvoiceDate";
    private List<List<String>> list;

    public Analysis() {
    }

    /**
     * Turns a .csv file into a list
     * @param inputCSV fileName
     */
    public void loadCSV(String inputCSV) {
        this.list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputCSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                this.list.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Counts frequency of values in a column
     * @param column Name of column
     * @return HashMap of the values and matching the counts
     */
    public HashMap<String, Integer> countValues(String column) {
        int index = this.list.get(0).indexOf(column);   // Get index of column

        List<String> strings = new ArrayList<>();

        if (!column.equals(Analysis.INVOICEDATE)) {
            for (int i = 1; i < this.list.size(); i++) {    // Skip header
                strings.add(this.list.get(i).get(index));   // Add values into a list
            }
        }
        else {
            for (int i = 1; i < this.list.size(); i++) {    // Skip header
                String[] date = this.list.get(i).get(index).split(" ");     // Split between day and hour
                String[] year = date[0].split("/");     // Get year from date array
                strings.add(year[2]);   // Add values into a list
            }
        }

        Set<String> distinct = new HashSet<>(strings);  // Get unique values in the column

        HashMap<String, Integer> map = new HashMap<>();     // Value, frequency

        for (String str : distinct) {
            // System.out.println(str + ": " + Collections.frequency(strings, str));
            map.put(str, Collections.frequency(strings, str));
        }

        return map;
    }

    /**
     * Gets the value with max frequency
     * @param column Name of column
     * @return String
     */
    public String maxFrequency(String column) {
        HashMap<String, Integer> map = this.countValues(column);    // Count
        Map.Entry<String, Integer> maxEntry = Collections.max(map.entrySet(), Map.Entry.comparingByValue());    // Get max
        System.out.println(column + " with max frequency: " + maxEntry);

        return String.valueOf(maxEntry);
    }

    /**
     * Gets the value with min frequency
     * @param column Name of column
     * @return String
     */
    public String minFrequency(String column) {
        HashMap<String, Integer> map = this.countValues(column);    // Count
        Map.Entry<String, Integer> minEntry = Collections.min(map.entrySet(), Map.Entry.comparingByValue());    // Get min
        System.out.println(column + " with max frequency: " + minEntry);

        return String.valueOf(minEntry);
    }

    /**
     * Creates a bar chart for a given column
     * @param column Name of column
     */
    public void createBarChart(String column) {
        try {
            SwingUtilities.invokeAndWait(() -> {
                BarChart example = new BarChart("Bar Chart Window", column, "Count", this.countValues(column));
                example.setSize(1500, 700);
                example.setLocationRelativeTo(null);
                example.setDefaultCloseOperation(EXIT_ON_CLOSE);
                example.setVisible(true);
            });
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.loadCSV("./files/processed_data.csv");
        analysis.createBarChart("Country");
        analysis.maxFrequency("Country");
        analysis.minFrequency("Country");
    }
}
