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
public class Customer_Class {
    private int IDCus;
    private String IdentityCard;
    private String CusName;
    private String Date;
    private String Phone;
    private String Email;
    private int Quantity;
    private int Discount;

    public Customer_Class() {
    }

    public int getIDCus() {
        return IDCus;
    }

    public void setIDCus(int IDCus) {
        this.IDCus = IDCus;
    }
    
    public String getIdentityCard() {
        return IdentityCard;
    }

    public void setIdentityCard(String IdentityCard) {
        this.IdentityCard = IdentityCard;
    }

    public String getCusName() {
        return CusName;
    }

    public void setCusName(String CusName) {
        this.CusName = CusName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getDiscount() {
        return Discount;
    }

    public void setDiscount(int Discount) {
        this.Discount = Discount;
    }

    public Customer_Class(String IdentityCard, String CusName, String Date, String Phone, String Email, int Quantity, int Discount) {
        this.IdentityCard = IdentityCard;
        this.CusName = CusName;
        this.Date = Date;
        this.Phone = Phone;
        this.Email = Email;
        this.Quantity = Quantity;
        this.Discount = Discount;
    }
    
                  
}
