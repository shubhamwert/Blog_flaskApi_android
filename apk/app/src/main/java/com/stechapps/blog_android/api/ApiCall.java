package com.stechapps.blog_android.api;

import com.google.gson.JsonObject;
import com.stechapps.blog_android.Controller.CreateNewUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiCall {

    @Headers("Content-type: application/json")
    @GET("/info_time")
    Call<ApiResponse> m(@Header("Authorization") String Credential);

    @Headers("Content-type: application/json")
    @POST("/create_blog")
    Call<CreateBlogModel> create_blog(@Body JsonObject body);

    @Headers("Content-type:application/json")
    @POST("/create_newuser")
    Call<NewUser> createUser(@Body JsonObject body);

    @Headers("Content-type:application/json")
    @GET("/get_blog_self")
    Call<get_blog_response> get_blogs(@Query("u") String name ,@Header("Authorization") String Credential);

    @GET("/sign_up")
    Call<NewUser> LoginUser(@Header("Authorization") String Credential);
}
