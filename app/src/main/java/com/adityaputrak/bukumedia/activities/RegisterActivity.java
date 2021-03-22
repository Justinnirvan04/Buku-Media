package com.adityaputrak.bukumedia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adityaputrak.bukumedia.R;

import api.ApiClient;
import model.RegisterResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText etNama,etEmail,etPassword;
    Button btRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btRegister = findViewById(R.id.btRegister);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prosesRegistrasi();
            }
        });
    }

    private void prosesRegistrasi() {
        String nama,email,password;
        nama=etNama.getText().toString();
        email=etEmail.getText().toString();
        password=etPassword.getText().toString();

        ApiClient.getService().registerUser(nama,email,password,"COSTUMER").enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus()==1){
                        Toast.makeText(RegisterActivity.this,"Registrasi sukses", Toast.LENGTH_SHORT);
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
            Toast.makeText(RegisterActivity.this,"GAGAL"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}