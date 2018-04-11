package com.ood.clean.waterball.agentlearningapp.modles.entities;

public class City extends Entitiy{
    private String name;

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
