package com.ood.clean.waterball.agentlearningapp.views.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.ood.clean.waterball.agentlearningapp.R;
import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.repositories.UserRetrofitRepository;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.SignInModel;
import com.ood.clean.waterball.agentlearningapp.presenter.MainPresenter;
import com.ood.clean.waterball.agentlearningapp.views.base.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements MainView {
    private final static String TAG = "MainActivity";
    @BindView(R.id.accountEd)
    EditText accountEd;
    @BindView(R.id.passwordEd)
    EditText passwordEd;
    @BindView(R.id.signInProgressBar)
    ProgressBar signInProgressBar;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenter(new UserRetrofitRepository());
        mainPresenter.setMainView(this);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ear_icon);
    }

    public void onLoginBtnClick(View view) {
        Log.d(TAG, "onLoginBtnClick");
        SignInModel signInModel = new SignInModel(accountEd.getText().toString(), passwordEd.getText().toString());
        mainPresenter.signIn(signInModel);
        signInProgressBar.setVisibility(View.VISIBLE);
    }

    public void onCreateAccountBtnClick(View view) {
        Log.d(TAG, "turn to sign up activity");
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void onForgotPasswordBtnClick(View view) {
    }


    @Override
    public void onSignInSuccessfully(User user) {
        Log.d(TAG, "onSignInSuccessfully");
        Intent intent = new Intent(this, BaseActivity.class);
        intent.putExtra("user", user);
        signInProgressBar.setVisibility(View.GONE);
        startActivity(intent);
    }

    @Override
    public void onAccountNoFound() {
        Log.d(TAG, "onAccountNoFound");
        new AlertDialog.Builder(this)
                .setMessage(getString(R.string.accountNotFound))
                .show();
        signInProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onPasswordNotCorrect() {
        Log.d(TAG, "onPasswordNotCorrect");
        new AlertDialog.Builder(this)
                .setMessage(getString(R.string.passwordNotCorrect))
                .show();
        signInProgressBar.setVisibility(View.GONE);
    }

}
