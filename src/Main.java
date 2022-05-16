/**
 * Main class
 */
public class Main {
    public static void main(String[] args) {
        CSVProcessor cp = new CSVProcessor();
        // Clean up original .csv
        cp.createCSV("./files/test.csv", "./files/new_test.csv");
        // Store .csv
        cp.loadCSV("./files/new_test.csv");
        // Remove column
        cp.removeColumn("Description");
        // Save to file
        cp.saveListToFile("./files/removed.csv");

        ARFFProcessor ap = new ARFFProcessor();
        // Turn .csv to .arff
        ap.createARFF("./files/new_test.csv", "./files/new_test.arff");

        TransactionList tl = new TransactionList();
        tl.createGroupedList("./files/processed_data.csv");
        tl.saveListToFile("./files/processed_data_grouped_list.csv");
    }
}
