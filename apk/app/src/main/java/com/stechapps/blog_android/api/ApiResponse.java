package com.stechapps.blog_android.api;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("success_date")
    private String s;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
