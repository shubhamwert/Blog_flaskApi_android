package com.stechapps.blog_android.Controller;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.stechapps.blog_android.api.ApiCall;
import com.stechapps.blog_android.api.NewUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateNewUser implements Callback<NewUser> {


    public CreateNewUser(Context mContext) {
        this.mContext = mContext;
    }

    private static String base_url="http://192.168.43.219:5000";
    private Context mContext;
    public void OnStart(JsonObject j){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build();
        ApiCall apiCall=retrofit.create(ApiCall.class);
        Call<NewUser> newUserCall=apiCall.createUser(j);
        newUserCall.enqueue(this);

    }

    @Override
    public void onResponse(Call<com.stechapps.blog_android.api.NewUser> call, Response<com.stechapps.blog_android.api.NewUser> response) {
        if(response.isSuccessful()){
            Toast.makeText(mContext, "New user Created"+response.body().getName(), Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(mContext, "unable to create user"+response.body().getName(), Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onFailure(Call<com.stechapps.blog_android.api.NewUser> call, Throwable t) {
        Toast.makeText(mContext, "Error", Toast.LENGTH_SHORT).show();

    }


}
