package com.rajesh.mvp_java.main;

/**
 * Defines the contract between the View  {@link MainActivity}
 * and the presenter {@link MainPresenter}
 */

public interface MainContract {
    interface MainView {
        void showSignInScreen();

        void showSignUpScreen();
    }

    interface Presenter {

        void handleSignInBtnClick();

        void handleSignUpBtnClick();
    }
}
