package com.stechapps.blog_android.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiCall {
    @Headers("Content-type: application/json")
    @GET("/info_time")
    Call<ApiResponse> m();

}
