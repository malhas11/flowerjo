package com.julia.flowersjo;

public class orders {
    private String orderID, image, quantity, time, date, from, to, message;

    public orders(String id, String image, String quantity, String time, String date, String from, String to, String message){

        this.orderID = id;
        this.image = image;
        this.quantity = quantity;
        this.time = time;
        this.date = date;
        this.from = from;
        this.to = to;
        this.message = message;


    }
    public orders(){

    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
