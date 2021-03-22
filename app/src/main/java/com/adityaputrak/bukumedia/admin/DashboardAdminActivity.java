package com.adityaputrak.bukumedia.admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adityaputrak.bukumedia.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import adapter.BukuAdapter;
import api.ApiClient;
import model.DataBuku;
import model.ListBukuResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardAdminActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton gabut;
    Context context;
    BukuAdapter adapter;
    private List<DataBuku> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);
        recyclerView=findViewById(R.id.rvBuku);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter=new BukuAdapter(mItems,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        gabut=findViewById(R.id.fab);

        gabut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardAdminActivity.this,AddBukuActivity.class);
                startActivity(i);
            }
        });

        readData();
    }

    private void readData() {
        ApiClient.getService().getListBuku().enqueue(new Callback<ListBukuResponse>() {
            @Override
            public void onResponse(Call<ListBukuResponse> call, Response<ListBukuResponse> response) {
                mItems=response.body().getDataBuku();
                recyclerView.setAdapter(new BukuAdapter(mItems,getApplicationContext()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ListBukuResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"KONEKSI ERROR!!", Toast.LENGTH_SHORT).show();

            }
        });
    }

}