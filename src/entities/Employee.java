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
public class Employee {
    private String UsernameEmp;
    private String NameEmp;
    private String Gender;
    private String Birthday;
    private String Phone;
    private String Email;
    private String Address;
    private String Hinh;
    private String Password;
    public Employee() {
    }

    public String getUsernameEmp() {
        return UsernameEmp;
    }

    public void setUsernameEmp(String UsernameEmp) {
        this.UsernameEmp = UsernameEmp;
    }


    public String getNameEmp() {
        return NameEmp;
    }

    public void setNameEmp(String NameEmp) {
        this.NameEmp = NameEmp;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String Birthday) {
        this.Birthday = Birthday;
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

    public String getAddress() {
        return Address;
    }

    public Employee(String UsernameEmp, String Password, String NameEmp, String Gender, String Birthday, String Phone, String Email, String Address, String Hinh) {
        this.UsernameEmp = UsernameEmp;
        this.NameEmp = NameEmp;
        this.Gender = Gender;
        this.Birthday = Birthday;
        this.Phone = Phone;
        this.Email = Email;
        this.Address = Address;
        this.Hinh = Hinh;
        this.Password = Password;
    }

    public Employee(String UsernameEmp, String NameEmp, String Gender, String Birthday, String Phone, String Email, String Address, String Hinh) {
        this.UsernameEmp = UsernameEmp;
        this.NameEmp = NameEmp;
        this.Gender = Gender;
        this.Birthday = Birthday;
        this.Phone = Phone;
        this.Email = Email;
        this.Address = Address;
        this.Hinh = Hinh;
    }

    

    public Employee(String UsernameEmp, String Phone, String Email, String Address, String Password) {
        this.UsernameEmp = UsernameEmp;
        this.Phone = Phone;
        this.Email = Email;
        this.Address = Address;
        this.Password = Password;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
}
