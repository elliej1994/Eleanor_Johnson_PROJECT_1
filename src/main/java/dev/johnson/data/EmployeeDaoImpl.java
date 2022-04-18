package dev.johnson.data;

import dev.johnson.entities.Employee;
import dev.johnson.utilities.ConnectionUtil;

import java.sql.*;

public class EmployeeDaoImpl implements EmployeeDao {


    @Override
    public Employee createEmployee(Employee employee) {
    try {
    Connection conn = ConnectionUtil.createConnection();
    String sql = "insert into employees (fname,lname,ph_num,dpt,title,salary) values(default,?,?,?,?,?,?)";
        ResultSet rs;
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, employee.getfName());
            ps.setString(2, employee.getlName());
            ps.setString(3, employee.getPhNum());
            ps.setString(4, employee.getDpt());
            ps.setString(5, employee.getTitle());
            ps.setInt(6, employee.getSalary());
            ps.execute();
            rs = ps.getGeneratedKeys();
        }
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
}
