package model;

import android.service.autofill.UserData;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("user")
    private DataUser userData;

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

    public DataUser getUserData() {
        return userData;
    }

    public void setUserData(DataUser userData) {
        this.userData = userData;
    }
}
