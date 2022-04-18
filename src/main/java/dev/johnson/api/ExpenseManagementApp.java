package dev.johnson.api;

import com.google.gson.Gson;
import dev.johnson.data.EmployeeDaoImpl;
import dev.johnson.entities.Employee;
import dev.johnson.service.EmployeeService;
import dev.johnson.service.EmployeeServiceImpl;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

public class ExpenseManagementApp {
//todo inst list of records
    //todo inst list of employees
    static List<Employee> employeeList = new ArrayList<>();
    public static Gson gson = new Gson();
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDaoImpl());


    public static void main(String[] args) {


        Javalin app =  Javalin.create();

        app.post("/employee", context -> {
           String body = context.body();
           Employee employee = gson.fromJson(body, Employee.class);
            Employee employee1 = employeeService.registerEmployee(employee);
            context.status(201);
            String employeeJson = gson.toJson(employee1);
            context.result(employeeJson);
        });



    app.start(8080);
    }

}
