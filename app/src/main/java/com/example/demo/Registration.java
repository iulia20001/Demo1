package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {


    EditText edEmail, edName, edSName, edPass, edPassCon;
    Button btnRegistration, btnAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        edEmail = findViewById(R.id.Email);
        edName = findViewById(R.id.NameReg);
        edSName = findViewById(R.id.SurnameReg);
        edPass = findViewById(R.id.PassReg1);
        edPassCon = findViewById(R.id.PassReg2);
        btnRegistration = findViewById(R.id.Registar);
        btnAccount = findViewById(R.id.Account);

        // этот код поднаначен для того чтоб проверил
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edEmail.getText().toString())||
                        TextUtils.isEmpty(edName.getText().toString())||
                        TextUtils.isEmpty(edSName.getText().toString())||
                        TextUtils.isEmpty(edPass.getText().toString())||
                        TextUtils.isEmpty(edPassCon.getText().toString())){
                    String message = "Заполните все поля";
                    Toast.makeText(Registration.this, message,Toast.LENGTH_LONG).show();
                } else {
                    registerUser();
                }
            }
        });

        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registration.this, Login.class);
                startActivity(i);
            }
        });
    }

    public void registerUser(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(edEmail.getText().toString());
        registerRequest.setName(edName.getText().toString());
        registerRequest.setSurname(edSName.getText().toString());
        registerRequest.setPass(edPass.getText().toString());
        registerRequest.setPassCon(edPassCon.getText().toString());
        Call<RegisterResponse> registerResponseCall = ApiClient.getRegister().registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()){

                    String message = "Вы успешно вошли!";
                    Toast.makeText(Registration.this, message, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Registration.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    String message = "Что-то пошло не так!";
                    Toast.makeText(Registration.this, response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(Registration.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}