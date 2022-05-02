package dev.johnson.entities;

import java.util.Objects;

public class Expense {

private int recordNo;
private int eId;
private String expenseType;
private double amount;
private String status;



    public Expense(){}
    public Expense(int recordNo, int eId, String expenseType, double amount, String status) {
        this.recordNo = recordNo;
        this.eId = eId;
        this.expenseType = expenseType;
        this.amount = amount;
        this.status = status;
    }

    public int getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(int recordNo) {
        this.recordNo = recordNo;
    }

    public int geteId() {
        return eId;
    }

    public void seteId(int eId) {
        this.eId = eId;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
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
        return "ExpenseRecord{" +
                "recordNo=" + recordNo +
                ", eId=" + eId +
                ", expenseType='" + expenseType + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense that = (Expense) o;
        return recordNo == that.recordNo && eId == that.eId && Double.compare(that.amount, amount) == 0 && Objects.equals(expenseType, that.expenseType) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordNo, eId, expenseType, amount, status);
    }
}
