package com.julia.flowersjo.ui;

public class clientorder {
    private String address, email, name, city, number, orderid, payment, price, type;

    public clientorder(){

    }
    public clientorder(String address, String email, String name, String city, String number, String orderid, String payment, String price, String type){

        this.address = address;
        this.email = email;
        this.name = name;
        this.city = city;
        this.number = number;
        this.orderid = orderid;
        this.payment = payment;
        this.price = price;
        this.type = type;

    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
}
