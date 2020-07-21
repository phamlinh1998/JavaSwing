/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import server.ConnectionDB;
import entities.Revenue_Class;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell Vostro
 */
public class RevenueDAO {

    Connection conn = ConnectionDB.getConnection();

    public List<Revenue_Class> getAll() {
        List<Revenue_Class> data = new ArrayList<>();
        String sql = "Select * from Revenue order by IDRevenue DESC";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Revenue_Class rc = new Revenue_Class();
                rc.setMa(rs.getInt("IDRevenue"));
                rc.setDate(rs.getString("Date"));
                rc.setMoney(rs.getString("Money"));
                data.add(rc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<Revenue_Class> getByDate(String date) {
        String sql = "select * from Revenue where Date like ?";
        List<Revenue_Class> data = new ArrayList<>();
        try {
            CallableStatement cs = conn.prepareCall(sql);
            cs.setObject(1, date);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Revenue_Class rc = new Revenue_Class();
                rc.setMa(rs.getInt("IDRevenue"));
                rc.setDate(rs.getString("Date"));
                rc.setMoney(rs.getString("Money"));
                data.add(rc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public Revenue_Class getMoney1() {
        Revenue_Class rc = new Revenue_Class();
        String sql = "Select * from Revenue where Date=convert(varchar(20),getdate(),103)";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                rc.setMa(rs.getInt("IDRevenue"));
                rc.setDate(rs.getString("Date"));
                rc.setMoney(rs.getString("Money"));
                return rc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateRevenue(String money) {
        String sql = "update Revenue set Money=? where Date=convert(varchar(20),getdate(),103)";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, money);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RevenueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addRevenue(String money) {
        String sql = "insert into Revenue values(convert(varchar(20),getdate(),103),?)";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, money);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RevenueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
