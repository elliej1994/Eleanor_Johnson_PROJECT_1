package dev.johnson.test.daotests;


import dev.johnson.data.EmployeeDao;
import dev.johnson.data.EmployeeDaoImpl;
import dev.johnson.entities.Employee;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeedaoTest {
    static EmployeeDao employeeDao = new EmployeeDaoImpl();
    static Employee testEmployee = null;

    @Test
    @Order(1)
    public void create_employee_test(){

        Employee sample = new Employee(0,"Ja","Moe","Sales");
        Employee saved = employeeDao.createEmployee(sample);
        EmployeedaoTest.testEmployee = saved;
        Assertions.assertEquals(testEmployee.geteId(),saved.geteId());

    }
    @Test
    @Order(2)
    public void get_employee_byId_test(){
        Employee fetchedEmployee = employeeDao.getEmployee(testEmployee.geteId());
        System.out.println(fetchedEmployee);
        Assertions.assertEquals(fetchedEmployee.getfName(), testEmployee.getfName());
    }
   @Test
    @Order(3)
    public void update_employee_test(){
        EmployeedaoTest.testEmployee.setfName("Lorraine");
        employeeDao.updateEmployee(testEmployee);
        Employee fetchedEmployee = employeeDao.getEmployee(testEmployee.geteId());
       System.out.println(fetchedEmployee);
        Assertions.assertEquals(testEmployee.getfName(),fetchedEmployee.getfName());
   }


}
