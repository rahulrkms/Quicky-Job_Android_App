package com.example.quickyjob;

public class category_clickListener_response_model {
    String catname, cpname,url,error;

    public category_clickListener_response_model(String catname, String cpname, String url, String error) {
        this.catname = catname;
        this.cpname = cpname;
        this.url = url;
        this.error = error;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
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
