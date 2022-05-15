import java.util.ArrayList;

public class Transaction {
    private String id;
    private ArrayList<String> codeList;

    public Transaction() {
    }

    public Transaction(String id, ArrayList<String> codeList) {
        this.id = id;
        this.codeList = codeList;
    }

    public Transaction(String id, String code) {
        this.id = id;
        this.codeList = new ArrayList<>();
        this.codeList.add(code);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(ArrayList<String> codeList) {
        this.codeList = codeList;
    }

    public String getCodeListString() {
        if (this.codeList.size() == 1) {
            return this.codeList.get(0);
        }
        else {
            StringBuilder sb = new StringBuilder();

            for (String str : this.codeList) {
                sb.append(str).append("; ");
            }

            return String.valueOf(sb);
        }
    }

    @Override
    public String toString() {
        return "InvoiceNo=" + id +
                ", StockCode=" + codeList;
    }
}
