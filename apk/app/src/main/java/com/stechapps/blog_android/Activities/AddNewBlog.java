package com.stechapps.blog_android.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.JsonObject;
import com.stechapps.blog_android.Controller.CreateBlogApiCall;
import com.stechapps.blog_android.MainActivity;
import com.stechapps.blog_android.R;

public class AddNewBlog extends AppCompatActivity {
    String u;
    static public String t;
    public static String baseUrl="http://192.168.43.219:5000";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_blog);

        u = getIntent().getStringExtra("username");
        t=getIntent().getStringExtra("token");



    }
    public void makeCall(View view) {

        CreateBlogApiCall c=new CreateBlogApiCall(AddNewBlog.this);
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
        finish();
    }
}
