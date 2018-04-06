package com.ood.clean.waterball.agentlearningapp.modles.viewmodels;

public class SignInModel {
    private String account;
    private String password;

    public SignInModel(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }
}
