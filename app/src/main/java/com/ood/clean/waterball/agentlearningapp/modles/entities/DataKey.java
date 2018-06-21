package com.ood.clean.waterball.agentlearningapp.modles.entities;

public enum DataKey {
    NewestData("最新消息"), JoinActivities("以參加活動"), UserPreferencesData("推薦消息");

    private String key;

    DataKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
