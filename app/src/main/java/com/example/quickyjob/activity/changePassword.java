package com.example.quickyjob.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.quickyjob.R;
import com.example.quickyjob.apicontroller;
import com.example.quickyjob.modelclass.UpdatePasswordModelClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class changePassword extends AppCompatActivity {

    EditText enterUsername, enterOldpass,enter_new_pass;
    TextView enterNewpass_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);


        enterUsername = findViewById(R.id.enterUsername);
        enterOldpass = findViewById(R.id.enterOldpass);
        enter_new_pass = findViewById(R.id.enter_new_pass);

        enterNewpass_btn=findViewById(R.id.enterNewpass_btn);

        enterNewpass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= enterUsername.getText().toString();
                String current = enterOldpass.getText().toString();
                String newwpass = enter_new_pass.getText().toString();

                Call<UpdatePasswordModelClass> call = apicontroller.getInstance().getapi().getUpdatedPassword(email,current,newwpass);
                call.enqueue(new Callback<UpdatePasswordModelClass>() {
                    @Override
                    public void onResponse(Call<UpdatePasswordModelClass> call, Response<UpdatePasswordModelClass> response) {
                        UpdatePasswordModelClass updatePasswordModelClass = response.body();
                        String result = updatePasswordModelClass.getMessage().trim();
                        if (result.equals("password updated")){
                            Toast.makeText(changePassword.this, "Password Updated", Toast.LENGTH_SHORT).show();
                            enterUsername.setText("");
                            enterOldpass.setText("");
                            enter_new_pass.setText("");
                        }
                        if (result.equals("password not updated")){
                            Toast.makeText(changePassword.this, "Password Not Updated", Toast.LENGTH_SHORT).show();
                            enterUsername.setText("");
                            enterOldpass.setText("");
                            enter_new_pass.setText("");
                        }
                         if (result.equals("invalid password")){
                            Toast.makeText(changePassword.this, "Invalid Username/Password", Toast.LENGTH_SHORT).show();
                            enterUsername.setText("");
                            enterOldpass.setText("");
                            enter_new_pass.setText("");
                        }




                    }

                    @Override
                    public void onFailure(Call<UpdatePasswordModelClass> call, Throwable t) {
                        Toast.makeText(changePassword.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

             //   Toast.makeText(changePassword.this, "This is change pass button", Toast.LENGTH_SHORT).show();

            }
        });
    }
}