package com.stechapps.blog_android.api;

import com.google.gson.annotations.SerializedName;

public class NewUser {
    @SerializedName("name")
    private String name;
    @SerializedName("password")
    private String password;

    public NewUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
