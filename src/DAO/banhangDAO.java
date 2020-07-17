/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.Customer_Class;
import entities.Product_CLass;
import entities.ProductType_Class;
import entities.Promotion_Class;
import server.ConnectionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell Vostro
 */
public class banhangDAO {

    Connection conn = ConnectionDB.getConnection();

    public List<Product_CLass> getAll(String ten, String kichthuoc) {
        List<Product_CLass> data = new ArrayList<>();
        String sql = "Select * from Product Join ProductType on Product.IDType=ProductType.IDType where Product.ProductName=? and ProductType.Size=?";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            stm.setString(1, ten);
            stm.setString(2, kichthuoc);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product_CLass p = new Product_CLass();
                p.setIDProduct(rs.getString("IDProduct"));
                p.setProductName(rs.getString("ProductName"));
                p.setIDType(rs.getString("IDType"));
                p.setPrice(rs.getInt("Price"));
                p.setSize(rs.getString("Size"));
                data.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<Promotion_Class> getNamepromotion() {
        List<Promotion_Class> data = new ArrayList<>();
        String sql = "select DISTINCT NamePromo from Promotions";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Promotion_Class pc = new Promotion_Class();
                pc.setNamePromo(rs.getString("NamePromo"));
                data.add(pc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<Product_CLass> getNamePro() {
        List<Product_CLass> data = new ArrayList<>();
        String sql = "select DISTINCT ProductName from Product";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product_CLass pc = new Product_CLass();
                pc.setProductName(rs.getString("ProductName"));
                data.add(pc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<ProductType_Class> getSize() {
        List<ProductType_Class> data = new ArrayList<>();
        String sql = "select DISTINCT Size from ProductType";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ProductType_Class pt = new ProductType_Class();
                pt.setSize(rs.getString("Size"));
                data.add(pt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public Customer_Class getThongTinKH(int idcus) {
        Customer_Class c = new Customer_Class();
        String sql = "Select * from Customer where IDCus=?";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            stm.setInt(1, idcus);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                c.setIDCus(rs.getInt("IDCus"));
                c.setIdentityCard(rs.getString("IdentityCard"));
                c.setCusName(rs.getString("CusName"));
                c.setDate(rs.getString("DateAdd"));
                c.setPhone(rs.getString("Phone"));
                c.setEmail(rs.getString("Email"));
                c.setQuantity(rs.getInt("Quantity"));
                c.setDiscount(rs.getInt("Discount"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
}
