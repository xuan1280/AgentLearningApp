package com.ood.clean.waterball.agentlearningapp.presenter;

import android.os.Handler;
import android.util.Log;

import com.ood.clean.waterball.agentlearningapp.modles.entities.Activity;
import com.ood.clean.waterball.agentlearningapp.modles.entities.UserAssociation;
import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.repositories.ActivityRepository;
import com.ood.clean.waterball.agentlearningapp.modles.repositories.UserRepository;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.ResponseModel;
import com.ood.clean.waterball.agentlearningapp.views.base.UsersAndActivityView;

import java.io.IOException;
import java.util.List;

public class ActivityFragmentPresenter {
    private final static String TAG = "ActivityFragmentPS";
    private ActivityRepository activityRepository;
    private UserRepository userRepository;
    private Handler handler = new Handler();
    private UsersAndActivityView usersAndActivityView;

    public ActivityFragmentPresenter(ActivityRepository activityRepository, UserRepository userRepository, UsersAndActivityView usersAndActivityView) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
        this.usersAndActivityView = usersAndActivityView;
    }

    public void getRecentActivities(int userId, int offset, int limit) {
        Log.d(TAG, "get recent activities");
        new Thread() {
            @Override
            public void run() {
                ResponseModel<List<Activity>> responseModel;
                try {
                    responseModel = activityRepository.getRecentActivities(offset, limit);
                    if (responseModel.getCode() == 0) {
                        handler.post(() -> usersAndActivityView.onActivitiesRefreshSuccessfully(responseModel.getData()));
                        for(Activity activity : responseModel.getData())
                            new Thread( () -> getAssociationBetweenUserAndActivity(userId, activity.getId())).start();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }

    public void getRecentActivities(String tag, int offset, int limit, String startDate, String endDate, String updatedDate) {
        Log.d(TAG, "get recent activities");
        new Thread() {
            @Override
            public void run() {
                ResponseModel<List<Activity>> responseModel;
                try {
                    responseModel = activityRepository.getRecentActivities(tag, offset, limit, startDate, endDate, updatedDate);
                    if (responseModel.getCode() == 0)
                        handler.post(() -> usersAndActivityView.onActivitiesRefreshSuccessfully(responseModel.getData()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }

    public void getAssociationBetweenUserAndActivity(int userId, int activityId) {
        Log.d(TAG, "userId: " + userId + " get association with activityId: " + activityId);
        new Thread() {
            @Override
            public void run() {
                try {
                    ResponseModel<UserAssociation> responseModel = userRepository.getAssociationBetweenUserAndActivity(userId, activityId);
                    if (responseModel.getCode() == 404)
                        handler.post(() -> usersAndActivityView.onGetAssociationBetweenUserAndActivityTargetNotFound());
                    else
                        handler.post(() -> usersAndActivityView.onGetAssociationSuccessfully(responseModel.getData()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }

    public void pushUserPreference(int userId, int activityId, int browsingTime, int clickTime) {
        Log.d(TAG, "push user preference to backend");
        new Thread() {
            @Override
            public void run() {
                ResponseModel<User> responseModel;
                try {
                    responseModel = userRepository.pushUserPreferences(userId, activityId, browsingTime, clickTime);
                    if (responseModel.getCode() == 404)
                        handler.post(() -> usersAndActivityView.onPushUserPreferencesTargetNotFound());
                    else if (responseModel.getCode() == 400)
                        handler.post(() -> usersAndActivityView.onPushUserPreferencesBrowsingTimeAndClickTimeInvalid());
                    else
                        handler.post(() -> usersAndActivityView.onPushUserPreferencesSuccessfully());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }

    public void performOrCancelActionOnActivity(int userId, int activityId, String action, boolean value) {
        Log.d(TAG, "userId: " + userId + "perform or cancel invoke on activityId: " + activityId);
        new Thread() {
            @Override
            public void run() {
                ResponseModel<User> responseModel;
                try {
                    responseModel = userRepository.performOrCancelActionOnActivity(userId, activityId, action, value);
                    if (responseModel.getCode() == 404)
                        handler.post(() -> usersAndActivityView.onPerformOrCancelTargetNotFound());
                    else if (responseModel.getCode() == 400)
                        handler.post(() -> usersAndActivityView.onPerformOrCancelActionInvalid());
                    else {
                        getAssociationBetweenUserAndActivity(userId, activityId);
                        handler.post(() -> usersAndActivityView.onPerformOrCancelActionSuccessfully());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }

    public void getUserRelatedActivities(int UserId, String type, int count) {
        Log.d(TAG, "get userId: " + UserId + " related activities");
        new Thread() {
            @Override
            public void run() {
                try {
                    ResponseModel<List<Activity>> responseModel = userRepository.getUserRelatedActivities(UserId, type, count);
                    if (responseModel.getCode() == 404)
                        handler.post(() -> usersAndActivityView.onGetUserRelatedActivitiesTargetNotFound());
                    else if (responseModel.getCode() == 400)
                        handler.post(() -> usersAndActivityView.onGetUserRelatedActivitiesActionInvalid());
                    else
                        handler.post(() -> usersAndActivityView.onGetUserRelatedActivitiesSuccessfully(responseModel.getData()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }

    public void getUserRelatedActivities(int UserId, String type) {
        Log.d(TAG, "get userId: " + UserId + " related activities");
        new Thread() {
            @Override
            public void run() {
                try {
                    ResponseModel<List<Activity>> responseModel = userRepository.getUserRelatedActivities(UserId, type);
                    if (responseModel.getCode() == 404)
                        handler.post(() -> usersAndActivityView.onGetUserRelatedActivitiesTargetNotFound());
                    else if (responseModel.getCode() == 400)
                        handler.post(() -> usersAndActivityView.onGetUserRelatedActivitiesActionInvalid());
                    else {
                        for(Activity activity : responseModel.getData())
                            new Thread(() -> getAssociationBetweenUserAndActivity(UserId, activity.getId())).start();
                        handler.post(() -> usersAndActivityView.onGetUserRelatedActivitiesSuccessfully(responseModel.getData()));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }

}
