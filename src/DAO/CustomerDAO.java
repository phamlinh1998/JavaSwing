/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.ConnectionDB;

/**
 *
 * @author Dell Vostro
 */
public class CustomerDAO {

    Connection conn = ConnectionDB.getConnection();

    public List<Customer_Class> getAll() {
        List<Customer_Class> data = new ArrayList<>();
        String sql = "Select * from Customer ORDER BY IDCus DESC";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Customer_Class c = new Customer_Class();
                c.setIDCus(rs.getInt("IDCus"));
                c.setIdentityCard(rs.getString("IdentityCard"));
                c.setCusName(rs.getString("CusName"));
                c.setDate(rs.getString("DateAdd"));
                c.setPhone(rs.getString("Phone"));
                c.setEmail(rs.getString("Email"));
                c.setQuantity(rs.getInt("Quantity"));
                c.setDiscount(rs.getInt("Discount"));
                data.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
//    public Customer_Class getTT() {
//        String sql = "Select * from Customer where";
//        try {
//            CallableStatement stm = conn.prepareCall(sql);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                Customer_Class c = new Customer_Class();
//                c.setIDCus(rs.getInt("IDCus"));
//                c.setIdentityCard(rs.getString("IdentityCard"));
//                c.setCusName(rs.getString("CusName"));
//                c.setDate(rs.getString("DateAdd"));
//                c.setPhone(rs.getString("Phone"));
//                c.setEmail(rs.getString("Email"));
//                c.setQuantity(rs.getInt("Quantity"));
//                c.setDiscount(rs.getInt("Discount"));
//                data.add(c);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return data;
//    }

    public void them(String IdentityCard, String CusName, String date, String phone, String Email, int Quantity, int Discount) {
        String sql = "insert into Customer values (?,?,?,?,?,?,?)";//convert sang dinh dang dd/mm/yyyy
        try {
            CallableStatement stm = conn.prepareCall(sql);
            stm.setString(1, IdentityCard);
            stm.setString(2, CusName);
            stm.setString(3, date);
            stm.setString(4, phone);
            stm.setString(5, Email);
            stm.setInt(6, Quantity);
            stm.setInt(7, Discount);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void capnhat(String Phone, String Email, String IDCus) {
        String sql = "update Customer set Phone=?,Email=? where IDCus=?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, Phone);
            stm.setString(2, Email);
            stm.setString(3, IDCus);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateQuanDis(int Quantity, int Discount, int IDCus) {
        String sql = "update Customer set Quantity=?,Discount=? where IDCus=?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, Quantity);
            stm.setInt(2, Discount);
            stm.setInt(3, IDCus);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void xoa(String ma) {
        String sql = "delete Customer where IDCus=?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, ma);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
