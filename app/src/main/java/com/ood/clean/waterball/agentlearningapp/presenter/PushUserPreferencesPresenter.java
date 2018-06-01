package com.ood.clean.waterball.agentlearningapp.presenter;

import android.os.Handler;
import android.util.Log;

import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.repositories.UserRepository;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.ResponseModel;
import com.ood.clean.waterball.agentlearningapp.views.base.PushUserPreferencesView;

import java.io.IOException;

public class PushUserPreferencesPresenter {
    private final static String TAG = "PushUserPreferencesPS";
    private UserRepository userRepository;
    private PushUserPreferencesView pushUserPreferencesView;
    private Handler handler = new Handler();

    public PushUserPreferencesPresenter(UserRepository userRepository, PushUserPreferencesView pushUserPreferencesView) {
        this.userRepository = userRepository;
        this.pushUserPreferencesView = pushUserPreferencesView;
    }

    public void pushUserPreference(int userId, int activityId, int browsingTime, int clickTime){
        Log.d(TAG, "push user preference to backend");
        new Thread(){
            @Override
            public void run() {
                ResponseModel<User> responseModel;
                try {
                    responseModel = userRepository.pushUserPreferences(userId, activityId, browsingTime, clickTime);
                    if(responseModel.getCode() == 404)
                        handler.post(() -> pushUserPreferencesView.onTargetUserOrActivityNotFound());
                    else if(responseModel.getCode() == 400)
                        handler.post( () -> pushUserPreferencesView.onBrowsingTimeAndClickTimeInvalid());
                    else
                        handler.post( () -> pushUserPreferencesView.onPushUserPreferencesSuccessfully());
                } catch (IOException e){
                    throw new RuntimeException(e);
                }

            }
        }.start();

    }

}
