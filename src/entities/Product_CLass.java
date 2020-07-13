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
public class Product_CLass {
    private String IDProduct;
    private String ProductName;
    private String IDType;
    private int Price;
    private String Size;
    
    public Product_CLass() {
    }

    public Product_CLass(String IDProduct, String ProductName, String IDType, int Price, String Size) {
        this.IDProduct = IDProduct;
        this.ProductName = ProductName;
        this.IDType = IDType;
        this.Price = Price;
        this.Size = Size;
    }

    public Product_CLass(String IDProduct, String ProductName, String IDType, int Price) {
        this.IDProduct = IDProduct;
        this.ProductName = ProductName;
        this.IDType = IDType;
        this.Price = Price;
    }

  

    public String getIDProduct() {
        return IDProduct;
    }

    public void setIDProduct(String IDProduct) {
        this.IDProduct = IDProduct;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getIDType() {
        return IDType;
    }

    public void setIDType(String IDType) {
        this.IDType = IDType;
    }

    public int getPrice() {
        return Price;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }
    
}
