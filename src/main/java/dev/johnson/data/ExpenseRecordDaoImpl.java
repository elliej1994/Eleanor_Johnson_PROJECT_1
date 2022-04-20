package dev.johnson.data;

import dev.johnson.entities.Employee;
import dev.johnson.entities.ExpenseRecord;
import dev.johnson.exceptions.ResourceNotFound;
import dev.johnson.utilities.ConnectionUtil;
import dev.johnson.utilities.LogLevel;
import dev.johnson.utilities.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseRecordDaoImpl implements ExpenseRecordDao{
    @Override
    public ExpenseRecord createExpenseRecord(ExpenseRecord expenseRecord) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "insert into expense_records values(default,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, expenseRecord.geteId());
            ps.setString(2, expenseRecord.getExpenseType());
            ps.setDouble(3, expenseRecord.getAmount());
            ps.setString(4, expenseRecord.getStatus());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            rs.next();

            int recordNo = rs.getInt("recordno");
            System.out.println(recordNo);
           expenseRecord.setRecordNo(recordNo);
            return expenseRecord;

        }catch(SQLException e){
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }

    }

    @Override
    public ExpenseRecord getExpenseRecord(int recordNo) {

        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from expense_records where recordno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,recordNo);
            ResultSet rs = ps.executeQuery();

            rs.next();
            ExpenseRecord expenseRecord = new ExpenseRecord();
            expenseRecord.setRecordNo(rs.getInt("recordno"));
            expenseRecord.seteId(rs.getInt("eid"));
            expenseRecord.setExpenseType(rs.getString("expense_type"));
            expenseRecord.setAmount(rs.getDouble("amount"));
            expenseRecord.setStatus(rs.getString("status"));
            return expenseRecord;

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(),LogLevel.ERROR);
        }
        return null;
    }

    @Override
    public List<ExpenseRecord> listExpenseRecord() {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from expense_records";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

           List<ExpenseRecord> expenseRecordList = new ArrayList<>();
           while(rs.next()) {
               ExpenseRecord expenseRecord = new ExpenseRecord();
               expenseRecord.setRecordNo(rs.getInt("recordNo"));
               expenseRecord.seteId(rs.getInt("eid"));
               expenseRecord.setExpenseType(rs.getString("expense_type"));
               expenseRecord.setAmount(rs.getDouble("amount"));
               expenseRecord.setStatus(rs.getString("status"));
               expenseRecordList.add(expenseRecord);
           }
           return expenseRecordList;
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(),LogLevel.ERROR);
        }
        return null;
    }

    @Override
    public ExpenseRecord updateExpenseRecord(ExpenseRecord expenseRecord) {


        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "update expense_records set eid=?, expense_type=?, amount=?, status=? where recordno=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,expenseRecord.geteId());
            ps.setString(2, expenseRecord.getExpenseType());
            ps.setDouble(3,expenseRecord.getAmount());
            ps.setString(4, expenseRecord.getStatus());
            ps.setInt(5,expenseRecord.getRecordNo());

            int rowsUpdated =ps.executeUpdate();

            if(rowsUpdated == 0){
                throw new ResourceNotFound(expenseRecord.getRecordNo());
            }
            return expenseRecord;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}

