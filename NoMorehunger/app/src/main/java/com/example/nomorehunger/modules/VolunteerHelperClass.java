package com.example.nomorehunger.modules;

public class VolunteerHelperClass {

    String name,phone,desc,address;
    public VolunteerHelperClass(String name, String phone, String desc, String address){
        this.name = name;
        this.phone = phone;
        this.desc = desc;
        this.address = address;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
