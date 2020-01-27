package com.stechapps.blog_android.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.stechapps.blog_android.R;
import com.stechapps.blog_android.api.ApiCall;
import com.stechapps.blog_android.api.get_blog_response;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Blogs_List extends AppCompatActivity {
    public static String baseUrl="http://192.168.43.219:5000";
    String TAG="HHHSABFHSF";
    String u,p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogs__list);
        u = getIntent().getStringExtra("username");
        p = getIntent().getStringExtra("password");
        call_blogs();


    }

    private void call_blogs() {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        ApiCall apiCall=retrofit.create(ApiCall.class);
        Toast.makeText(Blogs_List.this, " "+p+" "+u, Toast.LENGTH_SHORT).show();
        Call<get_blog_response> blog_response=apiCall.get_blogs(u, Credentials.basic(u,p));
        blog_response.enqueue(new Callback<get_blog_response>() {
            @Override
            public void onResponse(Call<get_blog_response> call, Response<get_blog_response> response) {
                if(!response.body().getResponse().equals("success"))
                {
                    Toast.makeText(Blogs_List.this, ""+response.body().getResponse(), Toast.LENGTH_SHORT).show();
                    return;
                }
                TextView tv=findViewById(R.id.mView);
                tv.setText("");
                for(int i=0;i<response.body().getContent().size();i++){
                    tv.append(response.body().getContent().get(i));
                    tv.append("\n");
                }
            }

            @Override
            public void onFailure(Call<get_blog_response> call, Throwable t) {
                Log.d(TAG, "onResponse: "+t.toString());

            }
        });
    }
}
