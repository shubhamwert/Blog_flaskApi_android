package com.stechapps.blog_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.stechapps.blog_android.Activities.Blogs_List;
import com.stechapps.blog_android.Activities.NewUserActivity;
import com.stechapps.blog_android.Controller.CheckApiConnectivity;
import com.stechapps.blog_android.Controller.CreateBlogApiCall;

public class MainActivity extends AppCompatActivity {
public static String baseUrl="http://192.168.43.219:5000";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    public void makeCall(View view) {

        CreateBlogApiCall c=new CreateBlogApiCall(MainActivity.this);
        EditText writer=findViewById(R.id.Writter_name_1);
        EditText blog=findViewById(R.id.blog);
        JsonObject j= new JsonObject();
       try {
           j.addProperty("blog_writter", writer.getText().toString());
           j.addProperty("blog", blog.getText().toString());
       }catch (Exception e){
           Log.e("MAIN ACTIVITY", "makeCall: "+e);
       }

        c.onStart(j);
    }

    public void NewUser(View view) {
        startActivity(new Intent(MainActivity.this, NewUserActivity.class));

    }

    public void ViewBlogs(View view) {
        startActivity(new Intent(MainActivity.this, Blogs_List.class));
    }
    public void CheckConnectivity(View view){
        CheckApiConnectivity checkApiConnectivity=new CheckApiConnectivity(this);
        checkApiConnectivity.onStart();

    }
}
