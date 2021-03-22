package model;

import com.google.gson.annotations.SerializedName;

public class DataBuku {
    @SerializedName("id")
    private int id;
    @SerializedName("judul")
    private String judul;
    @SerializedName("deskripsi")
    private String deskripsi;
    @SerializedName("gambar")
    private String cover;
    @SerializedName("harga")
    private int harga;
    @SerializedName("stok")
    private int stok;

    public DataBuku(int id, String judul, String deskripsi, String cover, int harga, int stok) {
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.cover = cover;
        this.harga = harga;
        this.stok = stok;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String Judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
}