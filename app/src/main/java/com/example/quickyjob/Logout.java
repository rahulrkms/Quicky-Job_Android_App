package com.example.quickyjob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Logout extends AppCompatActivity {

    Button btnlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);


        btnlogout=findViewById(R.id.btnlogout);

       btnlogout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
               SharedPreferences.Editor editor = sp.edit();
               editor.remove("email").commit();
               editor.remove("password").commit();
               editor.apply();
               startActivity(new Intent(getApplicationContext(),MainActivity.class));
               finish();
           }
       });



    }

}