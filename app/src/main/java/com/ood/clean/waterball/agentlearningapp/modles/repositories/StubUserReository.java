package com.ood.clean.waterball.agentlearningapp.modles.repositories;

import android.util.Log;

import com.ood.clean.waterball.agentlearningapp.modles.entities.City;
import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.SignInModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StubUserReository implements UserRepository {
    private final static String TAG = "StubUserReository";
    private Map<String, User> usersMap = new HashMap<>();

    public StubUserReository() {
        createUsers();
    }

    private void createUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "123", 3, true, "123", "123", new City(2, "新北市")));
        users.add(new User(2, "waterball", 22, true, "waterball", "123", new City(2, "新北市")));
        for (User user: users)
            usersMap.put(user.getAccount(), user);
    }

    @Override
    public User signIn(SignInModel signInModel) {
        Log.d(TAG, "signIn");
        User user = usersMap.get(signInModel.getAccount());
        if (user.getPassword().equals(signInModel.getPassword()))
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
