package com.example.quickyjob.modelclass;

public class fetch_dashboard_response_model {
    String cpname;
    //    String eligibility;
//    String sdesc;
    String url;
    String error;

    public fetch_dashboard_response_model(String cpname, String url, String error) {
        this.cpname = cpname;
        this.url = url;
        this.error = error;
    }

    public fetch_dashboard_response_model() {
    }

    public String getCpname() {
        return cpname;
    }

    public void setCpname(String cpname) {
        this.cpname = cpname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}