package dev.johnson.daotests;


import dev.johnson.data.EmployeeDao;
import dev.johnson.data.EmployeeDaoImpl;
import dev.johnson.data.ExpenseRecordDao;
import dev.johnson.data.ExpenseRecordDaoImpl;
import dev.johnson.entities.Employee;
import dev.johnson.entities.ExpenseRecord;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class expenserecorddaotests {

    static ExpenseRecordDao expenseRecordDao = new ExpenseRecordDaoImpl();
    static ExpenseRecord testRecord = null;



        @Test
        @Order(1)
        public void create_expense_record_test(){
            ExpenseRecord sample = new ExpenseRecord(0,1,"Travel",157.65,"Pending");
            ExpenseRecord saved = expenseRecordDao.createExpenseRecord(sample);
            expenserecorddaotests.testRecord = saved;
            Assertions.assertNotEquals(0,saved.getRecordNo());

        }

    @Test
    @Order(2)
    public void get_expenseRecord_byRecordNo_test(){
        ExpenseRecord fetchedRecord = expenseRecordDao.getExpenseRecord(testRecord.getRecordNo());
        System.out.println(fetchedRecord);
        Assertions.assertEquals(fetchedRecord.getRecordNo(), testRecord.getRecordNo());

    }
}
