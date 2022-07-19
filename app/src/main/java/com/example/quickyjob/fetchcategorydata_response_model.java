package com.example.quickyjob;

public class fetchcategorydata_response_model {
    String catname;
    String error;

    public fetchcategorydata_response_model(String catname, String error) {
        this.catname = catname;
        this.error = error;
    }

    public fetchcategorydata_response_model() {
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
