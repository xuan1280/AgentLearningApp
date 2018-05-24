package com.ood.clean.waterball.agentlearningapp.presenter;

import android.os.Handler;
import android.util.Log;

import com.ood.clean.waterball.agentlearningapp.modles.entities.Activity;
import com.ood.clean.waterball.agentlearningapp.modles.repositories.ActivityRepository;

import java.util.ArrayList;
import java.util.List;

public class ActivityPresenter {
    private final static String TAG = "ActivityPresenter";
    private ActivityRepository activityRepository;
    private Handler handler = new Handler();

    public ActivityPresenter(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> getRecentActivities(){
        Log.d(TAG, "get recent activities");
        List<Activity> activities = new ArrayList<>();


        return activities;
    }

}
