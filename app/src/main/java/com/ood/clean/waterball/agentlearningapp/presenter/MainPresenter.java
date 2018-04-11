package com.ood.clean.waterball.agentlearningapp.presenter;

import android.os.Handler;
import android.util.Log;

import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.repositories.UserRepository;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.SignInModel;
import com.ood.clean.waterball.agentlearningapp.views.base.MainView;


public class MainPresenter {
    private final static String TAG = "MainPresenter";
    private UserRepository userRepository;
    private MainView mainView;
    private Handler handler = new Handler();

    public MainPresenter(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public void signIn(final SignInModel signInModel) {
        Log.d(TAG, "signIn");
        new Thread(){
            @Override
            public void run() {
                User user = userRepository.signIn(signInModel);
                //TODO detect code
                if (user == null)
                    handler.post(()-> mainView.onSignInFailed());
                else
                    handler.post(()-> mainView.onSignInSuccessfully(user));
            }
        }.start();
    }
}
