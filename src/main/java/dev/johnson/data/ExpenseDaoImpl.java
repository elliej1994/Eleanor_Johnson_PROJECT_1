package dev.johnson.data;

import dev.johnson.entities.Expense;
import dev.johnson.exceptions.ResourceNotFound;
import dev.johnson.utilities.ConnectionUtil;
import dev.johnson.utilities.LogLevel;
import dev.johnson.utilities.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDaoImpl implements ExpenseDao {
    @Override
    public Expense createExpense(Expense expense) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "insert into expense values(default,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, expense.geteId());
            ps.setString(2, expense.getExpenseType());
            ps.setDouble(3, expense.getAmount());
            ps.setString(4, expense.getStatus());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            rs.next();

            int recordNo = rs.getInt("recordno");
            System.out.println(recordNo);
           expense.setRecordNo(recordNo);
            return expense;

        }catch(SQLException e){
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }

    }



    @Override
    public Expense getExpense(int recordNo) {

        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from expense where recordno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,recordNo);
            ResultSet rs = ps.executeQuery();

            rs.next();
            Expense expense = new Expense();
            expense.setRecordNo(rs.getInt("recordno"));
            expense.seteId(rs.getInt("eid"));
            expense.setExpenseType(rs.getString("expense_type"));
            expense.setAmount(rs.getDouble("amount"));
            expense.setStatus(rs.getString("status"));
            return expense;

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(),LogLevel.ERROR);
        }
        return null;
    }

    @Override
    public Expense getExpenseById(int eId) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from expense where eid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,eId);
            ResultSet rs = ps.executeQuery();

            rs.next();
            Expense expense = new Expense();
            expense.setRecordNo(rs.getInt("recordno"));
            expense.seteId(rs.getInt("eid"));
            expense.setExpenseType(rs.getString("expense_type"));
            expense.setAmount(rs.getDouble("amount"));
            expense.setStatus(rs.getString("status"));
            return expense;

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(),LogLevel.ERROR);
        }
        return null;
    }

    @Override
    public List<Expense> listExpense() {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from expense";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

           List<Expense> expenseList = new ArrayList<>();
           while(rs.next()) {
               Expense expense = new Expense();
               expense.setRecordNo(rs.getInt("recordno"));
               expense.seteId(rs.getInt("eid"));
               expense.setExpenseType(rs.getString("expense_type"));
               expense.setAmount(rs.getDouble("amount"));
               expense.setStatus(rs.getString("status"));
               expenseList.add(expense);
           }
           return expenseList;
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(),LogLevel.ERROR);
        }
        return null;
    }

    @Override
    public Expense updateExpense(Expense expense) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "update expense set eid=?, expense_type=?, amount=?, status=? where recordno=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, expense.geteId());
            ps.setString(2, expense.getExpenseType());
            ps.setDouble(3, expense.getAmount());
            ps.setString(4, expense.getStatus());
            ps.setInt(5, expense.getRecordNo());

            int rowsUpdated =ps.executeUpdate();

            if(rowsUpdated == 0){
                throw new ResourceNotFound(expense.getRecordNo());
            }
            return expense;
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(),LogLevel.ERROR);
            return null;
        }
    }

    @Override
    public boolean deleteExpense(int recordNo) {
        try{
            Connection conn = ConnectionUtil.createConnection();
            String sql = "delete from expense where recordno=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,recordNo);
            ps.execute();
            return true;
        }catch(SQLException e){
            Logger.log(e.getMessage(),LogLevel.ERROR);
            e.printStackTrace();
            return false;

        }

    }
}

