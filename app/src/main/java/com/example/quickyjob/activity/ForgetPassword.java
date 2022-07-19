package com.example.quickyjob.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quickyjob.MainActivity;
import com.example.quickyjob.R;
import com.example.quickyjob.apicontroller;
import com.example.quickyjob.modelclass.ForgetPasswordResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPassword extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText emailForVerification,yourAnswerForget;
    Button saveAnswerbtn;
    String selectSecQues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        emailForVerification = findViewById(R.id.emailForVerification);
        yourAnswerForget = findViewById(R.id.yourAnswerForget);
        saveAnswerbtn = findViewById(R.id.saveAnswerbtn);
        
        saveAnswerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassBtn(emailForVerification.getText().toString(),selectSecQues,yourAnswerForget.getText().toString());

            }
        });


        //  Spinner coading

        Spinner spin = findViewById(R.id.spinnerbarForget);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,R.array.security, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spin.setAdapter(arrayAdapter);
        spin.setOnItemSelectedListener(this);
    }

    private void resetPassBtn(String email  , String selectSecQues, String userAnswer) {
        Call<ForgetPasswordResponseModel> newcall = apicontroller.getInstance().getapi().forgePass(selectSecQues,userAnswer,email);
        newcall.enqueue(new Callback<ForgetPasswordResponseModel>() {
            @Override
            public void onResponse(Call<ForgetPasswordResponseModel> call, Response<ForgetPasswordResponseModel> response) {
                ForgetPasswordResponseModel forgetPasswordResponseModel = response.body();
                String res = forgetPasswordResponseModel.getMessage().trim();
                if (res.equals("data")){
                Intent intent = new Intent(getApplicationContext(),ResetForgetPaasword.class);
                intent.putExtra("emailFromForget", email);
                startActivity(intent);
                finish();

                }

            }

            @Override
            public void onFailure(Call<ForgetPasswordResponseModel> call, Throwable t) {
                Toast.makeText(ForgetPassword.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectSecQues = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(this, selectSecQues, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}