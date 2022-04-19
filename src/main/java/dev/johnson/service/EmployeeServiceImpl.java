package dev.johnson.service;

import dev.johnson.data.EmployeeDao;
import dev.johnson.data.EmployeeDaoImpl;
import dev.johnson.entities.Employee;

import java.util.List;

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

    @Override
    public List<Employee> employeeList() {
        return this.employeeDao.getAllEmployees();
    }

    @Override
    public Employee retrieveEmployeeById(int eId) {
        return this.employeeDao.getEmployee(eId);
    }
}
