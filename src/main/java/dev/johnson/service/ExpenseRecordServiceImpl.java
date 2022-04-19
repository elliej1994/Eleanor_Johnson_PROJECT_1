package dev.johnson.service;

import dev.johnson.data.ExpenseRecordDao;
import dev.johnson.data.ExpenseRecordDaoImpl;
import dev.johnson.entities.ExpenseRecord;

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
}
