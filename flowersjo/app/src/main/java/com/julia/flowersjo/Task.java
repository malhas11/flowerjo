package com.julia.flowersjo;

public class Task {
  private String title;
  private String Description;
  private String employee;
  private String Image;

  public Task(){
  }

  public Task(String title, String Description, String Image){
    this.title = title;
    this.Description = Description;
    this.Image = Image;
  }

  public String getImage() {
    return Image;
  }

  public void setImage(String image) {
    Image = image;
  }

  public String getEmployee() {
    return employee;
  }

  public void setEmployee(String employee) {
    this.employee = employee;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return Description;
  }

  public void setDescription(String description) {
    Description = description;
  }


}
