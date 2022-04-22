package dev.johnson.service;

import dev.johnson.data.EmployeeDao;
import dev.johnson.data.EmployeeDaoImpl;
import dev.johnson.entities.Employee;
import dev.johnson.utilities.LogLevel;
import dev.johnson.utilities.Logger;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDaoImpl employeesDao) {
    this.employeeDao = employeesDao;
    }

    @Override
    public Employee registerEmployee(Employee employees){
        Employee employee = this.employeeDao.createEmployee(employees);
        Logger.log("The employee with eId "+ employee.geteId()+" was created.", LogLevel.INFO);
        return employee;
    }

    @Override
    public List<Employee> employeeList() {
        return this.employeeDao.getAllEmployees();
    }

    @Override
    public Employee retrieveEmployeeById(int eId) {
        return this.employeeDao.getEmployee(eId);
    }

    @Override
    public Employee replaceEmployee(Employee employee) {
        Logger.log("The employee information was updated",LogLevel.INFO);
        return this.employeeDao.updateEmployee(employee);
    }

    @Override
    public boolean destroyEmployee(int eId) {
        return this.employeeDao.deleteEmployee(eId);
    }

  /*  @Override
    public boolean canDelete(int eId) {
    List<ExpenseRecordService>

        return false;
    }*/
}
