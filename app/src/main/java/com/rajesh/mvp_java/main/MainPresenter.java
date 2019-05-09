package com.rajesh.mvp_java.main;


/**
 * Responsible for handling action from the view and updating UI as required
 *
 */

public class MainPresenter implements MainContract.Presenter {
    private MainContract.MainView mainView;

    MainPresenter(MainContract.MainView view){
        mainView = view;
    }

    @Override
    public void handleSignInBtnClick() {
        mainView.showSignInScreen();
    }

    @Override
    public void handleSignUpBtnClick() {
        mainView.showSignUpScreen();
    }
}
