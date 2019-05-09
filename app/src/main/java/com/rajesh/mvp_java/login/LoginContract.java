package com.rajesh.mvp_java.login;

import com.rajesh.mvp_java.login.model.User;

public interface LoginContract {

    interface LoginPresenter {

        void doLogin(User user);

    }

    interface LoginView {

        void showProgressBar();

        void hideProgressBar();

        void onLoginSuccess();

        void onLoginFailure(String message);

    }

}
