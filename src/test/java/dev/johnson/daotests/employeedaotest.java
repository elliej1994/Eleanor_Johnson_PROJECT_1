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
    public void create_employee_test(){

        Employee sample = new Employee(0,"Ja","Moe","Sales");
        Employee saved = employeeDao.createEmployee(sample);
        employeedaotest.testEmployee = saved;
        Assertions.assertNotEquals(0,saved.geteId());

    }
    @Test
    @Order(2)
    public void get_employee_byId_test(){
        Employee fetchedEmployee = employeeDao.getEmployee(testEmployee.geteId());
        System.out.println(fetchedEmployee);
        Assertions.assertEquals(fetchedEmployee.getfName(), testEmployee.getfName());

    }
}
