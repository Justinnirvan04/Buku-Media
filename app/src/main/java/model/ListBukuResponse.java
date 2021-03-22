package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListBukuResponse {
    @SerializedName("status")
    private int status;
    @SerializedName("buku")
    private List<DataBuku> dataBuku;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBuku> getDataBuku() {
        return dataBuku;
    }

    public void setDataBuku(List<DataBuku> dataBuku) {
        this.dataBuku = dataBuku;
    }
}
