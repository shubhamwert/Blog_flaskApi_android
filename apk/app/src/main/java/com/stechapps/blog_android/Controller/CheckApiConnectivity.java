package com.stechapps.blog_android.Controller;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.stechapps.blog_android.api.ApiCall;
import com.stechapps.blog_android.api.ApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckApiConnectivity implements Callback<ApiResponse> {
private static String base_url="http://192.168.43.219:5000";

private Context context;
private  String re = "";

    public CheckApiConnectivity(Context context) {
        this.context=context;
    }

    public void onStart(){
//    Gson gson=

        Retrofit retrofit=new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build();
        ApiCall a=retrofit.create(ApiCall.class);
        Call<ApiResponse> call=a.m();
        call.enqueue(this);


    }

    @Override
    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
            if (response.isSuccessful()    )
            {
                Toast.makeText(context, "Hurray Response"+response.body().getS(), Toast.LENGTH_SHORT).show();
                re=response.body().getS();
            }
        }


    @Override
    public void onFailure(Call<ApiResponse> call, Throwable t) {
        Log.e("failure", "onFailure: "+t.toString() );
        Toast.makeText(context, "Something not right " +t.toString(), Toast.LENGTH_SHORT).show();

    }
}
