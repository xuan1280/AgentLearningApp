package com.ood.clean.waterball.agentlearningapp.views.base;


import com.ood.clean.waterball.agentlearningapp.modles.entities.User;

public interface MainView {
    void onSignInSuccessfully(User user);
    void onAccountNoFound();
    void onPasswordNotCorrect();
}