package dev.johnson.data;

import dev.johnson.entities.ExpenseRecord;

import java.util.List;

public interface ExpenseRecordDao {

    public ExpenseRecord createExpenseRecord(ExpenseRecord expenseRecord);
    public ExpenseRecord getExpenseRecord(int recordNo);
    public ExpenseRecord getExpenseRecordeId(int eId);
    List<ExpenseRecord> listExpenseRecord();
    public ExpenseRecord updateExpenseRecord(ExpenseRecord expenseRecord);
    public boolean deleteExpenseRecord(int recordNo);

}
