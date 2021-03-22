package com.adityaputrak.bukumedia.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.adityaputrak.bukumedia.R;
import com.bumptech.glide.Glide;

import api.ApiClient;
import model.DeleteBukuResponse;
import model.UpdateBukuResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    EditText etJudul,etDeskripsi,etHarga,etStok;
    ImageView ivCover;
    Button btUpdate;
    int id;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_admin,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menu_hapus){
            hapusData();
        }return true;
    }

    private void hapusData() {
        ApiClient.getService().deleteBuku(id).enqueue(new Callback<DeleteBukuResponse>() {
            @Override
            public void onResponse(Call<DeleteBukuResponse> call, Response<DeleteBukuResponse> response) {
                if (response.body().getStatus()==1){
                    startActivity(new Intent(DetailActivity.this,DashboardAdminActivity.class));
                    Toast.makeText(DetailActivity.this, "KEHAPUS WOE", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DeleteBukuResponse> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        etJudul = findViewById(R.id.etJudul);
        etDeskripsi = findViewById(R.id.etDeskripsi);
        etHarga = findViewById(R.id.etHarga);
        etStok = findViewById(R.id.etStok);
        ivCover = findViewById(R.id.ivCover);
        btUpdate = findViewById(R.id.btUpdate);

        id = getIntent().getIntExtra("ID",0);
        String judul = getIntent().getStringExtra("Judul");
        String deskripsi = getIntent().getStringExtra("DESKRIPSI");
        int stok = getIntent().getIntExtra("STOK",0);
        int harga = getIntent().getIntExtra("HARGA",0);
        String urlCover = getIntent().getStringExtra("COVER");

        etJudul.setText(judul);
        etDeskripsi.setText(deskripsi);
        etHarga.setText(String.valueOf(harga));
        etStok.setText(String.valueOf(stok));
        Glide.with(getApplicationContext())
                .load("http://192.168.43.80:8000/storage/"+urlCover)
                .into(ivCover);
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(id);
            }
        });
    }
    private void updateData(int id){
        String judul,deskripsi,harga,stok;
        judul=etJudul.getText().toString();
        deskripsi=etDeskripsi.getText().toString();
        harga=etHarga.getText().toString();
        stok=etStok.getText().toString();
        ApiClient.getService().updatebuku(id,judul,deskripsi,harga,stok)
                .enqueue(new Callback<UpdateBukuResponse>() {
            @Override
            public void onResponse(Call<UpdateBukuResponse> call, Response<UpdateBukuResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(DetailActivity.this,"BERHASIL WOIII",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DetailActivity.this,DashboardAdminActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<UpdateBukuResponse> call, Throwable t) {
                Toast.makeText(DetailActivity.this,"GAGAL WOIII", Toast.LENGTH_SHORT).show();
            }
        });
    }

}