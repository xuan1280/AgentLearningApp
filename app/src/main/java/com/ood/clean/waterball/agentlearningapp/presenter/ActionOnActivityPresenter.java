package com.ood.clean.waterball.agentlearningapp.presenter;

import android.os.Handler;
import android.util.Log;

import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.repositories.UserRepository;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.ResponseModel;
import com.ood.clean.waterball.agentlearningapp.views.base.ActionOnActivityView;

import java.io.IOException;

public class ActionOnActivityPresenter {
    private final static String TAG = "ActionOnActivityPS";
    private UserRepository userRepository;
    private ActionOnActivityView actionOnActivityView;
    private Handler handler = new Handler();

    public ActionOnActivityPresenter(UserRepository userRepository, ActionOnActivityView actionOnActivityView) {
        this.userRepository = userRepository;
        this.actionOnActivityView = actionOnActivityView;
    }

    public void actionOnActivity(int userId, int activityId, String action, boolean value) {
        Log.d(TAG, "userId: " + userId + "perform or cancel action on activityId: " + activityId);
        new Thread() {
            @Override
            public void run() {
                ResponseModel<User> responseModel = null;
                try {
                    responseModel = userRepository.actionOnActivity(userId, activityId, action, value);
                    if (responseModel.getCode() == 404)
                        handler.post(() -> actionOnActivityView.onTargetNotFound());
                    else if (responseModel.getCode() == 400)
                        handler.post(() -> actionOnActivityView.onActionInvalid());
                    else
                        handler.post(() -> actionOnActivityView.onActionSuccessfully());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }

}
