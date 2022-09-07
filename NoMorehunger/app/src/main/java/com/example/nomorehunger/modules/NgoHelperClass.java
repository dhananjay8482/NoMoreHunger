package com.example.nomorehunger.modules;

public class NgoHelperClass {

    String name,phone,desc;
    public NgoHelperClass(String name, String phone, String desc){
        this.name = name;
        this.phone = phone;
        this.desc = desc;
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
}
