package dev.johnson.daotests;


import dev.johnson.data.EmployeesDao;
import dev.johnson.data.EmployeesDaoImpl;
import dev.johnson.entities.Employees;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class employeesdaotest {
    static EmployeesDao employeesDao = new EmployeesDaoImpl();
    static Employees testEmployee = null;

    @Test
    @Order(1)
    public void create_account_test(){

        Employees sample = new Employees(0,"Jane","Doe","444-444-4444","Sales","Regional Lead",50);
        Employees saved = employeesDao.createEmployee(sample);
        employeesdaotest.testEmployee = saved;
        Assertions.assertNotEquals(0,saved.geteId());

    }

}
