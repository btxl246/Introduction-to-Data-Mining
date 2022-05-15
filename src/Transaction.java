import java.util.ArrayList;

/**
 * Transaction object containing InvoiceNo and StockCodes
 */
public class Transaction {
    private String id;
    private ArrayList<String> codeList;

    /**
     * Empty constructor
     */
    public Transaction() {
    }

    /**
     * Constructor
     * @param id InvoiceNo
     * @param codeList ArrayList of StockCodes
     */
    public Transaction(String id, ArrayList<String> codeList) {
        this.id = id;
        this.codeList = codeList;
    }

    /**
     * Constructor
     * Inserts StockCode into ArrayList
     * @param id InvoiceNo
     * @param code A StockCode
     */
    public Transaction(String id, String code) {
        this.id = id;
        this.codeList = new ArrayList<>();
        this.codeList.add(code);
    }

    /**
     * Getter method
     * @return InvoiceNo
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method
     * @param id InvoiceNo
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter method
     * @return StockCodes
     */
    public ArrayList<String> getCodeList() {
        return codeList;
    }

    /**
     * Setter method
     * @param codeList StockCodes
     */
    public void setCodeList(ArrayList<String> codeList) {
        this.codeList = codeList;
    }

    /**
     * Creates a separated String of StockCodes
     * @return String
     */
    public String getCodeListString() {
        if (this.codeList.size() == 1) {
            return this.codeList.get(0);
        }
        else {  // ; separated values
            StringBuilder sb = new StringBuilder();

            for (String str : this.codeList) {
                sb.append(str).append("; ");
            }

            return String.valueOf(sb);
        }
    }

    /**
     * toString method
     * @return String
     */
    @Override
    public String toString() {
        return "InvoiceNo=" + id +
                ", StockCode=" + codeList;
    }
}
