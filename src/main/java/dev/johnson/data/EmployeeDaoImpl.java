package dev.johnson.data;

import dev.johnson.entities.Employee;
import dev.johnson.exceptions.ResourceNotFound;
import dev.johnson.utilities.ConnectionUtil;
import dev.johnson.utilities.LogLevel;
import dev.johnson.utilities.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {


    @Override
    public Employee createEmployee(Employee employee) {
    try {
    Connection conn = ConnectionUtil.createConnection();
    String sql = "insert into employees values(default,?,?,?)";
    PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getfName());
            ps.setString(2, employee.getlName());
            ps.setString(3, employee.getDpt());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            rs.next();

    int eId = rs.getInt("eid");
        System.out.println(eId);
        employee.seteId(eId);
        return employee;

    }catch(SQLException e){
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            }
        return null;
    }

    @Override
    public Employee getEmployee(int eId) {



        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from employees where eid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,eId);
            ResultSet rs = ps.executeQuery();
            //look into exceptions here
            rs.next();
            Employee employee = new Employee();
            employee.seteId(rs.getInt("eid"));
            employee.setfName(rs.getString("fname"));
            employee.setlName(rs.getString("lname"));
            employee.setDpt(rs.getString("dpt"));
            return employee;

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(),LogLevel.ERROR);
        }


        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from employees";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Employee> employeeList = new ArrayList<>();
            while(rs.next()){
                Employee employee = new Employee();
                employee.seteId(rs.getInt("eid"));
                employee.setfName(rs.getString("fname"));
                employee.setlName(rs.getString("lname"));
                employee.setDpt(rs.getString("dpt"));
                employeeList.add(employee);
            }
                return employeeList;
        }catch(SQLException e){
                e.printStackTrace();
                return null;
            }
    }

    @Override
    public Employee updateEmployee(Employee employee) {

        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "update employees set fname=?, lname=?, dpt=? where eid=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getfName());
            ps.setString(2, employee.getlName());
            ps.setString(3, employee.getDpt());
            ps.setInt(4,employee.geteId());
            int rowsUpdated = ps.executeUpdate();

            if(rowsUpdated == 0){
                throw new ResourceNotFound(employee.geteId());
            }
            return employee;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteEmployee(int eId) {

        try{
            Connection conn = ConnectionUtil.createConnection();
            String sql = "delete from employees where eid=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,eId);
            ps.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;

        }

    }
}
