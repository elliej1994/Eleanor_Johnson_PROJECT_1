package dev.johnson.api;

import com.google.gson.Gson;
import dev.johnson.data.EmployeeDaoImpl;
import dev.johnson.data.ExpenseDaoImpl;
import dev.johnson.entities.Employee;
import dev.johnson.entities.Expense;
import dev.johnson.exceptions.ResourceNotFound;
import dev.johnson.service.EmployeeService;
import dev.johnson.service.EmployeeServiceImpl;
import dev.johnson.service.ExpenseService;
import dev.johnson.service.ExpenseServiceImpl;
import dev.johnson.utilities.ConnectionUtil;
import dev.johnson.utilities.Logger;
import io.javalin.Javalin;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
An application to keep track of employee reimbursement requests
*/
public class ExpenseManagementApp {

    public static Gson gson = new Gson();
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDaoImpl());
    public static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDaoImpl());


    public static void main(String[] args) {


        Javalin app =  Javalin.create();
/*
*
* employee routes
*
*pluralizeeee
 */

        app.get("/", context -> {
            Connection conn = ConnectionUtil.createConnection();
            if(conn != null){
                context.status(200);
                context.result("Application is working");
            }else{
                context.status(400);
                context.result("Application not connecting");
            }
        });

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
        Employee employee = employeeService.retrieveEmployeeById(eId);
        if(employee!= null){
                String employeeJSON = gson.toJson(employeeService.retrieveEmployeeById(eId));
                context.result(employeeJSON);
            }else{
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
            List<Expense> eList = expenseService.expenseList();
               for(Expense expense : eList) {
                   if (expense.geteId() == eId) {
                       context.result("Cannot delete employees with any expense records");
                       context.status(401);
                       return;
                   } else {

                       boolean result = employeeService.destroyEmployee(eId);

                       if (result) {
                           context.result("The employee with that id has been deleted");
                       } else {
                           context.status(404);
                           context.result("That employee does not exist");
                       }
                   }
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

        app.post("/expense", context -> {
            String body = context.body();
            Expense expense = gson.fromJson(body, Expense.class);
            expense.setStatus("Pending");
            Expense expense1 = expenseService.registerExpense(expense);
            context.status(201);
            String expenseRecordJSON = gson.toJson(expense1);
            context.result(expenseRecordJSON);
        });

        app.get("/expense",context -> {
            String status = context.queryParam("status");
            List<Expense> expenseList = expenseService.expenseList();
            if(status==null){
                context.result(gson.toJson(expenseList));
            }else{
                List<Expense> requestedStatus = new ArrayList<>();
                for(Expense expense : expenseList){
                    if(expense.getStatus().equals(status)){
                        requestedStatus.add(expense);
                    }
                }
                String expenseRecordsListJSON = gson.toJson(requestedStatus);
                context.result(expenseRecordsListJSON);
            }
        });

        app.get("/expense/{recordno}", context -> {
            int recordNo = Integer.parseInt(context.pathParam("recordno"));
            Expense expense = expenseService.retrieveExpensesByNo(recordNo);
            if(expense!=null) {
                String expensesJSON = gson.toJson(expenseService.retrieveExpensesByNo(recordNo));
                context.result(expensesJSON);
            }else{
                context.status(404);
                context.result("The expense record with that record number was not found.");
            }
        });

        app.put("/expense/{recordno}",context -> {
            int recordNo = Integer.parseInt(context.pathParam("recordno"));
            String body = context.body();
            Expense expense = gson.fromJson(body, Expense.class);
            expense.setRecordNo(recordNo);
            if(Objects.equals(expense.getStatus(),"Pending")){
                expenseService.replaceExpense(expense);
                context.result("Expense record replaced");
            }else{
                context.result("Cannot update a finalized expense");
            }

        });

        app.patch("/expense/{recordno}/approve",context -> {
            int recordNo = Integer.parseInt(context.pathParam("recordno"));
            Expense expense = expenseService.retrieveExpensesByNo(recordNo);
            if(expense!=null) {

                    if (Objects.equals(expense.getStatus(), "Pending")) {
                        expenseService.approveExpense(expense);
                        context.result("The expense has been approved");
                        context.status(200);
                    } else {
                        context.result("Cannot update a finalized expense.");
                        context.status(400);
                    }
                } else {
                    context.status(404);
                    context.result("There is no pending expense with that record number");
                }

        });



        app.patch("/expense/{recordno}/deny",context -> {

                int recordNo = Integer.parseInt(context.pathParam("recordno"));
                Expense expense = expenseService.retrieveExpensesByNo(recordNo);
                if(expense!=null){

                if(Objects.equals(expense.getStatus(),"Pending")){
                    expenseService.denyExpense(expense);
                    context.result("The expense has been denied");
                    context.status(200);
                } else{
                    context.result("Cannot update a finalized expense");
                }
            }else{
                context.result("There is no pending expense with that record number");
                context.status(404);

            }
        });
        app.delete("/expense/{recordno}", context -> {
            int recordNo = Integer.parseInt(context.pathParam("recordno"));
            Expense expense = expenseService.retrieveExpensesByNo(recordNo);
           if(expense!=null) {
              if (Objects.equals(expense.getStatus(), "Pending")) {
                   expenseService.destroyExpense(recordNo);
                   context.status(201);
                   context.result("Expense deleted");
               } else if(expense.getStatus()!="Pending"){
                  context.status(400);
                  context.result("Cannot delete a finalized record");
              }
           }else if(expense==null){
               context.status(404);
               context.result("There is no expense with that record number");
           }
           });


        app.get("/employee/{eid}/expense",context -> {
            int eId = Integer.parseInt(context.pathParam("eid"));
            List<Expense> expenseList = expenseService.expenseList();
            List<Expense> requestedList = new ArrayList<>();
            for(Expense expense : expenseList){
                if(expense.geteId()==eId){
                    requestedList.add(expense);
                }
            }
            try{
                String employeeJSON = gson.toJson(employeeService.retrieveEmployeeById(eId));
                String expensesJSON = gson.toJson(requestedList);
                context.result(expensesJSON + employeeJSON);
            }catch(ResourceNotFound e){
               context.status(404);
            }
        });

        app.post("/employee/{eid}/expense", context -> {
            int eId = Integer.parseInt(context.pathParam("eid"));
              String body = context.body();
              Expense expense = gson.fromJson(body, Expense.class);
              expense.seteId(eId);
              expense.setStatus("Pending");
              if(expense.getAmount() <0 )
              {
                  context.status(400);
                  context.result("Cannot create a negative expense "+ expense);

              }else{
                  Expense expense2 = expenseService.registerExpense(expense);
                  context.status(201);
                  String expenseRecordJSON = gson.toJson(expense2);
                  context.result(expenseRecordJSON);
                  context.result("Created a new expense");
              }
        });


    app.start(8080);


    }

}
