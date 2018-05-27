package com.ood.clean.waterball.agentlearningapp.modles.repositories;

import com.ood.clean.waterball.agentlearningapp.modles.entities.Activity;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.ResponseModel;

import java.util.List;

public interface ActivityRepository {
    ResponseModel<List<Activity>> getRecentActivities(String tag, int offset, int limit, String startDate, String endDate, String updatedDate) throws Exception;
    ResponseModel<List<Activity>> getRecentActivities(int offset, int limit) throws Exception;
}
