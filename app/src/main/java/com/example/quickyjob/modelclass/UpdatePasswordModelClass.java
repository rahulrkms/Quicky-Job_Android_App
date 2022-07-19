package com.example.quickyjob.modelclass;

public class UpdatePasswordModelClass
{
    String message;

    public UpdatePasswordModelClass(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
