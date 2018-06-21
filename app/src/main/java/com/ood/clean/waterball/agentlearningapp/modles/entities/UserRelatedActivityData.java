package com.ood.clean.waterball.agentlearningapp.modles.entities;

public class UserRelatedActivityData {
    private Activity activity;
    private int userId;
    private int activityId;
    private boolean isLike;
    private boolean isJoin;

    public UserRelatedActivityData(Activity activity, int userId, int activityId, boolean isLike, boolean isJoin) {
        this.activity = activity;
        this.userId = userId;
        this.activityId = activityId;
        this.isLike = isLike;
        this.isJoin = isJoin;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public boolean isJoin() {
        return isJoin;
    }

    public void setJoin(boolean join) {
        isJoin = join;
    }
}
