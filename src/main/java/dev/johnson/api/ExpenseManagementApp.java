package dev.johnson.api;

import com.google.gson.Gson;
import dev.johnson.data.EmployeesDaoImpl;
import dev.johnson.entities.Employees;
import dev.johnson.service.EmployeesService;
import dev.johnson.service.EmployeesServiceImpl;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

public class ExpenseManagementApp {
//todo inst list of records
    //todo inst list of employees
    static List<Employees> employeesList = new ArrayList<>();
    public static Gson gson = new Gson();
    public static EmployeesService employeesService = new EmployeesServiceImpl(new EmployeesDaoImpl());

    public static void main(String[] args) {


        Javalin app =  Javalin.create();

        app.post("/employees", context -> {
           String body = context.body();
           Employees employees = gson.fromJson(body, Employees.class);
            Employees employees1 = employeesService.registerEmployee(employees);
            context.status(201);
            String employeeJson = gson.toJson(employees1);
            context.result(employeeJson);
        });



    app.start(8080);
    }

}
