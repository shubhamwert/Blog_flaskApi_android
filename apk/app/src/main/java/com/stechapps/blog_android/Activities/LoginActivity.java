package com.stechapps.blog_android.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.stechapps.blog_android.MainActivity;
import com.stechapps.blog_android.R;
import com.stechapps.blog_android.api.ApiCall;
import com.stechapps.blog_android.api.CreateBlogModel;
import com.stechapps.blog_android.api.NewUser;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    public void verify_password(View view) {
        String base_url="http://192.168.43.219:5000";

        final EditText ed=findViewById(R.id.username_login);
        final EditText ed2=findViewById(R.id.password_login);
        Retrofit retrofit=new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build();
        ApiCall a=retrofit.create(ApiCall.class);
        Call<NewUser> call=a.LoginUser(Credentials.basic(ed.getText().toString(),ed2.getText().toString()));
        call.enqueue(new Callback<NewUser>() {
            @Override
            public void onResponse(Call<NewUser> call, Response<NewUser> response) {

                if(response.body().isResponse()){
                   Toast.makeText(LoginActivity.this, " welcome "+response.body().getName()+" ", Toast.LENGTH_SHORT).show();
                   Intent i=new Intent(LoginActivity.this, MainActivity.class);
                   i.putExtra("token",response.body().getToken().toString());
                   startActivity(i);


                }
                else{
                    Toast.makeText(LoginActivity.this, "response is negative", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewUser> call, Throwable t) {
                Toast.makeText(LoginActivity.this, ""+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void signUp(View view) {
        startActivity(new Intent(LoginActivity.this,signUpActivity.class));
    }
}
