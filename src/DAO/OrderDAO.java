/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import server.ConnectionDB;
import java.sql.*;
import entities.Order_Class;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell Vostro
 */
public class OrderDAO {

    Connection conn = ConnectionDB.getConnection();

    public List<Order_Class> getAll() {
        List<Order_Class> data = new ArrayList<>();
        String sql = "select Orders.IDOrder,IDProduct,CusName,Quantity,NamePromo,TimeOrder,DateOrder,UsernameEmp "
                + "from OrderDetails join Orders on OrderDetails.IDOrder=Orders.IDOrder Order by OrderDetails.IDOrder DESC";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order_Class oc = new Order_Class();
                oc.setIDOrder(rs.getString("IDOrder"));
                oc.setIDProduct(rs.getString("IDProduct"));
                oc.setCusName(rs.getString("CusName"));
                oc.setQuantity(rs.getInt("Quantity"));
                oc.setNamePromo(rs.getString("NamePromo"));
                oc.setTimeOrder(rs.getString("TimeOrder"));
                oc.setDateOrder(rs.getString("DateOrder"));
                oc.setUsernameEmp(rs.getString("UsernameEmp"));
                data.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<Order_Class> TimKiem(String IDOrder, String IDProduct,String CusName,String NamePromo,String DateOrder,String UsernameEmp ) {
        String sql = "select Orders.IDOrder,IDProduct,CusName,Quantity,NamePromo,TimeOrder,DateOrder,UsernameEmp "
                + "from OrderDetails join [Orders] on OrderDetails.IDOrder=[Orders].IDOrder "
                + "where OrderDetails.IDOrder LIKE ? or OrderDetails.IDProduct LIKE ? or OrderDetails.CusName LIKE ? "
                + "or OrderDetails.NamePromo LIKE ? or [Orders].DateOrder LIKE ?"
                + "or [Orders].UsernameEmp LIKE ? Order by OrderDetails.IDOrder DESC";
        List<Order_Class> data = new ArrayList<>();
        Order_Class oc = null;
        try {
            CallableStatement stm = conn.prepareCall(sql);
            stm.setString(1, IDOrder);
            stm.setString(2, IDProduct);
            stm.setString(3, CusName);
            stm.setString(4, NamePromo);
            stm.setString(5, DateOrder);
            stm.setString(6, UsernameEmp);
            ResultSet rs = stm.executeQuery();
            int quantity = rs.getInt("Quantity");
            while (rs.next()) {  
                oc = new Order_Class(IDOrder, IDProduct, quantity, NamePromo, DateOrder, DateOrder, UsernameEmp);
                data.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    public List<Order_Class> getByIDOrder() {
        List<Order_Class> data = new ArrayList<>();
        String sql="select IDOrder from Orders";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order_Class oc = new Order_Class();
                oc.setIDOrder(rs.getString("IDOrder"));
                data.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    public List<Order_Class> getByIDProduct() {
        List<Order_Class> data = new ArrayList<>();
        String sql="select IDProduct from OrderDetails";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order_Class oc = new Order_Class();
                oc.setIDProduct(rs.getString("IDProduct"));
                data.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    public List<Order_Class> getByCusName() {
        List<Order_Class> data = new ArrayList<>();
        String sql="select CusName from OrderDetails";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order_Class oc = new Order_Class();
                oc.setCusName(rs.getString("CusName"));
                data.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    public List<Order_Class> getByNamePromo() {
        List<Order_Class> data = new ArrayList<>();
        String sql="select NamePromo from OrderDetails";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order_Class oc = new Order_Class();
                oc.setNamePromo(rs.getString("NamePromo"));
                data.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    public List<Order_Class> getByDateOrder() {
        List<Order_Class> data = new ArrayList<>();
        String sql="select DateOrder from OrderDetails";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order_Class oc = new Order_Class();
                oc.setDateOrder(rs.getString("DateOrder"));
                data.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    public List<Order_Class> getByUsernameEmp() {
        List<Order_Class> data = new ArrayList<>();
        String sql="select Distinct UsernameEmp from Orders";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order_Class oc = new Order_Class();
                oc.setUsernameEmp(rs.getString("UsernameEmp"));
                data.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
}

