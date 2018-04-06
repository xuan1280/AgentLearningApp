package com.ood.clean.waterball.agentlearningapp.views.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.ood.clean.waterball.agentlearningapp.R;
import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.SignInModel;
import com.ood.clean.waterball.agentlearningapp.presenter.MainPresenter;
import com.ood.clean.waterball.agentlearningapp.views.base.MainView;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("Registered")
public class MainActivity extends AppCompatActivity implements MainView {
    private final static String TAG = "MainActivity";
    @BindView(R.id.accountEd) EditText accountEd;
    @BindView(R.id.passwordEd) EditText passwordEd;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenter();
        mainPresenter.setMainView(this);
    }

    public void onLoginBtnClick(View view) {
        Log.d(TAG, "onLoginBtnClick");
        SignInModel signInModel = new SignInModel(accountEd.getText().toString(), passwordEd.getText().toString());
        mainPresenter.signIn(signInModel);
    }

    public void onCreateAccountBtnClick(View view) {
    }

    public void onForgotPasswordBtnClick(View view) {
    }


    @Override
    public void onSignInSuccessful(User user) {
        Log.d(TAG, "onSignInSuccessful");
        Intent intent = new Intent(this, BaseActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
