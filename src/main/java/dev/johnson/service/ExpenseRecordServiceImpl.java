package dev.johnson.service;

import dev.johnson.data.ExpenseRecordDao;
import dev.johnson.data.ExpenseRecordDaoImpl;
import dev.johnson.entities.ExpenseRecord;
import dev.johnson.utilities.LogLevel;
import dev.johnson.utilities.Logger;

import java.util.List;

public class ExpenseRecordServiceImpl implements  ExpenseRecordService{
    private ExpenseRecordDao expenseRecordDao;

    public ExpenseRecordServiceImpl(ExpenseRecordDaoImpl expenseRecordDao) {
        this.expenseRecordDao = expenseRecordDao;
    }

    @Override
    public ExpenseRecord registerExpenseRecord(ExpenseRecord expenseRecord) {
       ExpenseRecord expenseRecord1 = this.expenseRecordDao.createExpenseRecord(expenseRecord);
       return expenseRecord1;

    }

    @Override
    public List<ExpenseRecord> expenseList() {
        return this.expenseRecordDao.listExpenseRecord();
    }

    @Override
    public ExpenseRecord retrieveExpensesByNo(int recordNo) {
        return this.expenseRecordDao.getExpenseRecord(recordNo);
    }

    @Override
    public ExpenseRecord retrieveExpenseByeId(int eId) {
        return this.expenseRecordDao.getExpenseRecordeId(eId);
    }

    @Override
    public ExpenseRecord replaceExpenseRecord(ExpenseRecord expenseRecord) {
        Logger.log("The record "+ expenseRecord.getRecordNo() + " was updated", LogLevel.INFO);
        return this.expenseRecordDao.updateExpenseRecord(expenseRecord);
    }

    @Override
    public ExpenseRecord approveExpense(ExpenseRecord expenseRecord) {
        expenseRecord.setStatus("Approved");
        return this.expenseRecordDao.updateExpenseRecord(expenseRecord);
    }

    @Override
    public ExpenseRecord denyExpense(ExpenseRecord expenseRecord) {
        expenseRecord.setStatus("Denied");
        return this.expenseRecordDao.updateExpenseRecord(expenseRecord);
    }

    @Override
    public boolean destroyExpense(int recordNo) {
        return this.expenseRecordDao.deleteExpenseRecord(recordNo);
    }


}
