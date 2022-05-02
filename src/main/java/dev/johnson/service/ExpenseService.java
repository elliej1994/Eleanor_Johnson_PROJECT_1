package dev.johnson.service;

import dev.johnson.entities.Expense;

import java.util.List;

public interface ExpenseService {

    public Expense registerExpense(Expense expense);
    List<Expense> expenseList();
   public Expense retrieveExpensesByNo(int recordNo);
   public Expense retrieveExpenseByeId(int eId);
   public Expense replaceExpense(Expense expense);
   public Expense approveExpense(Expense expense);
   public Expense denyExpense(Expense expense);
   public boolean destroyExpense(int recordNo);

}
