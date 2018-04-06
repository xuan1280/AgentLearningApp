package com.ood.clean.waterball.agentlearningapp.views.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.ood.clean.waterball.agentlearningapp.R;
import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.repositories.StubUserReository;
import com.ood.clean.waterball.agentlearningapp.modles.repositories.UserRepository;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.SignInModel;
import com.ood.clean.waterball.agentlearningapp.presenter.MainPresenter;
import com.ood.clean.waterball.agentlearningapp.views.base.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("Registered")
public class MainActivity extends AppCompatActivity implements MainView {
    private final static String TAG = "MainActivity";
    @BindView(R.id.accountEd) EditText accountEd;
    @BindView(R.id.passwordEd) EditText passwordEd;
    private MainPresenter mainPresenter;
    private UserRepository userRepository = new StubUserReository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenter(new StubUserReository());
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
    public void onSignInSuccessfully(User user) {
        Log.d(TAG, "onSignInSuccessfully");
        Intent intent = new Intent(this, BaseActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    @Override
    public void onSignInFailed() {
        Log.d(TAG, "onSignInFailed");
        new AlertDialog.Builder(this).setMessage(getString(R.string.accountOrPasswordNotCorrect)).create().show();
    }
}
