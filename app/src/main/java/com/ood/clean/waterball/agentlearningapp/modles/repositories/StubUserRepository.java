package com.ood.clean.waterball.agentlearningapp.modles.repositories;

import android.util.Log;

import com.ood.clean.waterball.agentlearningapp.modles.entities.City;
import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.ResponseModel;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.SignInModel;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.SignUpModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StubUserRepository implements UserRepository {
    private final static String TAG = "StubUserRepository";
    private Map<String, User> usersMap = new HashMap<>();

    public StubUserRepository() {
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
    public ResponseModel<User> signIn(SignInModel signInModel) {
        Log.d(TAG, "signIn");
        User user = usersMap.get(signInModel.getAccount());
        if (user == null)
            return new ResponseModel<>(404001, "No matched account found.", null);
        if (user.getPassword().equals(signInModel.getPassword()))
            return new ResponseModel<>(200, "successful.", user);
        return new ResponseModel<>(404002, "Password wrong.", null);
    }

    @Override
    public ResponseModel<User> signUp(SignUpModel signUpModel) {
        User signUpUser = new User(usersMap.size()+1, signUpModel.getName(), signUpModel.getAge(), signUpModel.isGender(), signUpModel.getAccount(), signUpModel.getPassword(), new City(signUpModel.getCityId(), "新北"));
        User user = usersMap.get(signUpModel.getAccount());
        if (user == null && !isParameterInvalid(signUpModel)) {
            usersMap.put(signUpUser.getAccount(), signUpUser);
            return new ResponseModel<>(200, "sign up successfully.", signUpUser);
        }
        else if (user != null)
            return new ResponseModel<>(40001, "account duplicated", null);
        return new ResponseModel<>(40000, "parameter invalid", null);
    }

    public boolean isParameterInvalid(SignUpModel signUpModel){
        if (signUpModel.getAccount().equals("") || signUpModel.getPassword().equals("") || signUpModel.getName().equals("") || signUpModel.getAge() == 0 || signUpModel.getCityId() == 0)
            return true;
        return false;
    }

    @Override
    public void removeUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

}
