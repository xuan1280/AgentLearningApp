package com.ood.clean.waterball.agentlearningapp.modles.repositories;

import com.ood.clean.waterball.agentlearningapp.modles.entities.Activity;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.ResponseModel;

import java.io.IOException;

public interface ActivityRepository {
    ResponseModel<Activity> getRecentActivities(String tag, int offset, int limit, String startDate, String endDate, String updatedDate) throws IOException;
}
