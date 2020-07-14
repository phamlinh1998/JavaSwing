/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.ProductType_Class;
import java.sql.CallableStatement;
import server.ConnectionDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.Product_CLass;

/**
 *
 * @author Dell Vostro
 */
public class ProductDAO {

    Connection conn = ConnectionDB.getConnection();

    public List<Product_CLass> getAll() {
        List<Product_CLass> data = new ArrayList<>();
        String sql = "select * from Product p inner join ProductType pt on p.IDType = pt.IDType";
        try {
            CallableStatement stm = conn.prepareCall(sql);
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

    public void xoa(String ma) {
        String sql = "delete Product where IDProduct=?";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            stm.setString(1, ma);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getIdType(String TypeName, String Size) throws SQLException {
        String sql = "select * from ProductType where TypeName=? and Size=?";

        ProductType_Class ptc = new ProductType_Class();
        CallableStatement stm = conn.prepareCall(sql);
        stm.setString(1, TypeName);
        stm.setString(2, Size);
        ResultSet rs = stm.executeQuery();
        String IDType = rs.getString("IDType");
        return IDType;
    }

    public void them(Product_CLass p) {
        try {
//            ProductType_Class ptc = new ProductType_Class();
            String sql = "insert into Product values (?,?,?,?)";
//            String sql1 = "select * from ProductType where TypeName=? and Size=?";
//            CallableStatement stm1 = conn.prepareCall(sql1);
//            stm1.setString(1, ptc.getTypeName());
//            stm1.setString(2, ptc.getSize());
//            ResultSet rs = stm1.executeQuery();
            CallableStatement stm = conn.prepareCall(sql);
            stm.setString(1, p.getIDProduct());
            stm.setString(2, p.getProductName());
            stm.setString(3, p.getIDType());
            stm.setInt(4, p.getPrice());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Product_CLass> getByProductName(String tensp) {
        String sql = "select * from Product p inner join ProductType pt on p.IDType = pt.IDType where ProductName like ?";
        List<Product_CLass> data = new ArrayList<>();
        try {
            CallableStatement cs = conn.prepareCall(sql);
            cs.setObject(1, tensp);
            ResultSet rs = cs.executeQuery();
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
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<Product_CLass> getByPrice(int a, int b) {
        String sql = "select * from Product p inner join ProductType pt on p.IDType = pt.IDType where Price BETWEEN ? and ?";
        List<Product_CLass> data = new ArrayList<>();
        try {
            CallableStatement cs = conn.prepareCall(sql);
            cs.setObject(1, a);
            cs.setObject(2, b);
            ResultSet rs = cs.executeQuery();
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
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<Product_CLass> getByGroup(String tensp, String kichthuoc) {
        String sql = "select * from Product p inner join ProductType pt on p.IDType = pt.IDType where TypeName=? and Size=?";
        List<Product_CLass> data = new ArrayList<>();
        try {
            CallableStatement cs = conn.prepareCall(sql);
            cs.setObject(1, tensp);
            cs.setObject(2, kichthuoc);
            ResultSet rs = cs.executeQuery();
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
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
}
