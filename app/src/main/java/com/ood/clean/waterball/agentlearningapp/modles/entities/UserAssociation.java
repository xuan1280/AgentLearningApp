package com.ood.clean.waterball.agentlearningapp.modles.entities;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

public class UserAssociation {
    private int userId;
    private int activityId;
    private boolean isLike;
    private boolean isJoin;

    public UserAssociation(int userId, int activityId, boolean isLike, boolean isJoin) {
        this.userId = userId;
        this.activityId = activityId;
        this.isLike = isLike;
        this.isJoin = isJoin;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAssociation that = (UserAssociation) o;
        return userId == that.userId &&
                activityId == that.activityId &&
                isLike == that.isLike &&
                isJoin == that.isJoin;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {

        return Objects.hash(userId, activityId, isLike, isJoin);
    }
}
