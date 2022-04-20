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
        app.post("/employee", context -> {
           String body = context.body();
           Employee employee = gson.fromJson(body, Employee.class);
            Employee employee1 = employeeService.registerEmployee(employee);
            context.status(201);
            String employeeJson = gson.toJson(employee1);
            context.result(employeeJson);
        });


        app.get("/employee", context -> {
            List<Employee> employeeList = employeeService.employeeList();
            String employeeListJSON = gson.toJson(employeeList);
            context.result(employeeListJSON);
        });

        app.get("/employee/{eid}", context -> {
        int eId = Integer.parseInt(context.pathParam("eid"));
            try {
                String employeeJSON = gson.toJson(employeeService.retrieveEmployeeById(eId));

                context.result(employeeJSON);
            }catch(ResourceNotFound e){
                context.status(404);
                context.result("The employee with that id was not found.");
            }
        });

        app.put("/employee/{eid}", context -> {
            int eId = Integer.parseInt(context.pathParam("eid"));
            String body = context.body();
            Employee employee = gson.fromJson(body,Employee.class);
            employee.seteId(eId);
            employeeService.replaceEmployee(employee);
            context.result("Employee replaced");
        });

        app.delete("/employee/{eid}", context -> {
            int eId = Integer.parseInt(context.pathParam("eid"));
            boolean result = employeeService.destroyEmployee(eId);
            if(result){
                context.status(404);
                context.result("That employee does not exist");
                Logger.log("Attempted to delete nonexistant employee with eId "+ eId,LogLevel.INFO);

            }else{
               context.result("The employee with that id has been deleted");
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

        app.post("/expenses", context -> {
            String body = context.body();
            ExpenseRecord expenseRecord = gson.fromJson(body,ExpenseRecord.class);
            ExpenseRecord expenseRecord1 = expenseRecordService.registerExpenseRecord(expenseRecord);
            context.status(201);
            String expenseRecordJSON = gson.toJson(expenseRecord1);
            context.result(expenseRecordJSON);
        });

        app.get("/expenses",context -> {
            String status = context.queryParam("status");
            List<ExpenseRecord> expenseRecordList = expenseRecordService.expenseList();
            if(status==null){
                context.result(gson.toJson(expenseRecordList));
            }else{
                List<ExpenseRecord> requestedStatus = new ArrayList<>();
                for(ExpenseRecord expenseRecord : expenseRecordList){
                    if(expenseRecord.getStatus().equals(status)){
                        requestedStatus.add(expenseRecord);
                    }
                }
                String expenseRecordsListJSON = gson.toJson(requestedStatus);
                context.result(expenseRecordsListJSON);
            }
        });

        app.get("/expenses/{recordno}", context -> {
            int recordNo = Integer.parseInt(context.pathParam("recordno"));
            try {
                String expensesJSON = gson.toJson(expenseRecordService.retrieveExpensesByNo(recordNo));
                context.result(expensesJSON);
            }catch(ResourceNotFound e){
                context.status(404);
                context.result("The expense record with that record number was not found.");
            }
        });

        app.put("/expenses/{recordno}",context -> {
            int recordNo = Integer.parseInt(context.pathParam("recordno"));
            String body = context.body();
            ExpenseRecord expenseRecord = gson.fromJson(body,ExpenseRecord.class);
            expenseRecord.setRecordNo(recordNo);
            expenseRecordService.replaceExpenseRecord(expenseRecord);
            context.result("Expense record replaced");
        });



      /*  app.put("/employee/{eid}", context -> {
            int eId = Integer.parseInt(context.pathParam("eid"));
            String body = context.body();
            Employee employee = gson.fromJson(body,Employee.class);
            employee.seteId(eId);
            employeeService.replaceEmployee(employee);
            context.result("Employee replaced");
        });*/

    app.start(8080);
    }

}
