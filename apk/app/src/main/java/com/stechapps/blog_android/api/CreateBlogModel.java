package com.stechapps.blog_android.api;

import com.google.gson.annotations.SerializedName;

public class CreateBlogModel {
    @SerializedName("blog_writer")
    private String blogWriter;

    @SerializedName("blog")
    private String blog;

    public CreateBlogModel(String blogWriter, String blog) {
        this.blogWriter = blogWriter;
        this.blog = blog;
    }

    public String getBlogWriter() {
        return blogWriter;
    }

    public void setBlogWriter(String blogWriter) {
        this.blogWriter = blogWriter;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }
}
