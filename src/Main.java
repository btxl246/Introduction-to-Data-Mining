import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        CSVProcessor cp = new CSVProcessor();
        cp.createCSV("./files/test.csv", "./files/new_test.csv");

        ARFFProcessor ap = new ARFFProcessor();
        ap.createARFF("./files/new_test.csv", "./files/new_test.arff");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        TransactionList tl = new TransactionList();
        System.out.println(dtf.format(now));
        tl.createUngroupedList("./files/new_test.csv");
        tl.saveListToFile("./files/new_test_ungrouped_list.csv");
        System.out.println(dtf.format(now));
        tl.createGroupedList("./files/new_test.csv");
        tl.saveListToFile("./files/new_test_grouped_list.csv");
        System.out.println(dtf.format(now));
    }
}
