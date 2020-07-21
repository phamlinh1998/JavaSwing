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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.ConnectionDB;

/**
 *
 * @author Dell Vostro
 */
public class ProductTypeDAO {

    Connection conn = ConnectionDB.getConnection();

    public List<ProductType_Class> getAll() {
        List<ProductType_Class> data = new ArrayList<>();
        String sql = "select * from ProductType";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ProductType_Class pt = new ProductType_Class();
                pt.setIDType(rs.getString("IDType"));
                pt.setTypeName(rs.getString("TypeName"));
                pt.setSize(rs.getString("Size"));
                data.add(pt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<ProductType_Class> getTypeName() {
        List<ProductType_Class> data = new ArrayList<>();
        String sql = "select DISTINCT TypeName from ProductType";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ProductType_Class pt = new ProductType_Class();
                pt.setTypeName(rs.getString("TypeName"));
                data.add(pt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public void them(ProductType_Class pt) {
        String sql = "insert into ProductType values (?,?,?)";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            stm.setString(1, pt.getIDType());
            stm.setString(2, pt.getTypeName());
            stm.setString(3, pt.getSize());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void xoa(String ma) {
        String sql = "delete ProductType where IDType=?";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            stm.setString(1, ma);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean xoasp(String ma) {
        String sql = "delete Product where IDType=?";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            stm.setString(1, ma);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public void capnhat(ProductType_Class pt) {
        String sql = "update ProductType set TypeName=?,Size=? where IDType=?";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            stm.setString(1, pt.getTypeName());
            stm.setString(2, pt.getSize());
            stm.setString(3, pt.getIDType());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ProductType_Class getId(String ten, String kichthuoc) {
        String sql = "select IDType from ProductType where TypeName=? and Size=?";
        
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, ten);
            stm.setString(2, kichthuoc);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ProductType_Class ptc = new ProductType_Class();
                ptc.setIDType(rs.getString("IDType"));  
                return ptc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
