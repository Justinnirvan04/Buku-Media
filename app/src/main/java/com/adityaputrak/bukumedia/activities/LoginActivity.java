package com.adityaputrak.bukumedia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.adityaputrak.bukumedia.R;
import com.adityaputrak.bukumedia.admin.DasboardUserActivity;
import com.adityaputrak.bukumedia.admin.DashboardAdminActivity;

import api.ApiClient;
import model.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    public static EditText edtUsername,edtPassword;
    public static ImageView btnLogin;
    public static TextView reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsername= findViewById(R.id.etUsername);
        edtPassword= findViewById(R.id.etPassword);
        btnLogin= findViewById(R.id.btnLogin1);
        reg= findViewById(R.id.txtRegist);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loginproses();
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
    }
    private void Loginproses(){
        final String username = edtUsername.getText().toString();
        final String password = edtPassword.getText().toString();

        ApiClient.getService().loginuser(username,password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus()==1){
                        Toast.makeText(LoginActivity.this,"Login sukses", Toast.LENGTH_SHORT);
                        Intent intent = new Intent(LoginActivity.this, DasboardUserActivity.class);
                        startActivity(intent);
                    }
                }
                Toast.makeText(LoginActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Login Gagal"+t.getMessage(), Toast.LENGTH_SHORT);
            }
        });
    }
}