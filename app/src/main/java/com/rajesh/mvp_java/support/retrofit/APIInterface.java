package com.rajesh.mvp_java.support.retrofit;

import com.rajesh.mvp_java.login.model.User;
import com.rajesh.mvp_java.login.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("person_array.json")
    Call<List<UserModel>> getUsers();

}
