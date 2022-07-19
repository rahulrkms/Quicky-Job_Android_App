package com.example.quickyjob.modelclass;

public class ForgetPasswordResponseModel {
    String message;

    public ForgetPasswordResponseModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
