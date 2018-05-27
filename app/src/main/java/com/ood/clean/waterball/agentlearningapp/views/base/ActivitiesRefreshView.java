package com.ood.clean.waterball.agentlearningapp.views.base;

import com.ood.clean.waterball.agentlearningapp.modles.entities.Activity;

import java.util.List;

public interface ActivitiesRefreshView {
    void onActivitiesRefreshSuccessfully(List<Activity> activities);
}
