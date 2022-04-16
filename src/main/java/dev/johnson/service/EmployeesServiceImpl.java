package dev.johnson.service;

import dev.johnson.data.EmployeesDao;
import dev.johnson.data.EmployeesDaoImpl;
import dev.johnson.entities.Employees;

public class EmployeesServiceImpl implements EmployeesService {

private EmployeesDao employeesDao;

    public EmployeesServiceImpl(EmployeesDaoImpl employeesDao) {
    this.employeesDao = employeesDao;
    }

    @Override
    public Employees registerEmployee(Employees employees){
        Employees employee = this.employeesDao.createEmployee(employees);
        return employee;
    }
}
