package com.adityaputrak.bukumedia.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.adityaputrak.bukumedia.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import adapter.BukuAdapter;
import adapter.BukuUserAdapter;
import api.ApiClient;
import model.DataBuku;
import model.ListBukuResponse;
import model.TotalSeluruhnya;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DasboardUserActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Context context;
    BukuAdapter adapter;
    private List<DataBuku> mItems = new ArrayList<>();

    Button btRefresh;
    TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dhasboard_user);

        recyclerView=findViewById(R.id.rvBuku);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter=new BukuAdapter(mItems,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
        btRefresh=findViewById(R.id.btRefresh);
        tvTotal=findViewById(R.id.tvTotal);
        btRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTotal.setText("Rp."+String.valueOf(TotalSeluruhnya.TOTALAKHIR));
            }
        });

        readData();
    }

    private void readData() {
       ApiClient.getService().getListBuku().enqueue(new Callback<ListBukuResponse>() {
           @Override
           public void onResponse(Call<ListBukuResponse> call, Response<ListBukuResponse> response) {
               if (response.isSuccessful()){
               if (response.body().getStatus()==1){
                   mItems = response.body().getDataBuku();
                   recyclerView.setAdapter(new BukuUserAdapter(mItems,getApplicationContext()));
                   adapter.notifyDataSetChanged();
               }
           }
           }



           @Override
           public void onFailure(Call<ListBukuResponse> call, Throwable t) {
            Toast.makeText(context, "GAGAL KONEKSI",Toast.LENGTH_SHORT);
            Log.d("On Failure",t.getMessage());
           }
       });
    }

}