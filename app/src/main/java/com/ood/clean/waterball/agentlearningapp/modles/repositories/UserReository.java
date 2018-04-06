package com.ood.clean.waterball.agentlearningapp.modles.repositories;

import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.SignInModel;

public interface UserReository {
    User signIn(SignInModel signInModel);
    void removeUser(User user);
    void updateUser(User user);
}
