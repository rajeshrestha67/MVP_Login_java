package com.rajesh.mvp_java.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rajesh.mvp_java.R;
import com.rajesh.mvp_java.login.view.LoginActivity;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    private MainPresenter mainPresenter;
    private Button signInBtn, signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter(MainActivity.this);
        initUI();
        initClickListeners();
    }

    private void initUI() {
        signInBtn = findViewById(R.id.signInBtn);
        signUpBtn = findViewById(R.id.signUpBtn);
    }

    private void initClickListeners() {
        signInBtn.setOnClickListener(clickListeners);
        signUpBtn.setOnClickListener(clickListeners);
    }

    private View.OnClickListener clickListeners = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int viewId = v.getId();
            switch (viewId) {
                case R.id.signInBtn:
                    mainPresenter.handleSignInBtnClick();
                    break;
                case R.id.signUpBtn:
                    mainPresenter.handleSignUpBtnClick();
                    break;
                default:
            }
        }
    };

    @Override
    public void showSignInScreen() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    @Override
    public void showSignUpScreen() {
        Toast.makeText(this, "show sign up screen", Toast.LENGTH_SHORT).show();
    }
}
