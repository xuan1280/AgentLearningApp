package com.ood.clean.waterball.agentlearningapp.modles.repositories;

import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.ResponseModel;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.SignInModel;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.SignUpModel;

import java.io.IOException;

//TODO ResponseModel<T>
public interface UserRepository {
    ResponseModel<User> signIn(SignInModel signInModel) throws IOException;
    ResponseModel<User> signUp(SignUpModel signUpModel) throws IOException;
    ResponseModel<User> actionOnActivity(int userId, int activityId, String action, boolean value) throws IOException;
    ResponseModel<User> pushUserPreferences(int userId, int activityId, int browsingTime, int clickTime) throws IOException;
    ResponseModel<User> getUserRelatedActivities(int userID, String type, int count) throws IOException;
    void removeUser(User user);
    void updateUser(User user);
}
