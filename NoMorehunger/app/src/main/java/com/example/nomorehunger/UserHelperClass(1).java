package com.example.nomorehunger;

public class UserHelperClass {

    String name,phone,address,acc_type,password;

    public UserHelperClass(String name, String phone, String address, String acc_type, String password){
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.acc_type = acc_type;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAcc_type() {
        return acc_type;
    }

    public void setAcc_type(String acc_type) {
        this.acc_type = acc_type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
