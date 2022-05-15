import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TransactionList {
    private ArrayList<Transaction> list;

    public TransactionList() {
        this.list = new ArrayList<>();
    }

    public TransactionList(ArrayList<Transaction> list) {
        this.list = list;
    }

    public ArrayList<Transaction> getList() {
        return list;
    }

    public void setList(ArrayList<Transaction> list) {
        this.list = list;
    }

    public ArrayList<String> getIds() {
        ArrayList<String> ids = new ArrayList<>();
        for (Transaction t : list) {
            ids.add(t.getId());
        }
        return ids;
    }

    public ArrayList<ArrayList<String>> getCodes() {
        ArrayList<ArrayList<String>> codes = new ArrayList<>();
        for (Transaction t : list) {
            codes.add(t.getCodeList());
        }
        return codes;
    }

    /**
     * Create a list without grouping StockCodes into one InvoiceNo
     * @param inputCSV
     * @return
     */
    public ArrayList<Transaction> createUngroupedList(String inputCSV) {
        try {
            FileReader inputFile = new FileReader(inputCSV);
            BufferedReader br = new BufferedReader(inputFile);
            String line = "";
            String[] tempArr;
            String[] header = br.readLine().split(",");
            int x = Arrays.asList(header).indexOf("InvoiceNo");
            int y = Arrays.asList(header).indexOf("StockCode");

            this.list = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                tempArr = line.split(",");
                String id = tempArr[x];
                String code = tempArr[y];
                this.list.add(new Transaction(id, code));
            }

            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        return list;
    }

    /**
     * Create a list by grouping StockCodes into one InvoiceNo
     * @param inputCSV
     * @return
     */
    public ArrayList<Transaction> createGroupedList(String inputCSV) {
        try {
            FileReader inputFile = new FileReader(inputCSV);
            BufferedReader br = new BufferedReader(inputFile);
            String line = "";
            String[] tempArr;
            String[] header = br.readLine().split(",");
            int x = Arrays.asList(header).indexOf("InvoiceNo");
            int y = Arrays.asList(header).indexOf("StockCode");

            this.list = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                tempArr = line.split(",");
                String id = tempArr[x];
                String code = tempArr[y];
                if (this.list.isEmpty()) {  // Empty list
                    this.list.add(new Transaction(id, code));
                }
                else {
                    int a = this.getIds().indexOf(id);
                    if (a == -1) {  // Not found
                        this.list.add(new Transaction(id, code));   // New transaction
                    }
                    else {
                        Transaction transaction = this.list.get(a);
                        int b = transaction.getCodeList().indexOf(code);
                        if (b == -1) {  // Not found
                            transaction.getCodeList().add(code);    // Add code to existing codeList
                        }
                    }
                }
            }

            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

        return list;
    }

    public void saveListToFile(String outputCSV) {
        try {
            FileWriter outputFile = new FileWriter(outputCSV);
            CSVWriter writer = new CSVWriter(outputFile, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

            for (Transaction t : this.list) {
                writer.writeNext(new String[]{t.getId(), t.getCodeListString()});
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Transaction t : this.list) {
            sb.append(t.toString()).append("\n");
        }

        return String.valueOf(sb);
    }
}
