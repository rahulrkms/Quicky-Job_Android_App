package com.example.quickyjob.modelclass;

public class fetchcategorydata_response_model {
    String catname;
    String error;
    String catimage;

    public fetchcategorydata_response_model() {
    }

    public fetchcategorydata_response_model(String catname, String error, String catimage) {
        this.catname = catname;
        this.error = error;
        this.catimage = catimage;
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

    public String getCatimage() {
        return catimage;
    }

    public void setCatimage(String catimage) {
        this.catimage = catimage;
    }
}
