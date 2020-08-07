package com.julia.flowersjo.ui.pastorders;

public class pastorderdetails {

    private String name, email, number, address, city;

    public pastorderdetails(){

    }
    public pastorderdetails(String name, String email, String number, String address, String city){

        this.name = name;
        this.email = email;
        this.number = number;
        this.address = address;
        this.city = city;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
