package com.stechapps.blog_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.stechapps.blog_android.Activities.AddNewBlog;
import com.stechapps.blog_android.api.ApiCall;
import com.stechapps.blog_android.api.get_blog_response;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static String baseUrl="http://192.168.43.219:5000";
    String TAG="HHHSABFHSF";
    String u,t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        u = getIntent().getStringExtra("username");
        t=getIntent().getStringExtra("token");
        call_blogs();


    }

    private void call_blogs() {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        ApiCall apiCall=retrofit.create(ApiCall.class);
        Call<get_blog_response> blog_response=apiCall.get_blogs(u, Credentials.basic(t,"some text"));
        blog_response.enqueue(new Callback<get_blog_response>() {
            @Override
            public void onResponse(Call<get_blog_response> call, Response<get_blog_response> response) {
                if(!response.body().isResponse())
                {
                    Toast.makeText(MainActivity.this, "Error ", Toast.LENGTH_SHORT).show();
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


    public void addNewBlog(View view) {
        Intent i=new Intent(MainActivity.this, AddNewBlog.class);
        i.putExtra("username",u);
        i.putExtra("token",t);
        startActivity(i);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        call_blogs();
    }
}
