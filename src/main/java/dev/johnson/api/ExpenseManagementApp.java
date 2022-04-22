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
import java.util.Objects;

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
            List<ExpenseRecord> expenseRecordList = expenseRecordService.expenseList();
               for(ExpenseRecord expenseRecord :expenseRecordList){
                   if(expenseRecord.geteId()==eId){
                       context.result("Cannot delete employees with any expense records");
                   }
               }
               boolean result = employeeService.destroyEmployee(eId);
            if(result){
                context.result("The employee with that id has been deleted");
            }else{
                context.status(404);
                context.result("That employee does not exist");

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
            expenseRecord.setStatus("Pending");
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
            if(Objects.equals(expenseRecord.getStatus(),"Pending")){
                expenseRecordService.replaceExpenseRecord(expenseRecord);
                context.result("Expense record replaced");
            }else{
                context.result("Cannot update a finalized expense");
            }

        });

        app.patch("/expenses/{recordno}/approve",context -> {
            try {
                int recordNo = Integer.parseInt(context.pathParam("recordno"));
                ExpenseRecord expenseRecord = expenseRecordService.retrieveExpensesByNo(recordNo);
                if(Objects.equals(expenseRecord.getStatus(), "Pending")){
                    expenseRecordService.approveExpense(expenseRecord);
                    context.result("The expense has been approved");
                    context.status(200);
                }else {
                    context.result("Cannot update a finalized expense.");
                }
            }catch(ResourceNotFound e){
                context.status(404);
                context.result(e.getMessage());
            }
        });



        app.patch("/expenses/{recordno}/deny",context -> {
            try {
                int recordNo = Integer.parseInt(context.pathParam("recordno"));
                ExpenseRecord expenseRecord = expenseRecordService.retrieveExpensesByNo(recordNo);
                if(Objects.equals(expenseRecord.getStatus(),"Pending")){
                    expenseRecordService.denyExpense(expenseRecord);
                    context.result("The expense has been denied");
                    context.status(200);
                } else{
                    context.result("Cannot update a finalized expense");
                }
            }catch(ResourceNotFound e){
                context.result(e.getMessage());
                context.status(404);

            }
        });
        app.delete("/expenses/{recordno}", context -> {
            int recordNo = Integer.parseInt(context.pathParam("recordno"));
            ExpenseRecord expenseRecord = expenseRecordService.retrieveExpensesByNo(recordNo);
            if(expenseRecord.getStatus()=="Pending") {
                expenseRecordService.destroyExpense(recordNo);
                context.status(201);
                context.result("Expense deleted");
            } else context.status(400);
            context.result("Cannot delete a finalized record");
        });


        app.get("/employee/{eid}/expenses",context -> {
            int eId = Integer.parseInt(context.pathParam("eid"));
            try{
                String employeeJSON = gson.toJson(employeeService.retrieveEmployeeById(eId));
                String expensesJSON = gson.toJson(expenseRecordService.retrieveExpenseByeId(eId));
                context.result(expensesJSON + employeeJSON);
            }catch(ResourceNotFound e){
               context.status(404);
            }
        });

        app.post("/employee/{eid}/expenses", context -> {
            int eId = Integer.parseInt(context.pathParam("eid"));
              String body = context.body();
              ExpenseRecord expenseRecord = gson.fromJson(body, ExpenseRecord.class);
              expenseRecord.seteId(eId);
              expenseRecord.setStatus("Pending");
              if(expenseRecord.getAmount() <0 )
              {
                  context.status(400);
                  context.result("Cannot create a negative expense "+ expenseRecord);

              }else{
                  ExpenseRecord expenseRecord2 = expenseRecordService.registerExpenseRecord(expenseRecord);
                  context.status(201);
                  String expenseRecordJSON = gson.toJson(expenseRecord2);
                  context.result(expenseRecordJSON);
                  context.result("Created a new expense");
              }
        });

    app.start(8080);
    }

}
