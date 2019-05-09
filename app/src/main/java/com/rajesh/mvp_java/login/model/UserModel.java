package com.rajesh.mvp_java.login.model;

import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("name")
    public String name;

    @SerializedName(("email"))
    public String email;

    @SerializedName("phone")
    public Phone phone;

    public class Phone {
        @SerializedName("home")
        public String home;

        @SerializedName("mobile")
        public String mobile;
    }
}
