package com.ood.clean.waterball.agentlearningapp.modles.entities;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String name;
    private int age;
    private boolean gender;
    private String account;
    private String password;
    private City city;

    public User() {
    }

    public User(int id, String name, int age, boolean gender, String account, String password, City city) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.account = account;
        this.password = password;
        this.city = city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean getGender() {
        return gender;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public City getCity() {
        return city;
    }
}
