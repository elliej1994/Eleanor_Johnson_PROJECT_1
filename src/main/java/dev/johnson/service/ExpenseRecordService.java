package dev.johnson.service;

import dev.johnson.entities.ExpenseRecord;

import java.util.List;

public interface ExpenseRecordService {

    public ExpenseRecord registerExpenseRecord (ExpenseRecord expenseRecord);
    List<ExpenseRecord> expenseList();
}
