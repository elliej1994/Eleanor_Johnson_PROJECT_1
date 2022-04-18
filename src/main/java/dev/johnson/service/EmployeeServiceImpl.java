package dev.johnson.service;

import dev.johnson.data.EmployeeDao;
import dev.johnson.data.EmployeeDaoImpl;
import dev.johnson.entities.Employee;

public class EmployeeServiceImpl implements EmployeeService {

private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDaoImpl employeesDao) {
    this.employeeDao = employeesDao;
    }

    @Override
    public Employee registerEmployee(Employee employees){
        Employee employee = this.employeeDao.createEmployee(employees);
        return employee;
    }
}
