package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adityaputrak.bukumedia.R;
import com.adityaputrak.bukumedia.admin.DetailActivity;
import com.bumptech.glide.Glide;

import java.util.List;

import model.DataBuku;
import model.TotalSeluruhnya;

public class BukuUserAdapter extends RecyclerView.Adapter<BukuUserAdapter.ViewHolder> {

    private List<DataBuku> mdata;
    private Context context;

    public BukuUserAdapter(List<DataBuku>data, Context context){
        this.mdata=data;
        this.context=context;
    }

    @NonNull
    @Override
    public BukuUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_data_buku,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BukuUserAdapter.ViewHolder holder, int position) {
    final DataBuku dataBuku = mdata.get(position);
    String BASEIMAGE = "http://192.168.43.80:8000/storage/";

    holder.setJudul(dataBuku.getJudul());
    holder.setDeskripsi(dataBuku.getDeskripsi());
    holder.setHarga(dataBuku.getHarga());
    holder.setStok(dataBuku.getStok());
    holder.setTotalstok(dataBuku.getStok());
    holder.setHargabuku(dataBuku.getStok());


        Glide.with(context).load(BASEIMAGE+dataBuku.getCover()).into(holder.Cover);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Judul, Deskripsi, Harga, Stok, Jumlah, TotalQty;
        ImageView Cover;
        Button btkurang,bttambah;
        int datajumlah,Total,Hargabuku,Totalstok;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Judul = itemView.findViewById(R.id.tvJudul);
            Deskripsi = itemView.findViewById(R.id.tvDeskripsi);
            Harga = itemView.findViewById(R.id.tvHarga);
            Stok = itemView.findViewById(R.id.tvStok);
            Cover = itemView.findViewById(R.id.ivCover);
            Jumlah = itemView.findViewById(R.id.tvJumlah);
            TotalQty = itemView.findViewById(R.id.tvTotalQty);
            btkurang = itemView.findViewById(R.id.btKurangi);
            bttambah = itemView.findViewById(R.id.btTambah);
            datajumlah = 0;
            Total= 0;
            bttambah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                        datajumlah = datajumlah+1;
                        Jumlah.setText(String.valueOf(datajumlah));
                        TotalQty.setText(String.valueOf(Total));
                        Total = datajumlah * Hargabuku;
                        TotalSeluruhnya.TOTALAKHIR= TotalSeluruhnya.TOTALAKHIR+Hargabuku;

                }
            });
            btkurang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (datajumlah==0){

                    }else {
                        datajumlah = datajumlah-1;
                        Jumlah.setText(String.valueOf(datajumlah));
                        TotalQty.setText(String.valueOf(Total));
                        Total = datajumlah * Hargabuku;
                        TotalSeluruhnya.TOTALAKHIR= TotalSeluruhnya.TOTALAKHIR-Hargabuku;
                    }
                }
            });
        }

        public void setJudul(String judul){ Judul.setText(judul);}
        public void setDeskripsi(String deskripsi){ Deskripsi.setText(deskripsi);}
        public void setHarga(int harga){ Harga.setText(String.valueOf(harga));}
        public void setStok(int stok){ Stok.setText(String.valueOf(stok));}
        public void setCover(ImageView cover){ this.Cover=cover;}

        public void setTotalQty(TextView totalQty){TotalQty=totalQty;}
        public void setTotalstok(int totalstok){this.Totalstok=totalstok;}
        public void setHargabuku(int hargabuku){this.Hargabuku=hargabuku;}
}
}
