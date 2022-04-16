package dev.johnson.entities;

public class ExpenseRecord {

    private int recordNo;
    private int eID;
    private String expenseType;
    private String item;
    private double amount;
    private String status;


    public ExpenseRecord(){}

    public ExpenseRecord(int recordNo, int eID, String expenseType, String item, double amount, String status) {
        this.recordNo = recordNo;
        this.eID = eID;
        this.expenseType = expenseType;
        this.item = item;
        this.amount = amount;
        this.status = status;
    }

    public int getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(int recordNo) {
        this.recordNo = recordNo;
    }

    public int geteID() {
        return eID;
    }

    public void seteID(int eID) {
        this.eID = eID;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "expenseRecord{" +
                "recordNo=" + recordNo +
                ", eID=" + eID +
                ", expenseType='" + expenseType + '\'' +
                ", item='" + item + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }


}
