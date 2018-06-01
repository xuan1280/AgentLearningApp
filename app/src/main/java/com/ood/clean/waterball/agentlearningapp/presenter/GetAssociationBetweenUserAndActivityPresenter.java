package com.ood.clean.waterball.agentlearningapp.presenter;

import android.os.Handler;
import android.util.Log;

import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.repositories.UserRepository;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.ResponseModel;
import com.ood.clean.waterball.agentlearningapp.views.base.GetUserRelatedActivitiesView;

import java.io.IOException;

public class GetAssociationBetweenUserAndActivityPresenter {
    private final static String TAG = "GetAssociationPresenter";
    private UserRepository userRepository;
    private GetUserRelatedActivitiesView getUserRelatedActivitiesView;
    private Handler handler = new Handler();

    public GetAssociationBetweenUserAndActivityPresenter(UserRepository userRepository, GetUserRelatedActivitiesView getUserRelatedActivitiesView) {
        this.userRepository = userRepository;
        this.getUserRelatedActivitiesView = getUserRelatedActivitiesView;
    }

    public void getAssociationBetweenUserAndActivity(int userId, int activityId) {
        Log.d(TAG, "userId: " + userId + " get association with activityId: " + activityId);
        new Thread() {
            @Override
            public void run() {
                try {
                    ResponseModel<User> responseModel = userRepository.getAssociationBetweenUserAndActivity(userId, activityId);
                    if (responseModel.getCode() == 404)
                        handler.post(() -> getUserRelatedActivitiesView.onTargetNotFound());
                    else
                        handler.post(() -> getUserRelatedActivitiesView.onGetUserRelatedActivitiesSuccessfully());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }.start();
    }

}
