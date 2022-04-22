package dev.johnson.service;

import dev.johnson.entities.ExpenseRecord;

import java.util.List;

public interface ExpenseRecordService {

    public ExpenseRecord registerExpenseRecord (ExpenseRecord expenseRecord);
    List<ExpenseRecord> expenseList();
   public ExpenseRecord retrieveExpensesByNo(int recordNo);
   public ExpenseRecord retrieveExpenseByeId(int eId);
   public ExpenseRecord replaceExpenseRecord(ExpenseRecord expenseRecord);
   public ExpenseRecord approveExpense(ExpenseRecord expenseRecord);
   public ExpenseRecord denyExpense(ExpenseRecord expenseRecord);
   public boolean destroyExpense(int recordNo);
}
