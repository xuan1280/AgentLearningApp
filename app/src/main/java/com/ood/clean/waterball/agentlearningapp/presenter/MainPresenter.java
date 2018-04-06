package com.ood.clean.waterball.agentlearningapp.presenter;

import android.util.Log;

import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.repositories.StubUserReository;
import com.ood.clean.waterball.agentlearningapp.modles.repositories.UserReository;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.SignInModel;
import com.ood.clean.waterball.agentlearningapp.views.base.MainView;

public class MainPresenter {
    private final static String TAG = "MainPresenter";
    private MainView mainView;

    public MainPresenter(){
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public void signIn(SignInModel signInModel) {
        Log.d(TAG, "signIn");
        UserReository userReository = new StubUserReository();
        User user = userReository.signIn(signInModel);
        mainView.onSignInSuccessful(user);
    }
}
