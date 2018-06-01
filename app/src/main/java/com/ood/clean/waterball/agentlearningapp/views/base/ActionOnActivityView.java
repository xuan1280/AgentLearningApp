package com.ood.clean.waterball.agentlearningapp.views.base;

import com.ood.clean.waterball.agentlearningapp.modles.entities.Activity;

public interface ActionOnActivityView {
    void onActionSuccessfully();
    void onTargetNotFound();
    void onActionInvalid();
}
