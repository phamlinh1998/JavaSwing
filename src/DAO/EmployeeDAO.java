/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.ConnectionDB;

/**
 *
 * @author Dell Vostro
 */
public class EmployeeDAO {
    Connection conn = ConnectionDB.getConnection();

    public List<Employee> getAll() {
        List<Employee> data = new ArrayList<>();
        String sql = "select UsernameEmp,UsernameEmp,Gender,Birthday,Phone,Email,Address,Hinh from Employee p";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Employee ep = new Employee();
                ep.setUsernameEmp(rs.getString("UsernameEmp"));
                ep.setNameEmp(rs.getString("UsernameEmp"));
                ep.setGender(rs.getString("Gender"));
                ep.setBirthday(rs.getString("Birthday"));
                ep.setPhone(rs.getString("Phone"));
                ep.setEmail(rs.getString("Email"));
                ep.setAddress(rs.getString("Address"));
                ep.setHinh(rs.getString("Hinh"));
                data.add(ep);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    public void capnhat(String pass,String Phone,String Email,String Address,String UsernameEmp) {
        String sql = "update Employee set Password=?,Phone=?,Email=?,Address=? where UsernameEmp=?";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            stm.setString(1, pass);
            stm.setString(2, Phone);
            stm.setString(3, Email);
            stm.setString(4, Address);
            stm.setString(5,UsernameEmp);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void them(Employee e) {
        String sql = "insert into Employee values (?,?,?,?,?,?,?,?,?)";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            stm.setString(1, e.getUsernameEmp());
            stm.setString(2, e.getPassword() );
            stm.setString(3, e.getNameEmp());
            stm.setString(4, e.getGender());
            stm.setString(5, e.getBirthday());
            stm.setString(6, e.getPhone());
            stm.setString(7, e.getEmail());
            stm.setString(8, e.getAddress());
            stm.setString(9, e.getHinh());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Employee getByUser(String id){
        String sql = "SELECT UsernameEmp,UsernameEmp,Gender,Birthday,Phone,Email,Address,Hinh FROM Employee WHERE UsernameEmp like ?";
        Employee e = null;
        try {
            CallableStatement cs = conn.prepareCall(sql);
            cs.setObject(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {                
                e = new Employee(rs.getString("UsernameEmp"), rs.getString("UsernameEmp"), rs.getString("Gender"), rs.getString("Birthday"), rs.getString("Phone"), rs.getString("Email"), rs.getString("Address"), rs.getString("Hinh"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }
}
