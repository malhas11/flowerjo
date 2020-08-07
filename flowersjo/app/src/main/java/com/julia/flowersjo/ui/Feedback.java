package com.julia.flowersjo.ui;

public class Feedback {
    String firstname, email, phonenumber, feedbackmessage;

    public Feedback(){

    }
    public Feedback(String firstname, String email, String phonenumber, String feedbackmessage){
        this.firstname = firstname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.feedbackmessage = feedbackmessage;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getFeedbackmessage() {
        return feedbackmessage;
    }

    public void setFeedbackmessage(String feedbackmessage) {
        this.feedbackmessage = feedbackmessage;
    }
}
