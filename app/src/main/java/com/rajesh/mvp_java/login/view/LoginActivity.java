package com.rajesh.mvp_java.login.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rajesh.mvp_java.Dashboard;
import com.rajesh.mvp_java.R;
import com.rajesh.mvp_java.login.LoginContract;
import com.rajesh.mvp_java.login.presenter.LoginPresenter;
import com.rajesh.mvp_java.login.model.User;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView {

    private LoginPresenter loginPresenter;
    private ProgressDialog progressDialog;
    private EditText userNameET, passwordET;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initUI();
        initializeListeners();
        loginPresenter = new LoginPresenter(LoginActivity.this);
    }

    private void initUI() {
        userNameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);
        loginBtn = findViewById(R.id.loginBtn);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging in...");
        progressDialog.setCancelable(false);


        userNameET.setText("test@gmail.com");
        passwordET.setText("0000");
    }

    private void initializeListeners() {
        loginBtn.setOnClickListener(clickListeners);
    }

    private View.OnClickListener clickListeners = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int viewId = v.getId();
            if (viewId == R.id.loginBtn) {
                User user = new User(userNameET.getText().toString(), passwordET.getText().toString());
                loginPresenter.doLogin(user);
            }
        }
    };

    @Override
    public void showProgressBar() {
        progressDialog.show();
    }

    @Override
    public void hideProgressBar() {
        progressDialog.hide();
    }

    @Override
    public void onLoginSuccess() {
        startActivity(new Intent(LoginActivity.this, Dashboard.class));
        hideProgressBar();
    }

    @Override
    public void onLoginFailure(String message) {
        hideProgressBar();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
