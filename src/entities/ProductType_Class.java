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
public class ProductType_Class {
    private String IDType;
    private String TypeName;
    private String Size;

    public ProductType_Class() {
    }

    public ProductType_Class(String IDType, String TypeName, String Size) {
        this.IDType = IDType;
        this.TypeName = TypeName;
        this.Size = Size;
    }

    public String getIDType() {
        return IDType;
    }

    public void setIDType(String IDType) {
        this.IDType = IDType;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String TypeName) {
        this.TypeName = TypeName;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }

    @Override
    public String toString() {
        return this.TypeName;
    }
    
}
