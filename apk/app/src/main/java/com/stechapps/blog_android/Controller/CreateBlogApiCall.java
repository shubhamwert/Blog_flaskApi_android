package com.stechapps.blog_android.Controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.stechapps.blog_android.api.ApiCall;
import com.stechapps.blog_android.api.CreateBlogModel;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateBlogApiCall implements Callback<CreateBlogModel> {
    private Context context;

    public static String base_url="http://192.168.43.219:5000";
    public CreateBlogApiCall(Context context) {
        this.context=context;
    }

    public void onStart(JsonObject j){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build();
        ApiCall a=retrofit.create(ApiCall.class);
        Call<CreateBlogModel> call=a.create_blog(j);
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<CreateBlogModel> call, Response<CreateBlogModel> response) {
        if (response.isSuccessful())
        { Toast.makeText(context, " Created New Blog", Toast.LENGTH_SHORT).show();  }
    }

    @Override
    public void onFailure(Call<CreateBlogModel> call, Throwable t) {
        Log.e("hehe ","onFailure: "+t.toString());
    }



}
