package com.ood.clean.waterball.agentlearningapp.modles.repositories;

import com.ood.clean.waterball.agentlearningapp.modles.entities.Activity;

import java.util.List;

public interface AvtivityRepository {
    List<Activity> getRecentActivities();
}
