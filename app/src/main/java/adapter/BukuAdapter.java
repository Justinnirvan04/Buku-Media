package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adityaputrak.bukumedia.R;
import com.adityaputrak.bukumedia.admin.DetailActivity;
import com.bumptech.glide.Glide;

import java.util.List;

import model.DataBuku;

public class BukuAdapter extends RecyclerView.Adapter<BukuAdapter.ViewHolder> {

    private List<DataBuku> mdata;
    private Context context;

    public BukuAdapter(List<DataBuku>data, Context context){
        this.mdata=data;
        this.context=context;
    }

    @NonNull
    @Override
    public BukuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_buku,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BukuAdapter.ViewHolder holder, int position) {
    final DataBuku dataBuku = mdata.get(position);
    String BASEIMAGE = "http://192.168.43.80:8000/storage/";

    holder.setJudul(dataBuku.getJudul());
    holder.setDeskripsi(dataBuku.getDeskripsi());
    holder.setHarga(dataBuku.getHarga());
    holder.setStok(dataBuku.getStok());

        Glide.with(context).load(BASEIMAGE+dataBuku.getCover()).into(holder.Cover);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("ID",mdata.get(position).getId());
                intent.putExtra("JUDUL",mdata.get(position).getJudul());
                intent.putExtra("DESKRIPSI",mdata.get(position).getDeskripsi());
                intent.putExtra("HARGA",mdata.get(position).getHarga());
                intent.putExtra("COVER",mdata.get(position).getCover());
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Judul, Deskripsi, Harga, Stok;
        ImageView Cover;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Judul = itemView.findViewById(R.id.tvJudul);
            Deskripsi = itemView.findViewById(R.id.tvDeskripsi);
            Harga = itemView.findViewById(R.id.tvHarga);
            Stok = itemView.findViewById(R.id.tvStok);
            Cover = itemView.findViewById(R.id.ivCover);
        }
        public void setJudul(String judul){ Judul.setText(judul);}
        public void setDeskripsi(String deskripsi){ Deskripsi.setText(deskripsi);}
        public void setHarga(int harga){ Harga.setText(String.valueOf(harga));}
        public void setStok(int stok){ Stok.setText(String.valueOf(stok));}
        public void setCover(ImageView cover){ this.Cover=cover;}
}
}
