package com.ood.clean.waterball.agentlearningapp.modles.viewmodels;

public class SignUpModel {
    private String account;
    private String password;
    private String name;
    private int age;
    private boolean gender;
    private int cityId;

    public SignUpModel(String account, String password, String name, int age, boolean gender, int city) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.cityId = city;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

}
