package com.adityaputrak.bukumedia.admin;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.adityaputrak.bukumedia.R;

import java.io.File;

import api.ApiClient;
import model.DetailBukuResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBukuActivity extends AppCompatActivity {

    EditText etJudul,etDeskripsi,etHarga,etStok;
    ImageView ivCover;
    TextView tvCover;
    int id;
    Button btnGambar, btnAdd;

    String mediapath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_buku);

        etJudul=findViewById(R.id.etJudul);
        etDeskripsi=findViewById(R.id.etDeskripsi);
        etHarga=findViewById(R.id.etHarga);
        etStok=findViewById(R.id.etStok);
        ivCover=findViewById(R.id.ivCover);
        btnAdd=findViewById(R.id.btTambah);
        btnGambar=findViewById(R.id.btGambar);
        tvCover=findViewById(R.id.tvGambar);

        String[]galleryPremissions={
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        if(EasyPermissions.hasPermissions(this,galleryPremissions)){

        }else {
            EasyPermissions.requestPermissions(this,"Accses for storage",
                    101,galleryPremissions);
        }
        btnGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahGambar();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kirimData();
            }
        });
    }

    private void tambahGambar() {
        Intent galeryIntent=new Intent(Intent.ACTION_PICK);
        galeryIntent.setType("image/*");
        galeryIntent=Intent.createChooser(galeryIntent,"Choose a file");
        startActivityForResult(galeryIntent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            if (requestCode==1 && resultCode == RESULT_OK&&null !=data){
                Uri selectedImage=data.getData();
                String[]filePathColumn= {MediaStore.Files.FileColumns.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null,
                        null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediapath = cursor.getString(columnIndex);

                tvCover.setText("lokasi file"+mediapath);
                ivCover.setImageBitmap(BitmapFactory.decodeFile(mediapath));
                cursor.close();
            } else {
                Toast.makeText(AddBukuActivity.this, "File belum di tambahakan", Toast.LENGTH_SHORT).show();
            }
        } catch (Throwable e) {
            Toast.makeText(this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void kirimData() {
        final String judul = etJudul.getText().toString();
        final String deskripsi = etDeskripsi.getText().toString();
        final String harga = etHarga.getText().toString();
        final String stok = etStok.getText().toString();

        File file = new File(mediapath);
        Toast.makeText(AddBukuActivity.this, "Error" , Toast.LENGTH_SHORT).show();

        RequestBody requestBody = RequestBody.create(file, MediaType.parse("*/*"));
        MultipartBody.Part fileToUpload = MultipartBody.Part
                .createFormData("gambar", file.getName(), requestBody);
        RequestBody dataJudul = RequestBody.create(judul, MediaType.parse("multipart/form-data"));
        RequestBody dataDeskripsi = RequestBody.create(deskripsi, MediaType.parse("multipart/form-data"));
        RequestBody dataHarga = RequestBody.create(harga, MediaType.parse("multipart/form-data"));
        RequestBody dataStok = RequestBody.create(stok, MediaType.parse("multipart/form-data"));

        ApiClient.getService().createbuku(dataJudul, dataDeskripsi, fileToUpload, dataHarga, dataStok).enqueue(new Callback<DetailBukuResponse>() {
            @Override
            public void onResponse(Call<DetailBukuResponse> call, Response<DetailBukuResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus()==1){
                        Toast.makeText(AddBukuActivity.this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddBukuActivity.this, "GAGAL", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DetailBukuResponse> call, Throwable t) {
                Toast.makeText(AddBukuActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}