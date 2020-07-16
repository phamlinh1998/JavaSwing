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
public class Promotion_Class {
    private int IDPromo;
    private String NamePromo;
    private int DiscountPromo;
    private String StartPromo;
    private String EndPromo; 
    private String Description;

    public Promotion_Class() {
    }

    public int getIDPromo() {
        return IDPromo;
    }

    public void setIDPromo(int IDPromo) {
        this.IDPromo = IDPromo;
    }

    public String getNamePromo() {
        return NamePromo;
    }

    public void setNamePromo(String NamePromo) {
        this.NamePromo = NamePromo;
    }

    public int getDiscountPromo() {
        return DiscountPromo;
    }

    public void setDiscountPromo(int DiscountPromo) {
        this.DiscountPromo = DiscountPromo;
    }

    public String getStartPromo() {
        return StartPromo;
    }

    public void setStartPromo(String StartPromo) {
        this.StartPromo = StartPromo;
    }

    public String getEndPromo() {
        return EndPromo;
    }

    public void setEndPromo(String EndPromo) {
        this.EndPromo = EndPromo;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

}
