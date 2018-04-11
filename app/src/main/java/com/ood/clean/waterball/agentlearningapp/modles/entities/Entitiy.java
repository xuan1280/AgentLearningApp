package com.ood.clean.waterball.agentlearningapp.modles.entities;


import java.io.Serializable;

public class Entitiy implements Serializable {
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entitiy entitiy = (Entitiy) o;

        return id == entitiy.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
