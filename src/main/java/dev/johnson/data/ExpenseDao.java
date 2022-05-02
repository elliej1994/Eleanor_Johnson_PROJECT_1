package dev.johnson.data;

import dev.johnson.entities.Expense;

import java.util.List;

public interface ExpenseDao {

    public Expense createExpense(Expense expense);
    public Expense getExpense(int recordNo);
    public Expense getExpenseById(int eId);
    List<Expense> listExpense();
    public Expense updateExpense(Expense expense);
    public boolean deleteExpense(int recordNo);

}
