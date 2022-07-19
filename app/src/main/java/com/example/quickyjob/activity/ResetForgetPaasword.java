package com.example.quickyjob.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quickyjob.MainActivity;
import com.example.quickyjob.R;
import com.example.quickyjob.apicontroller;
import com.example.quickyjob.modelclass.ForgetPasswordResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetForgetPaasword extends AppCompatActivity {
    Button reset_forget_btn;
    EditText reset_forget_new_password,reset_forget_confirm_new_password;
    String email,result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_forget_paasword);

        reset_forget_new_password = findViewById(R.id.reset_forget_new_password);
        reset_forget_confirm_new_password = findViewById(R.id.reset_forget_confirm_new_password);
        reset_forget_btn = findViewById(R.id.reset_forget_btn);

        email = getIntent().getStringExtra("emailFromForget");

        reset_forget_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               forgetResetPassword(reset_forget_new_password.getText().toString(),email);
            }
        });

    }

    public void forgetResetPassword(String newPassword, String email){
        Call<ForgetPasswordResponseModel> call = apicontroller.getInstance().getapi().resetForgetPassword(newPassword,email);
        call.enqueue(new Callback<ForgetPasswordResponseModel>() {
            @Override
            public void onResponse(Call<ForgetPasswordResponseModel> call, Response<ForgetPasswordResponseModel> response) {
                ForgetPasswordResponseModel model = response.body();
                 result = model.getMessage().trim();
                if (result.equals("password updated")){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
                if (result.equals("password not updated")){
                    Toast.makeText(ResetForgetPaasword.this, "Security Question/Ans not matched", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ForgetPasswordResponseModel> call, Throwable t) {

            }
        });
    }
}