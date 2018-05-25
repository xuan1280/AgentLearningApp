package com.ood.clean.waterball.agentlearningapp.presenter;

import android.os.Handler;
import android.util.Log;

import com.ood.clean.waterball.agentlearningapp.modles.entities.Activity;
import com.ood.clean.waterball.agentlearningapp.modles.repositories.ActivityRepository;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.ResponseModel;
import com.ood.clean.waterball.agentlearningapp.views.base.ActivitiesRefreshView;

import java.io.IOException;

public class ActivityPresenter {
    private final static String TAG = "ActivityPresenter";
    private ActivityRepository activityRepository;
    private Handler handler = new Handler();
    private ActivitiesRefreshView activitiesRefreshView;

    public ActivityPresenter(ActivityRepository activityRepository, ActivitiesRefreshView activitiesRefreshView) {
        this.activityRepository = activityRepository;
        this.activitiesRefreshView = activitiesRefreshView;
    }

    public void getRecentActivities(String tag, int offset, int limit, String startDate, String endDate, String updatedDate){
        Log.d(TAG, "get recent activities");
        new Thread(){
            @Override
            public void run() {
                ResponseModel<Activity> responseModel;
                try {
                    responseModel = activityRepository.getRecentActivities(tag, offset, limit, startDate, endDate, updatedDate);
                    if (responseModel.getCode() == 200)
                        handler.post(() -> activitiesRefreshView.onActivitiesRefreshSuccessfully());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

}
