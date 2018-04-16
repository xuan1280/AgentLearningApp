package com.ood.clean.waterball.agentlearningapp.modles.repositories;

import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.ResponseModel;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.SignInModel;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.SignUpModel;

//TODO ResponseModel<T>
public interface UserRepository {
    ResponseModel<User> signIn(SignInModel signInModel);
    ResponseModel<User> signUp(SignUpModel signUpModel);
    void removeUser(User user);
    void updateUser(User user);
}
