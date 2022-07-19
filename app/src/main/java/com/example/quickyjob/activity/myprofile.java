package com.example.quickyjob.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quickyjob.MainActivity;
import com.example.quickyjob.R;
import com.example.quickyjob.apicontroller;
import com.example.quickyjob.modelclass.FetchProfileDataResponseModel;
import com.example.quickyjob.modelclass.UpdateProfile;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class myprofile extends AppCompatActivity {
    TextView getting_email;
    SharedPreferences shpp;
    EditText ed11,ed13;
    Button save_btn;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

        //getSupportActionBar().hide();
        getting_email = findViewById(R.id.getting_email);
        ed11 = findViewById(R.id.ed11);
        ed13 = findViewById(R.id.ed13);
        save_btn = findViewById(R.id.save_btn);

        shpp = getSharedPreferences("credentials",MODE_PRIVATE);
        email = shpp.getString("email","");


        Call<List<FetchProfileDataResponseModel>> call = apicontroller.getInstance().getapi().getProfileDataForUpdate(email);
        call.enqueue(new Callback<List<FetchProfileDataResponseModel>>() {
            @Override
            public void onResponse(Call<List<FetchProfileDataResponseModel>> call, Response<List<FetchProfileDataResponseModel>> response) {
                List<FetchProfileDataResponseModel> model = response.body();
                for (int i =0; i<model.size();i++) {
                        ed11.setText(model.get(i).getName());
                        ed13.setText(model.get(i).getMobile());
                        getting_email.setText(model.get(i).getName());

                }
            }

            @Override
            public void onFailure(Call<List<FetchProfileDataResponseModel>> call, Throwable t) {
                Toast.makeText(myprofile.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    save_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            updateprofOfUser(ed11.getText().toString(),ed13.getText().toString());

        }
    });

    }

    private void updateprofOfUser(String name1, String mobile1) {

        Call<UpdateProfile> call = apicontroller.getInstance().getapi().updateProfileOfUser(email,name1,mobile1);

        call.enqueue(new Callback<UpdateProfile>() {
            @Override
            public void onResponse(Call<UpdateProfile> call, Response<UpdateProfile> response) {
                UpdateProfile updateProfile = response.body();
                String result = updateProfile.getMsg().trim();
                if (result.equals("Details Updated")){
                    Toast.makeText(myprofile.this, "Details Updated SuccessFully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                if (result.equals("Something Wrong")){
                    Toast.makeText(myprofile.this, "Something Went wrong", Toast.LENGTH_SHORT).show();
                }
                if (result.equals("Invalid Email")){
                    Toast.makeText(myprofile.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateProfile> call, Throwable t) {
                Toast.makeText(myprofile.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}