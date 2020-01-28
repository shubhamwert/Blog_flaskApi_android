package com.stechapps.blog_android.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.stechapps.blog_android.Controller.CreateNewUser;
import com.stechapps.blog_android.R;

public class signUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

    }

    public void newUserCreated(View view) {
        EditText ed=findViewById(R.id.name);
        EditText ed2=findViewById(R.id.pass1);
        JsonObject j=new JsonObject();
        try {
            j.addProperty("name", ed.getText().toString());
            j.addProperty("password",ed2.getText().toString());

        }
        catch (Exception e){
            Toast.makeText(this, "try again", Toast.LENGTH_SHORT).show();
            return;
        }
        CreateNewUser c=new CreateNewUser(signUpActivity.this);
        c.OnStart(j);
        finish();

    }
}
