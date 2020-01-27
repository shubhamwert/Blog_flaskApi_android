package com.stechapps.blog_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.stechapps.blog_android.Controller.CheckApiConnectivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void makeCall(View view) {
        CheckApiConnectivity c=new CheckApiConnectivity(MainActivity.this);
        c.onStart();
    }
}
