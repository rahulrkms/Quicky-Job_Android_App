package com.example.quickyjob;

public class login_response_model {

    String message;

    public login_response_model(String message) {
        this.message = message;
    }

    public login_response_model() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
