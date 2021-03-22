package model;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {
        @SerializedName("status")
        private int status;
        @SerializedName("message")
        private int message;
        @SerializedName("user")
        private int user;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
}

