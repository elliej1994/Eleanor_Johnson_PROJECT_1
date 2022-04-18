package dev.johnson.daotests;


import dev.johnson.data.EmployeeDao;
import dev.johnson.data.EmployeeDaoImpl;
import dev.johnson.entities.Employee;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class employeedaotest {
    static EmployeeDao employeeDao = new EmployeeDaoImpl();
    static Employee testEmployee = null;

    @Test
    @Order(1)
    public void create_account_test(){

        Employee sample = new Employee(0,"Jane","Doe","444-444-4444","Sales","Regional Lead",50);
        Employee saved = employeeDao.createEmployee(sample);
        employeedaotest.testEmployee = saved;
        Assertions.assertNotEquals(0,saved.geteId());

    }

}
