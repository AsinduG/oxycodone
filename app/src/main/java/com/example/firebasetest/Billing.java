package com.example.firebasetest;

public class Billing {

    private String name;
    private String address;
    private String postalcode;
    private String phone;
    private String email;

    public Billing(){

    }

    public Billing(String name, String address, String postalcode, String phone, String email) {
        this.name = name;
        this.address = address;
        this.postalcode = postalcode;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}