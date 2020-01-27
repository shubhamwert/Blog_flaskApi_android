package com.stechapps.blog_android.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class get_blog_response {
    @SerializedName("response")
    private String response;

    @SerializedName("name")
    private String name;

    @SerializedName("content")
    private List<String> content=new ArrayList<String>();

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public get_blog_response(String response, String name, List<String> content) {
        this.response = response;
        this.name = name;
        this.content = content;
    }
}
