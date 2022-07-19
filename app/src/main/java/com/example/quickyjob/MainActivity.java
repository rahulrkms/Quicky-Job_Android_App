package com.example.quickyjob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quickyjob.activity.ForgetPassword;
import com.example.quickyjob.activity.ResetForgetPaasword;
import com.example.quickyjob.activity.SecurityQuestion;
import com.example.quickyjob.modelclass.login_response_model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText main_ed_email,main_ed_password;
    Button main_btn_login;
    TextView main_tv_login_page,main_tv_login_report,main_forget_password_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        main_ed_email = findViewById(R.id.main_ed_email);
        main_ed_password = findViewById(R.id.main_ed_password);
        main_btn_login = findViewById(R.id.main_btn_login);
        main_tv_login_page = findViewById(R.id.main_tv_login_page);
        main_tv_login_report = findViewById(R.id.main_tv_login_report);
        main_forget_password_tv = findViewById(R.id.main_forget_password_tv);

        verifyuserexistance();


        main_forget_password_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 startActivity(new Intent(getApplicationContext(), ForgetPassword.class));
            }
        });
        main_tv_login_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),register.class));
                finish();
            }
        });

        main_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userlogin(main_ed_email.getText().toString(),main_ed_password.getText().toString());


            }
        });

    }
    public void userlogin(String email, String password){
        Call<login_response_model> call1 = apicontroller.getInstance().getapi().getlogin(email, password);
        call1.enqueue(new Callback<login_response_model>() {
            @Override
            public void onResponse(Call<login_response_model> call, Response<login_response_model> response) {
                login_response_model obj = response.body();
                String result = obj.getMessage().trim();

                // Shared Preferences
                if (result.equals("exist")){
                    SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("email",email);
                    editor.putString("password",password);
                    editor.commit();
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(),dashboard.class);
                   // intent.putExtra("getemaili",main_ed_email.getText().toString());
                    startActivity(intent);
                    finish();
                }
                if (result.equals("notexist")){
                    main_tv_login_report.setText("email/password wrong");
                    main_tv_login_report.setTextColor(Color.RED);
                }
            }

            @Override
            public void onFailure(Call<login_response_model> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void verifyuserexistance() {
        SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
        if (sp.contains("email")){
            Intent intent1 = new Intent(getApplicationContext(),dashboard.class);
            startActivity(intent1);
        }


    }
}