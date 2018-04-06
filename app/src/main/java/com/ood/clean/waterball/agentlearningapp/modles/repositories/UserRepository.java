package com.ood.clean.waterball.agentlearningapp.modles.repositories;

import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.SignInModel;

//TODO ResponseModel<T>
public interface UserRepository {
    User signIn(SignInModel signInModel);
    void removeUser(User user);
    void updateUser(User user);
}
