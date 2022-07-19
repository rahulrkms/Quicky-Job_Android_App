package com.example.quickyjob.modelclass;

public class SecurityQuestionVerificationResponse {

    String message;

    public SecurityQuestionVerificationResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
