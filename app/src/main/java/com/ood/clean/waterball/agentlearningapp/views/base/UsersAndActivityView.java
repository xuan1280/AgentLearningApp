package com.ood.clean.waterball.agentlearningapp.views.base;

import com.ood.clean.waterball.agentlearningapp.modles.entities.Activity;
import com.ood.clean.waterball.agentlearningapp.modles.entities.UserAssociation;

import java.util.List;

public interface UsersAndActivityView {
    void onPerformOrCancelActionSuccessfully();
    void onPerformOrCancelTargetNotFound();
    void onPerformOrCancelActionInvalid();

    void onGetAssociationBetweenUserAndActivityTargetNotFound();
    void onGetAssociationSuccessfully(UserAssociation data);

    void onGetUserRelatedActivitiesSuccessfully(List<Activity> data);
    void onGetUserRelatedActivitiesTargetNotFound();
    void onGetUserRelatedActivitiesActionInvalid();

    void onPushUserPreferencesSuccessfully();
    void onPushUserPreferencesTargetNotFound();
    void onPushUserPreferencesBrowsingTimeAndClickTimeInvalid();

    void onActivitiesRefreshSuccessfully(List<Activity> activities);
}
