/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.*;
import server.ConnectionDB;
import java.sql.Connection;
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
public class HistoryDAO {

    Connection conn = ConnectionDB.getConnection();

    public List<Employee> getUsernameEmp() {
        List<Employee> data = new ArrayList<>();
        String sql = "Select UsernameEmp from Employee";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setUsernameEmp(rs.getString("UsernameEmp"));
                data.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<History_Class> getByCustomer() {
        List<History_Class> data = new ArrayList<>();
        String sql = "select * from OrderDetails join Orders on OrderDetails.IDOrder=Orders.IDOrder "
                + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                + "join Promotions on OrderDetails.NamePromo=Promotions.NamePromo "
                + "join Customer on OrderDetails.IDCus=Customer.IDCus where Orderdetails.NamePromo != 'Khách vãng lai'";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                History_Class hc = new History_Class();
                hc.setMaDH(rs.getString("IDOrder"));
                hc.setMaSP(rs.getString("IDProduct"));
                hc.setQuantity(rs.getInt("Quantity"));
                hc.setPrice(rs.getInt("Price"));
                hc.setNamepromotion(rs.getString("NamePromo"));
                hc.setMaKH(rs.getString("CusName"));
                hc.setDiscount(rs.getInt("Discount"));
                hc.setThoigian(rs.getString("TimeOrder"));
                hc.setNgay(rs.getString("DateOrder"));
                hc.setUsernameEmp(rs.getString("UsernameEmp"));
                data.add(hc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<History_Class> getAllByCTKM() {
        List<History_Class> data = new ArrayList<>();
        String sql = "select * from OrderDetails join Orders on OrderDetails.IDOrder=Orders.IDOrder "
                + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                + "join Promotions on OrderDetails.NamePromo=Promotions.NamePromo";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                History_Class hc = new History_Class();
                hc.setMaDH(rs.getString("IDOrder"));
                hc.setMaSP(rs.getString("IDProduct"));
                hc.setQuantity(rs.getInt("Quantity"));
                hc.setPrice(rs.getInt("Price"));
                hc.setNamepromotion(rs.getString("NamePromo"));
                hc.setMaKH(rs.getString("IDCus"));
                hc.setDiscount(rs.getInt("DiscountPromo"));
                hc.setThoigian(rs.getString("TimeOrder"));
                hc.setNgay(rs.getString("DateOrder"));
                hc.setUsernameEmp(rs.getString("UsernameEmp"));
                data.add(hc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<History_Class> getAll() {
        List<History_Class> data = new ArrayList<>();
        String sql = "select * from OrderDetails join Orders on OrderDetails.IDOrder=Orders.IDOrder join Product on OrderDetails.IDProduct=Product.IDProduct join Promotions on OrderDetails.NamePromo=Promotions.NamePromo";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                History_Class hc = new History_Class();
                hc.setMaDH(rs.getString("IDOrder"));
                hc.setMaSP(rs.getString("IDProduct"));
                hc.setQuantity(rs.getInt("Quantity"));
                hc.setPrice(rs.getInt("Price"));
                hc.setNamepromotion(rs.getString("NamePromo"));
                hc.setMaKH(rs.getString("IDCus"));
                hc.setDiscount(rs.getInt("DiscountPromo"));
                hc.setThoigian(rs.getString("TimeOrder"));
                hc.setNgay(rs.getString("DateOrder"));
                hc.setUsernameEmp(rs.getString("UsernameEmp"));
                data.add(hc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<History_Class> TK_getall(String ten, String date) {
        List<History_Class> data = new ArrayList<>();
        String sql = "select * from OrderDetails join Orders on OrderDetails.IDOrder=Orders.IDOrder join Product on OrderDetails.IDProduct=Product.IDProduct join Promotions on OrderDetails.NamePromo=Promotions.NamePromo where UsernameEmp=? and DateOrder=?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, ten);
            stm.setString(2, date);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                History_Class hc = new History_Class();
                hc.setMaDH(rs.getString("IDOrder"));
                hc.setMaSP(rs.getString("IDProduct"));
                hc.setQuantity(rs.getInt("Quantity"));
                hc.setPrice(rs.getInt("Price"));
                hc.setNamepromotion(rs.getString("NamePromo"));
                hc.setMaKH(rs.getString("IDCus"));
                hc.setDiscount(rs.getInt("DiscountPromo"));
                hc.setThoigian(rs.getString("TimeOrder"));
                hc.setNgay(rs.getString("DateOrder"));
                hc.setUsernameEmp(rs.getString("UsernameEmp"));
                data.add(hc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<History_Class> TK_getAllByCTKM(String ten, String date) {
        List<History_Class> data = new ArrayList<>();
        String sql = "select * from OrderDetails join Orders on OrderDetails.IDOrder=Orders.IDOrder "
                + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                + "join Promotions on OrderDetails.NamePromo=Promotions.NamePromo where UsernameEmp=?  and DateOrder=?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, ten);
            stm.setString(2, date);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                History_Class hc = new History_Class();
                hc.setMaDH(rs.getString("IDOrder"));
                hc.setMaSP(rs.getString("IDProduct"));
                hc.setQuantity(rs.getInt("Quantity"));
                hc.setPrice(rs.getInt("Price"));
                hc.setNamepromotion(rs.getString("NamePromo"));
                hc.setMaKH(rs.getString("IDCus"));
                hc.setDiscount(rs.getInt("DiscountPromo"));
                hc.setThoigian(rs.getString("TimeOrder"));
                hc.setNgay(rs.getString("DateOrder"));
                hc.setUsernameEmp(rs.getString("UsernameEmp"));
                data.add(hc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<History_Class> TK_getByCustomer(String ten, String date) {
        List<History_Class> data = new ArrayList<>();
        String sql = "select * from OrderDetails join Orders on OrderDetails.IDOrder=Orders.IDOrder "
                + "join Product on OrderDetails.IDProduct=Product.IDProduct "
                + "join Promotions on OrderDetails.NamePromo=Promotions.NamePromo "
                + "join Customer on OrderDetails.IDCus=Customer.IDCus where Orderdetails.NamePromo != 'Khách vãng lai' and UsernameEmp=? and DateOrder=?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, ten);
            stm.setString(2, date);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                History_Class hc = new History_Class();
                hc.setMaDH(rs.getString("IDOrder"));
                hc.setMaSP(rs.getString("IDProduct"));
                hc.setQuantity(rs.getInt("Quantity"));
                hc.setPrice(rs.getInt("Price"));
                hc.setNamepromotion(rs.getString("NamePromo"));
                hc.setMaKH(rs.getString("CusName"));
                hc.setDiscount(rs.getInt("Discount"));
                hc.setThoigian(rs.getString("TimeOrder"));
                hc.setNgay(rs.getString("DateOrder"));
                hc.setUsernameEmp(rs.getString("UsernameEmp"));
                data.add(hc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    public boolean getOrder(String ten, String date){
        String sql ="select * from OrderDetails  join Orders on OrderDetails.IDOrder=Orders.IDOrder where UsernameEmp =? and DateOrder =?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, ten);
            stm.setString(2, date);
            ResultSet rs = stm.executeQuery();
            while (!rs.next()) {                
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    public Employee getNameEmployee(String ma) {
        Employee e = new Employee();
        String sql = "Select NameEmp from Employee where UsernameEmp=?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, ma);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                e.setNameEmp(rs.getString("NameEmp"));
               
            }
            return e;
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
