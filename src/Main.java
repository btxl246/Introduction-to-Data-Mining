/**
 * Main class
 */
public class Main {
    public static void main(String[] args) {
        CSVProcessor cp = new CSVProcessor();
        cp.createCSV("./files/data.csv", "./files/processed_data.csv");
        
        ARFFProcessor ap = new ARFFProcessor();
        ap.createARFF("./files/processed_data.csv", "./files/processed_data.arff");

        TransactionList tl = new TransactionList();
        tl.createGroupedList("./files/processed_data.csv");
        tl.saveListToFile("./files/processed_data_grouped_list.csv");
    }
}
