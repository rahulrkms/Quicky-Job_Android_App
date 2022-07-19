package com.example.quickyjob.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quickyjob.MainActivity;
import com.example.quickyjob.R;
import com.example.quickyjob.apicontroller;
import com.example.quickyjob.modelclass.SecurityQuestionVerificationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecurityQuestion extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button saveAnswerbtn;
    EditText yourAnswer;
    String text;
    String getemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_question);

        saveAnswerbtn = findViewById(R.id.saveAnswerbtn);
        yourAnswer = findViewById(R.id.yourAnswer);

         getemail = getIntent().getStringExtra("emailFromReg");



        saveAnswerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAns(text,yourAnswer.getText().toString());

            }
        });


        Spinner spinner = findViewById(R.id.spinnerbar);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.security, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    private void saveAns(String text, String yourAnswer) {
        Call<SecurityQuestionVerificationResponse> call = apicontroller.getInstance().getapi().verifySecurityQuestion(text,yourAnswer,getemail);
        call.enqueue(new Callback<SecurityQuestionVerificationResponse>() {
            @Override
            public void onResponse(Call<SecurityQuestionVerificationResponse> call, Response<SecurityQuestionVerificationResponse> response) {
                SecurityQuestionVerificationResponse model = response.body();
                String result = model.getMessage().trim();
                if (result.equals("Inserted")){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }

            }

            @Override
            public void onFailure(Call<SecurityQuestionVerificationResponse> call, Throwable t) {

                    Toast.makeText(SecurityQuestion.this, "Something Went wrong", Toast.LENGTH_SHORT).show();


            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
         text = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}