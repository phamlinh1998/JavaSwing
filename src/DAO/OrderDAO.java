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
        String sql = "select Orders.IDOrder,IDProduct,IDCus,Quantity,NamePromo,TimeOrder,DateOrder,UsernameEmp "
                + "from OrderDetails join Orders on OrderDetails.IDOrder=Orders.IDOrder Order by OrderDetails.IDOrder DESC";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order_Class oc = new Order_Class();
                oc.setIDOrder(rs.getString("IDOrder"));
                oc.setIDProduct(rs.getString("IDProduct"));
                oc.setCusName(rs.getString("IDCus"));
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

    public List<Order_Class> TimKiem(String IDOrder, String IDProduct, String CusName, String NamePromo, String DateOrder, String UsernameEmp) {
        String sql = "select OrderDetails.IDOrder,IDProduct,CusName,Quantity,NamePromo,TimeOrder,DateOrder,UsernameEmp from OrderDetails join Orders on OrderDetails.IDOrder=Orders.IDOrder where";
        List<Order_Class> data = new ArrayList<>();
        try {
            if (IDOrder != "") {
                sql = sql + " OrderDetails.IDOrder LIKE IDOrder";
            } else if (IDProduct != "") {
                sql = sql + " OrderDetails.IDProduct LIKE IDProduct";
            } else if (CusName != "") {
                sql = sql + " OrderDetails.CusName LIKE CusName ";
            } else if (NamePromo != "") {
                sql = sql + " OrderDetails.NamePromo LIKE NamePromo";
            } else if (DateOrder != "") {
                sql = sql + " Orders.DateOrder LIKE DateOrder";
            } else if (UsernameEmp != "") {
                sql = sql + " Orders.UsernameEmp LIKE UsernameEmp ";
            }
            PreparedStatement stm = conn.prepareStatement(sql);
//            stm.setString(1, IDOrder);
//            stm.setString(2, IDProduct);
//            stm.setString(3, CusName);
//            stm.setString(4, NamePromo);
//            stm.setString(5, DateOrder);
//            stm.setString(6, UsernameEmp);
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
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<Order_Class> TimKiem_IDOrder(String IDOrder) {
        String sql = "select Orders.IDOrder,IDProduct,IDCus,Quantity,NamePromo,TimeOrder,DateOrder,UsernameEmp from OrderDetails join [Orders] on OrderDetails.IDOrder=[Orders].IDOrder "
                + "Where OrderDetails.IDOrder LIKE ?";
        List<Order_Class> data = new ArrayList<>();
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, IDOrder);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order_Class oc = new Order_Class();
                oc.setIDOrder(rs.getString("IDOrder"));
                oc.setIDProduct(rs.getString("IDProduct"));
                oc.setCusName(rs.getString("IDCus"));
                oc.setQuantity(rs.getInt("Quantity"));
                oc.setNamePromo(rs.getString("NamePromo"));
                oc.setTimeOrder(rs.getString("TimeOrder"));
                oc.setDateOrder(rs.getString("DateOrder"));
                oc.setUsernameEmp(rs.getString("UsernameEmp"));
                data.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<Order_Class> TimKiem_IDProduct(String IDProduct) {
        String sql = "select Orders.IDOrder,IDProduct,IDCus,Quantity,NamePromo,TimeOrder,DateOrder,UsernameEmp from OrderDetails join [Orders] on OrderDetails.IDOrder=[Orders].IDOrder "
                + "Where OrderDetails.IDProduct LIKE ?";
        List<Order_Class> data = new ArrayList<>();
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, IDProduct);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order_Class oc = new Order_Class();
                oc.setIDOrder(rs.getString("IDOrder"));
                oc.setIDProduct(rs.getString("IDProduct"));
                oc.setCusName(rs.getString("IDCus"));
                oc.setQuantity(rs.getInt("Quantity"));
                oc.setNamePromo(rs.getString("NamePromo"));
                oc.setTimeOrder(rs.getString("TimeOrder"));
                oc.setDateOrder(rs.getString("DateOrder"));
                oc.setUsernameEmp(rs.getString("UsernameEmp"));
                data.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<Order_Class> TimKiem_NamePromo(String NamePromo) {
        String sql = "select Orders.IDOrder,IDProduct,IDCus,Quantity,NamePromo,TimeOrder,DateOrder,UsernameEmp from OrderDetails join [Orders] on OrderDetails.IDOrder=[Orders].IDOrder "
                + "Where OrderDetails.NamePromo LIKE ? Order By Orders.IDOrder DESC";
        List<Order_Class> data = new ArrayList<>();
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, NamePromo);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order_Class oc = new Order_Class();
                oc.setIDOrder(rs.getString("IDOrder"));
                oc.setIDProduct(rs.getString("IDProduct"));
                oc.setCusName(rs.getString("IDCus"));
                oc.setQuantity(rs.getInt("Quantity"));
                oc.setNamePromo(rs.getString("NamePromo"));
                oc.setTimeOrder(rs.getString("TimeOrder"));
                oc.setDateOrder(rs.getString("DateOrder"));
                oc.setUsernameEmp(rs.getString("UsernameEmp"));
                data.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<Order_Class> TimKiem_DateOrder(String DateOrder) {
        String sql = "select Orders.IDOrder,IDProduct,IDCus,Quantity,NamePromo,TimeOrder,DateOrder,UsernameEmp from OrderDetails join [Orders] on OrderDetails.IDOrder=[Orders].IDOrder "
                + "Where Orders.DateOrder LIKE ?";
        List<Order_Class> data = new ArrayList<>();
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, DateOrder);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order_Class oc = new Order_Class();
                oc.setIDOrder(rs.getString("IDOrder"));
                oc.setIDProduct(rs.getString("IDProduct"));
                oc.setCusName(rs.getString("IDCus"));
                oc.setQuantity(rs.getInt("Quantity"));
                oc.setNamePromo(rs.getString("NamePromo"));
                oc.setTimeOrder(rs.getString("TimeOrder"));
                oc.setDateOrder(rs.getString("DateOrder"));
                oc.setUsernameEmp(rs.getString("UsernameEmp"));
                data.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<Order_Class> TimKiem_UsernameEmp(String UsernameEmp) {
        String sql = "select Orders.IDOrder,IDProduct,IDCus,Quantity,NamePromo,TimeOrder,DateOrder,UsernameEmp from OrderDetails join [Orders] on OrderDetails.IDOrder=[Orders].IDOrder "
                + "Where [Orders].UsernameEmp LIKE ? Order By Orders.IDOrder DESC";
        List<Order_Class> data = new ArrayList<>();
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, UsernameEmp);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order_Class oc = new Order_Class();
                oc.setIDOrder(rs.getString("IDOrder"));
                oc.setIDProduct(rs.getString("IDProduct"));
                oc.setCusName(rs.getString("IDCus"));
                oc.setQuantity(rs.getInt("Quantity"));
                oc.setNamePromo(rs.getString("NamePromo"));
                oc.setTimeOrder(rs.getString("TimeOrder"));
                oc.setDateOrder(rs.getString("DateOrder"));
                oc.setUsernameEmp(rs.getString("UsernameEmp"));
                data.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<Order_Class> TimKiem_CusName(String CusName) {
        String sql = "select Orders.IDOrder,IDProduct,IDCus,Quantity,NamePromo,TimeOrder,DateOrder,UsernameEmp from OrderDetails join [Orders] on OrderDetails.IDOrder=[Orders].IDOrder "
                + "Where OrderDetails.CusName LIKE ?";
        List<Order_Class> data = new ArrayList<>();
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, CusName);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order_Class oc = new Order_Class();
                oc.setIDOrder(rs.getString("IDOrder"));
                oc.setIDProduct(rs.getString("IDProduct"));
                oc.setCusName(rs.getString("IDCus"));
                oc.setQuantity(rs.getInt("Quantity"));
                oc.setNamePromo(rs.getString("NamePromo"));
                oc.setTimeOrder(rs.getString("TimeOrder"));
                oc.setDateOrder(rs.getString("DateOrder"));
                oc.setUsernameEmp(rs.getString("UsernameEmp"));
                data.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<Order_Class> getByIDOrder() {
        List<Order_Class> data = new ArrayList<>();
        String sql = "select IDOrder from Orders";
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
        String sql = "select IDProduct from OrderDetails";
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
        String sql = "select distinct IDCus from OrderDetails";
        try {
            CallableStatement stm = conn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order_Class oc = new Order_Class();
                oc.setCusName(rs.getString("IDCus"));
                data.add(oc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<Order_Class> getByNamePromo() {
        List<Order_Class> data = new ArrayList<>();
        String sql = "select distinct  NamePromo from OrderDetails";
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
        String sql = "select DateOrder from OrderDetails";
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
        String sql = "select Distinct UsernameEmp from Orders";
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

    public boolean SaveOrder(String id, String tenNV) {
        String sql = "Insert into Orders values(?,convert(varchar(20),getdate(),103),convert(varchar(20),getdate(),108),?)";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            stm.setString(2, tenNV);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(banhangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void SaveOrderDetails(String IDOrder, String IDProduct, String CusName, int Quantity,String NamePromo) {
        String sql = "Insert into OrderDetails values(?,?,?,?,?)";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, IDOrder);
            stm.setString(2, IDProduct);
            stm.setString(3, CusName);
            stm.setInt(4, Quantity);
            stm.setString(5, NamePromo);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(banhangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
