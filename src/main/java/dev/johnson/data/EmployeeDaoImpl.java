package dev.johnson.data;

import dev.johnson.entities.Employee;
import dev.johnson.utilities.ConnectionUtil;

import java.sql.*;

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
        }


        return null;
    }
}
