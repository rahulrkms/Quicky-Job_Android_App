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

import com.example.quickyjob.activity.SecurityQuestion;
import com.example.quickyjob.modelclass.signup_response_model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity {

    EditText reg_ed_email,reg_ed_mobile,reg_ed_password,reg_name;
    Button reg_btn_register;
    TextView reg_tv_signin,reg_tv_signup_report;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        reg_ed_email = findViewById(R.id.reg_et_email);
        reg_ed_mobile = findViewById(R.id.regmoba);
        reg_ed_password = findViewById(R.id.regpass);
        reg_btn_register = findViewById(R.id.reg_btn_register);
        reg_tv_signin = findViewById(R.id.reg_tv_signin);
        reg_tv_signup_report = findViewById(R.id.reg_tv_signup_report);
        reg_name=findViewById(R.id.reg_name);


        reg_tv_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

        reg_btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userregister(reg_ed_email.getText().toString(),reg_ed_password.getText().toString()
                        ,reg_ed_mobile.getText().toString(),reg_name.getText().toString());
            }
        });

    }

    public void userregister(String email, String password, String mobile,String name){
        String address = "not applicable";
        Call<signup_response_model> call = apicontroller.getInstance().getapi().getregister(
                name,email,password,mobile,address);



        call.enqueue(new Callback<signup_response_model>() {
            @Override
            public void onResponse(Call<signup_response_model> call, Response<signup_response_model> response) {
                signup_response_model obj = response.body();
                String result = obj.getMessage().trim();
                if (result.equals("inserted")){
                    reg_tv_signup_report.setText("Sucessfully Resigtered");
                    reg_tv_signup_report.setTextColor(Color.GREEN);
                    reg_ed_email.setText("");
                    reg_ed_mobile.setText("");
                    reg_ed_password.setText("");
                    reg_name.setText("");

                    Intent intent = new Intent(getApplicationContext(),SecurityQuestion.class);
                    intent.putExtra("emailFromReg",email);
                    startActivity(intent);
                    finish();
                }
                if (result.equals("userexits")){
                    reg_tv_signup_report.setText("Already Registered");
                    reg_tv_signup_report.setTextColor(Color.RED);
                    reg_ed_email.setText("");
                    reg_ed_mobile.setText("");
                    reg_ed_password.setText("");
                    reg_name.setText("");

                }
            }

            @Override
            public void onFailure(Call<signup_response_model> call, Throwable t) {
                Toast.makeText(register.this, t.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}