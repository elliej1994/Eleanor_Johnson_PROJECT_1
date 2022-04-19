package dev.johnson.api;

import com.google.gson.Gson;
import dev.johnson.data.EmployeeDaoImpl;
import dev.johnson.data.ExpenseRecordDao;
import dev.johnson.data.ExpenseRecordDaoImpl;
import dev.johnson.entities.Employee;
import dev.johnson.entities.ExpenseRecord;
import dev.johnson.exceptions.ResourceNotFound;
import dev.johnson.service.EmployeeService;
import dev.johnson.service.EmployeeServiceImpl;
import dev.johnson.service.ExpenseRecordService;
import dev.johnson.service.ExpenseRecordServiceImpl;
import dev.johnson.utilities.LogLevel;
import dev.johnson.utilities.Logger;
import io.javalin.Javalin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseManagementApp {
//todo inst list of records
    //todo inst list of employees
    static List<Employee> employeeList = new ArrayList<>();
    public static Gson gson = new Gson();
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDaoImpl());
    public static ExpenseRecordService expenseRecordService = new ExpenseRecordServiceImpl(new ExpenseRecordDaoImpl());


    public static void main(String[] args) {


        Javalin app =  Javalin.create();
/*
*
* employee routes
*
*
 */
        app.post("/employees", context -> {
           String body = context.body();
           Employee employee = gson.fromJson(body, Employee.class);
            Employee employee1 = employeeService.registerEmployee(employee);
            context.status(201);
            String employeeJson = gson.toJson(employee1);
            context.result(employeeJson);
        });


        app.get("/employees", context -> {
            List<Employee> employeeList = employeeService.employeeList();
            String employeeListJSON = gson.toJson(employeeList);
            context.result(employeeListJSON);
        });

        app.get("/employees/{eid}", context -> {
        int eId = Integer.parseInt(context.pathParam("eid"));
            try {
                String employeeJSON = gson.toJson(employeeService.retrieveEmployeeById(eId));

                context.result(employeeJSON);
            }catch(ResourceNotFound e){
                context.status(404);
                context.result("The employee with that id was not found.");

            }



        });



/*
*
*
*
* Expense records routes below
*
*
*
 */

        app.post("/expenserecords", context -> {
            String body = context.body();
            ExpenseRecord expenseRecord = gson.fromJson(body,ExpenseRecord.class);
            ExpenseRecord expenseRecord1 = expenseRecordService.registerExpenseRecord(expenseRecord);
            context.status(201);
            String expenseRecordJSON = gson.toJson(expenseRecord1);
            context.result(expenseRecordJSON);


        });

        app.get("/expenserecords",context -> {
            List<ExpenseRecord> expenseRecordList = expenseRecordService.expenseList();
            String expenseRecordsListJSON = gson.toJson(expenseRecordList);
            context.result(expenseRecordsListJSON);


        });

    app.start(8080);
    }

}
