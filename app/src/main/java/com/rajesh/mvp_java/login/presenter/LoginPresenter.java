package com.rajesh.mvp_java.login.presenter;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import com.google.gson.Gson;
import com.rajesh.mvp_java.login.LoginContract;
import com.rajesh.mvp_java.login.model.User;
import com.rajesh.mvp_java.support.retrofit.APIInterface;
import com.rajesh.mvp_java.support.retrofit.RetrofitApiClient;
import com.rajesh.mvp_java.login.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.LoginPresenter {

    private String TAG = "LoginPresenter";
    private LoginContract.LoginView loginView;

    public LoginPresenter(LoginContract.LoginView view) {
        loginView = view;
    }


    @Override
    public void doLogin(final User user) {
        loginView.showProgressBar();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (validateUser(user)) {
                    //hit api request here
                    login(user);
                } else {
                    loginView.hideProgressBar();
                    loginView.onLoginFailure("Please enter valid credentials");
                }
            }
        }, 2000);

    }

    private void login(User user) {
        if (user.getUsername().equalsIgnoreCase("test@gmail.com") && user.getPassword().equalsIgnoreCase("0000")) {
            loginView.onLoginSuccess();
        } else {
            loginView.onLoginFailure("wrong username or passsword");
        }

        APIInterface apiInterface = RetrofitApiClient.getRetrofitClient().create(APIInterface.class);
        Call<List<UserModel>> call = apiInterface.getUsers();
        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                Log.e(TAG, "Response code: " + response.code());
                List<UserModel> userModelList = response.body();
                Log.e(TAG, "userlist size: " + userModelList.size());
                for (int i = 0; i < userModelList.size(); i++) {
                    Log.e(TAG, "user model " + i + " " + new Gson().toJson(userModelList.get(i)));
                }
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {

            }
        });

    }

    private boolean validateUser(User user) {
        if (TextUtils.isEmpty(user.getUsername())) {
            return false;
        }
        if (TextUtils.isEmpty(user.getPassword())) {
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(user.getUsername()).matches()) {
            return false;
        }
        if (user.getPassword().length() < 4) {
            return false;
        }
        return true;
    }
}
