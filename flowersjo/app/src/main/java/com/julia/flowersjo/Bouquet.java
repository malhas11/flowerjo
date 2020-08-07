package com.julia.flowersjo;

public class Bouquet {
    private String price, description, image, type, key;

    public Bouquet(){

    }

    public Bouquet(String price, String description, String image, String type, String key){
        this.price = price;
        this.description = description;
        this.image = image;
        this.type = type;
        this.key = key;

    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
