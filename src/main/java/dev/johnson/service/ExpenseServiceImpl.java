package dev.johnson.service;

import dev.johnson.data.ExpenseDao;
import dev.johnson.data.ExpenseDaoImpl;
import dev.johnson.entities.Expense;
import dev.johnson.exceptions.CannotEditException;
import dev.johnson.utilities.LogLevel;
import dev.johnson.utilities.Logger;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService {
    private ExpenseDao expenseDao;

    public ExpenseServiceImpl(ExpenseDaoImpl expenseRecordDao) {
        this.expenseDao = expenseRecordDao;
    }

    @Override
    public Expense registerExpense(Expense expense) {
       Expense expense1 = this.expenseDao.createExpense(expense);
       return expense1;

    }

    @Override
    public List<Expense> expenseList() {
        return this.expenseDao.listExpense();
    }

    @Override
    public Expense retrieveExpensesByNo(int recordNo) {
        return this.expenseDao.getExpense(recordNo);
    }

    @Override
    public Expense retrieveExpenseByeId(int eId) {
        return this.expenseDao.getExpenseById(eId);
    }

    @Override
    public Expense replaceExpense(Expense expense) {
        Logger.log("The record "+ expense.getRecordNo() + " was updated", LogLevel.INFO);
        return this.expenseDao.updateExpense(expense);
    }

    @Override
    public Expense approveExpense(Expense expense) {
        expense.setStatus("Approved");
        return this.expenseDao.updateExpense(expense);
    }

    @Override
    public Expense denyExpense(Expense expense) {
        expense.setStatus("Denied");
        return this.expenseDao.updateExpense(expense);
    }

    @Override
    public boolean destroyExpense(int recordNo) {
        return this.expenseDao.deleteExpense(recordNo);
    }




}
