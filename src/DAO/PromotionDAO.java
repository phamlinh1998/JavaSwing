/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
public class PromotionDAO {

    Connection conn = ConnectionDB.getConnection();

    public List<Promotion_Class> getAll() {
        List<Promotion_Class> data = new ArrayList<>();
        String sql = "Select * from Promotions ";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Promotion_Class pc = new Promotion_Class();
                pc.setIDPromo(rs.getInt("IDPromo"));
                pc.setNamePromo(rs.getString("NamePromo"));
                pc.setDiscountPromo(rs.getInt("DiscountPromo"));
                pc.setStartPromo(rs.getString("StartPromo"));
                pc.setEndPromo(rs.getString("EndPromo"));
                pc.setDescription(rs.getString("Description"));
                data.add(pc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    public void them(String NamePromo,int DiscountPromo,String StartPromo,String EndPromo,String Description ) {
        String sql = "Insert into Promotions values(?,?,?,?,?)";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            stm.setString(1, NamePromo);
            stm.setInt(2, DiscountPromo);
            stm.setString(3, StartPromo);
            stm.setString(4, EndPromo);
            stm.setString(5, Description);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void capnhat(int ck,String date_start,String date_End,String mota,String ma) {
        String sql = "update Promotions set DiscountPromo=?,StartPromo=?,EndPromo=?,Description=? where IDPromo=?";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            stm.setInt(1, ck);
            stm.setString(2, date_start);
            stm.setString(3, date_End);
            stm.setString(4, mota);
            stm.setString(5, ma);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void xoa(String ma) {
        String sql = "delete Promotions where IDPromo=?";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            stm.setString(1, ma);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
