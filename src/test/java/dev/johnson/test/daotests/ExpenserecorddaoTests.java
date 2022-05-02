package dev.johnson.test.daotests;


import dev.johnson.data.ExpenseDao;
import dev.johnson.data.ExpenseDaoImpl;
import dev.johnson.entities.Expense;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenserecorddaoTests {

    static ExpenseDao expenseDao = new ExpenseDaoImpl();
    static Expense testRecord = null;



        @Test
        @Order(1)
        public void create_expense_record_test(){
            Expense sample = new Expense(0,1,"Travel",157.65,"Pending");
            Expense saved = expenseDao.createExpense(sample);
            ExpenserecorddaoTests.testRecord = saved;
            Assertions.assertNotEquals(0,saved.getRecordNo());

        }

    @Test
    @Order(2)
    public void get_expenseRecord_byRecordNo_test(){
        Expense fetchedRecord = expenseDao.getExpense(testRecord.getRecordNo());
        System.out.println(fetchedRecord);
        Assertions.assertEquals(fetchedRecord.getRecordNo(), testRecord.getRecordNo());

    }


}
