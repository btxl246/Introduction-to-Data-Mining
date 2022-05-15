import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        CSVProcessor cp = new CSVProcessor();
        cp.createCSV("./files/data.csv", "./files/new_data.csv");
        ARFFProcessor ap = new ARFFProcessor();
        ap.createARFF("./files/new_data.csv", "./files/new_data.arff");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        TransactionList tl = new TransactionList();
        System.out.println(dtf.format(now));
        tl.createUngroupedList("./files/new_data.csv");
        tl.saveListToFile("./files/ungrouped_list.csv");
        System.out.println(dtf.format(now));
        tl.createGroupedList("./files/new_data.csv");
        tl.saveListToFile("./files/grouped_list.csv");
        System.out.println(dtf.format(now));
    }
}
