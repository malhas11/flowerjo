package com.julia.flowersjo;

public class client {
    private String fname, lname, email, pnumber, orderID;


    public client(String fn, String ln, String email, String pn, String id){
        this.fname = fn;
        this.lname = ln;
        this.email = email;
        this.pnumber = pn;
        this.orderID = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
}
