package com.ood.clean.waterball.agentlearningapp.presenter;

import android.os.Handler;
import android.util.Log;

import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.repositories.UserRepository;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.ResponseModel;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.SignInModel;
import com.ood.clean.waterball.agentlearningapp.views.base.MainView;

import java.io.IOException;


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
                ResponseModel<User> responseModel;
                try {
                    responseModel = userRepository.signIn(signInModel);
                    if (responseModel.getCode() == 404001)
                        handler.post(()-> mainView.onAccountNoFound());
                    else if (responseModel.getCode() == 404002)
                        handler.post(()-> mainView.onPasswordNotCorrect());
                    else
                        handler.post(()-> mainView.onSignInSuccessfully(responseModel.getData()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
