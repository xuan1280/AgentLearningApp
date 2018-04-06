package com.ood.clean.waterball.agentlearningapp.modles.repositories;

import android.util.Log;

import com.ood.clean.waterball.agentlearningapp.modles.entities.City;
import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.SignInModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StubUserReository implements UserReository {
    private final static String TAG = "StubUserReository";
    private Map<Integer, User> usersMap = new HashMap<>();

    public StubUserReository() {
        createUsers();
    }

    private void createUsers() {
        User user = new User(1, "123", 3, true, "123", "123", new City(2, "新北市"));
        usersMap.put(user.getId(), user);
    }

    @Override
    public User signIn(SignInModel signInModel) {
        Log.d(TAG, "signIn");
        for (User user: usersMap.values())
            if (user.getAccount().equals(signInModel.getAccount()) && user.getPassword().equals(signInModel.getPassword()))
                return user;
        return null;
    }

    @Override
    public void removeUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

}
