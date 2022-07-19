package com.example.quickyjob.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quickyjob.R;

import org.w3c.dom.Text;

public class Settings extends AppCompatActivity {

    TextView password_chnge,helpAndSupport,updateApp,aboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        password_chnge=findViewById(R.id.password_chnge);
        updateApp=findViewById(R.id.updateApp);
        helpAndSupport=findViewById(R.id.helpAndSupport);
        aboutUs=findViewById(R.id.aboutUs);
        
        pass_change();
        helpSupport();
        updateApplink();
        aboutUsSection();


    }

    private void aboutUsSection() {
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Settings.this, "This is about us section", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void updateApplink() {
        updateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Settings.this, "Paste App Update Link", Toast.LENGTH_SHORT).show();            }
        });

    }

    private void helpSupport() {
        helpAndSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Settings.this, "Help And Support Link", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void pass_change() {
        password_chnge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(Settings.this, "send to password chnge activity", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),changePassword.class);
                startActivity(intent);

            }
        });
    

    }
}