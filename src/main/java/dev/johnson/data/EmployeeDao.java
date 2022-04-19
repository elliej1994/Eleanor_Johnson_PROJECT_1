package dev.johnson.data;

import dev.johnson.entities.Employee;

import java.util.List;

public interface EmployeeDao {
    public Employee createEmployee(Employee employee);
    public Employee getEmployee(int eId);
    List<Employee> getAllEmployees();

/*public Employee updateEmployee(Employee employee);*/



}
