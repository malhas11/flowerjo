package com.julia.flowersjo;

public class flower {

    private String title;
    private String description;
    private String Image;

    public flower(String title, String description, String image){
        this.title = title;
        this.description = description;
        this.Image = image;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
