package com.ood.clean.waterball.agentlearningapp.utils;

import com.ood.clean.waterball.agentlearningapp.presenter.ActivityFragmentPresenter;

import java.util.List;

public interface Action {
    void invoke(ActivityFragmentPresenter activityFragmentPresenter);
}
