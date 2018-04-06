package com.ood.clean.waterball.agentlearningapp.presenter;

import android.util.Log;

import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.repositories.UserRepository;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.SignInModel;
import com.ood.clean.waterball.agentlearningapp.views.base.MainView;

public class MainPresenter {
    private final static String TAG = "MainPresenter";
    private UserRepository userRepository;
    private MainView mainView;

    public MainPresenter(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public void signIn(SignInModel signInModel) {
        Log.d(TAG, "signIn");
        User user = userRepository.signIn(signInModel);
        if (user == null)
            mainView.onSignInFailed();
        else
            mainView.onSignInSuccessfully(user);
    }
}
