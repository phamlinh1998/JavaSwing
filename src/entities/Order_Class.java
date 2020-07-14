/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Dell Vostro
 */
public class Order_Class {

    private String IDOrder;
    private String IDProduct;
    private String CusName;
    private int Quantity;
    private String NamePromo;
    private String TimeOrder;
    private String DateOrder;
    private String UsernameEmp;

    public Order_Class() {
    }

    public String getIDOrder() {
        return IDOrder;
    }

    public void setIDOrder(String IDOrder) {
        this.IDOrder = IDOrder;
    }

    public String getIDProduct() {
        return IDProduct;
    }

    public void setIDProduct(String IDProduct) {
        this.IDProduct = IDProduct;
    }

    public String getCusName() {
        return CusName;
    }

    public void setCusName(String CusName) {
        this.CusName = CusName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }



    public String getNamePromo() {
        return NamePromo;
    }

    public void setNamePromo(String NamePromo) {
        this.NamePromo = NamePromo;
    }

    public String getTimeOrder() {
        return TimeOrder;
    }

    public void setTimeOrder(String TimeOrder) {
        this.TimeOrder = TimeOrder;
    }

    public String getDateOrder() {
        return DateOrder;
    }

    public void setDateOrder(String DateOrder) {
        this.DateOrder = DateOrder;
    }

    public String getUsernameEmp() {
        return UsernameEmp;
    }

    public void setUsernameEmp(String UsernameEmp) {
        this.UsernameEmp = UsernameEmp;
    }

    public Order_Class(String IDOrder, String IDProduct, int Quantity, String NamePromo, String TimeOrder, String DateOrder, String UsernameEmp) {
        this.IDOrder = IDOrder;
        this.IDProduct = IDProduct;
        this.Quantity = Quantity;
        this.NamePromo = NamePromo;
        this.TimeOrder = TimeOrder;
        this.DateOrder = DateOrder;
        this.UsernameEmp = UsernameEmp;
    }

    public Order_Class(String IDOrder, String IDProduct, String CusName, String NamePromo, String DateOrder, String UsernameEmp) {
        this.IDOrder = IDOrder;
        this.IDProduct = IDProduct;
        this.CusName = CusName;
        this.NamePromo = NamePromo;
        this.DateOrder = DateOrder;
        this.UsernameEmp = UsernameEmp;
    }

    
       
}
