package com.example.quickyjob.modelclass;

public class ShowProfileDataResponseModel {
       String email,mobile;



    public ShowProfileDataResponseModel(String email, String mobile) {
        this.email = email;
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
