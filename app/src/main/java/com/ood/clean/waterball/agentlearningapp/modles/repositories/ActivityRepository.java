package com.ood.clean.waterball.agentlearningapp.modles.repositories;

import com.ood.clean.waterball.agentlearningapp.modles.entities.Activity;

import java.util.List;

public interface ActivityRepository {
    List<Activity> getRecentActivities();
}
