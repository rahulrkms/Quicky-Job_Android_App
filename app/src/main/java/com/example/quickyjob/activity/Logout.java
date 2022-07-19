package com.example.quickyjob.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quickyjob.MainActivity;
import com.example.quickyjob.R;

public class Logout extends AppCompatActivity {


    Button logoutbtn;
    TextView sentToHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);



        logoutbtn = findViewById(R.id.logoutbtn);
        sentToHome=findViewById(R.id.sentToHome);

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.remove("email");
                editor.remove("password");
                editor.clear();
                editor.apply();
                Intent inst = new Intent(Logout.this,MainActivity.class);
                startActivity(inst);
                finish();

            }
        });

        sentToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });






    }

}