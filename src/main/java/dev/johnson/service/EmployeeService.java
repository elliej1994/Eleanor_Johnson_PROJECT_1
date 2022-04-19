package dev.johnson.service;

import dev.johnson.entities.Employee;

import java.util.List;

public interface EmployeeService {

    public Employee registerEmployee(Employee employee);
    List<Employee> employeeList ();
    public Employee retrieveEmployeeById(int eId);




}
