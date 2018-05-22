package com.ood.clean.waterball.agentlearningapp.presenter;

import android.os.Handler;
import android.util.Log;

import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.repositories.UserRepository;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.ResponseModel;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.SignUpModel;
import com.ood.clean.waterball.agentlearningapp.views.base.SignUpView;

import java.io.IOException;

public class SignUpPresenter {
    private final static String TAG = "SignUpPresenter";
    private UserRepository userRepository;
    private SignUpView signUpView;
    private Handler handler = new Handler();

    public SignUpPresenter(UserRepository userRepository, SignUpView signUpView) {
        this.userRepository = userRepository;
        this.signUpView = signUpView;
    }

    public void signUp(SignUpModel signUpModel) {
        Log.d(TAG, "sign up.");
        new Thread() {
            @Override
            public void run() {
                ResponseModel<User> responseModel;
                try {
                    responseModel = userRepository.signUp(signUpModel);
                    if (responseModel.getCode() == 40001)
                        handler.post(() -> signUpView.onAccountDuplicated());
                    else if (responseModel.getCode() == 40000)
                        handler.post(() -> signUpView.onParameterInvalid());
                    else {
                        handler.post(() -> signUpView.onSignUpSuccessfully(responseModel.getData()));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

}
