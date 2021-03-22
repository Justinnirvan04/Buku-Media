package model;

import com.google.gson.annotations.SerializedName;

public class UpdateBukuResponse {
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("buku")
    private DataBuku dataBuku;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBuku getDataBuku() {
        return dataBuku;
    }

    public void setDataBuku(DataBuku dataBuku) {
        this.dataBuku = dataBuku;
    }
}
