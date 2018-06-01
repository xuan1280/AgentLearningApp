package com.ood.clean.waterball.agentlearningapp.presenter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.util.Log;

import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.repositories.UserRepository;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.ResponseModel;
import com.ood.clean.waterball.agentlearningapp.views.base.GetUserRelatedActivitiesView;

import java.io.IOException;

public class GetUserRelatedActivitiesPresenter {
    private final static String TAG = "GetUserRelatedActivitiesPS";
    private UserRepository userRepository;
    private GetUserRelatedActivitiesView getUserRelatedActivitiesView;
    private Handler handler = new Handler();

    public GetUserRelatedActivitiesPresenter(UserRepository userRepository, GetUserRelatedActivitiesView getUserRelatedActivitiesView) {
        this.userRepository = userRepository;
        this.getUserRelatedActivitiesView = getUserRelatedActivitiesView;
    }

    @SuppressLint("LongLogTag")
    public void getUserRelatedActivities(int UserId, String type, int count) {
        Log.d(TAG, "get userId: " + UserId + " related activities");
        new Thread() {
            @Override
            public void run() {
                try {
                    ResponseModel<User> responseModel = userRepository.getUserRelatedActivities(UserId, type, count);
                    if (responseModel.getCode() == 404)
                        handler.post(() -> getUserRelatedActivitiesView.onTargetNotFound());
                    else if (responseModel.getCode() == 400)
                        handler.post(() -> getUserRelatedActivitiesView.onActionInvalid());
                    else
                        handler.post(() -> getUserRelatedActivitiesView.onGetUserRelatedActivitiesSuccessfully());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }.start();
    }

}
