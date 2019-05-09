package com.rajesh.mvp_java.login.presenter;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;

import com.rajesh.mvp_java.login.LoginContract;
import com.rajesh.mvp_java.login.model.User;

public class LoginPresenter implements LoginContract.LoginPresenter {

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
