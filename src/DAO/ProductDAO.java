/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.ProductType_Class;
import entities.Thongke_class;
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
import java.sql.PreparedStatement;

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
            String sql = "insert into Product values (?,?,?,?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, p.getIDProduct());
            stm.setString(2, p.getProductName());
            stm.setString(3, p.getIDType());
            stm.setInt(4, p.getPrice());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void capnhat(String ten,String loai,int gia,String id) {
        try {
            String sql = "update Product set ProductName=?, IDType=?, Price=? where IDProduct=?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, ten);
            stm.setString(2, loai);
            stm.setInt(3, gia);
            stm.setString(4, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Product_CLass> getByProductName(String tensp) {
        //String sql = "select * from Product p inner join ProductType pt on p.IDType = pt.IDType where ProductName like ?";
        String sql="{call timkiemSPtheoTen (?) }";
        List<Product_CLass> data = new ArrayList<>();
        try {
            CallableStatement cs = conn.prepareCall(sql);
            cs.setString(1, tensp);
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

    public List<Product_CLass> getTenSP() {
        List<Product_CLass> data = new ArrayList<>();
        String sql = "Select DISTINCT ProductName from Product";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product_CLass p = new Product_CLass();
                p.setProductName(rs.getString("ProductName"));
                data.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<Thongke_class> getAllSP() {
        List<Thongke_class> data = new ArrayList<>();
        String sql = "select * from OrderDetails join Orders on OrderDetails.IDOrder=Orders.IDOrder join Product on OrderDetails.IDProduct=Product.IDProduct Order by OrderDetails.IDOrder DESC";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Thongke_class tc = new Thongke_class();
                tc.setIDOrder(rs.getString("IDOrder"));
                tc.setProductName(rs.getString("ProductName"));
                tc.setPrice(rs.getInt("Price"));
                tc.setIDType(rs.getString("IDType"));
                tc.setQuantity(rs.getInt("Quantity"));
                data.add(tc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<Thongke_class> Timkiem_Thongke(String tensp) {
        List<Thongke_class> data = new ArrayList<>();
        String sql = "select * from OrderDetails join Orders on OrderDetails.IDOrder=Orders.IDOrder join Product on OrderDetails.IDProduct=Product.IDProduct where ProductName=?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, tensp);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Thongke_class tc = new Thongke_class();
                tc.setIDOrder(rs.getString("IDOrder"));
                tc.setProductName(rs.getString("ProductName"));
                tc.setPrice(rs.getInt("Price"));
                tc.setIDType(rs.getString("IDType"));
                tc.setQuantity(rs.getInt("Quantity"));
                data.add(tc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<Thongke_class> Timkiem_Thongke_theoGia() {
        List<Thongke_class> data = new ArrayList<>();
        String sql = "select o.IDProduct, p.Price,p.ProductName,p.IDType, SUM(o.Quantity) as Tong  from OrderDetails o join Product p on o.IDProduct=p.IDProduct Group by o.IDProduct,p.Price,p.ProductName,p.IDType order by Tong DESC";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Thongke_class tc = new Thongke_class();
                tc.setIDOrder(rs.getString("IDProduct"));
                tc.setProductName(rs.getString("ProductName"));
                tc.setPrice(rs.getInt("Price"));
                tc.setIDType(rs.getString("IDType"));
                tc.setQuantity(rs.getInt("Tong"));
                data.add(tc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public List<Thongke_class> Timkiem_Thongke_theoNgay(String date1, String date2) {
        List<Thongke_class> data = new ArrayList<>();
        String sql = "select o.IDProduct, p.Price,p.ProductName,p.IDType,a.DateOrder,o.Quantity from OrderDetails o join Product p on o.IDProduct=p.IDProduct join Orders a on a.IDOrder=o.IDOrder where DateOrder BETWEEN ? and ?";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, date1);
            stm.setString(2, date2);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Thongke_class tc = new Thongke_class();
                tc.setIDOrder(rs.getString("IDProduct"));
                tc.setProductName(rs.getString("ProductName"));
                tc.setPrice(rs.getInt("Price"));
                tc.setIDType(rs.getString("IDType"));
                tc.setDate(rs.getString("DateOrder"));
                tc.setQuantity(rs.getInt("Quantity"));
                data.add(tc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
}
