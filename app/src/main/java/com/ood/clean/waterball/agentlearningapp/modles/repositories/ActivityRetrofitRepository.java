package com.ood.clean.waterball.agentlearningapp.modles.repositories;

import com.ood.clean.waterball.agentlearningapp.Secret;
import com.ood.clean.waterball.agentlearningapp.modles.entities.Activity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;


public class ActivityRetrofitRepository implements ActivityRepository{
    private final static String TAG = "UserRetrofitRepository";
    private ActivityAPI activityAPI;

    public ActivityRetrofitRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Secret.IP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        activityAPI = retrofit.create(ActivityAPI.class);
    }

    @Override
    public List<Activity> getRecentActivities() {
        return null;
    }

    private interface ActivityAPI {
        String RESOURCE = "HZN/api/";

        @Headers("Content-Type:application/x-www-form-urlencoded")
        @FormUrlEncoded
        @GET(RESOURCE + "{tag}/?{offset=}&{limit=}&{startDate=}&{endDate=}&{updatedDate=}")
        Call<List<Activity>> getRecentActivities();
    }
}
