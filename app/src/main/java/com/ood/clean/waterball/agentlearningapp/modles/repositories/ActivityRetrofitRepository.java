package com.ood.clean.waterball.agentlearningapp.modles.repositories;

import android.util.Log;

import com.ood.clean.waterball.agentlearningapp.Secret;
import com.ood.clean.waterball.agentlearningapp.modles.entities.Activity;
import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.ResponseModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;


public class ActivityRetrofitRepository implements ActivityRepository{
    private final static String TAG = "ActivityRetroRepository";
    private ActivityAPI activityAPI;

    public ActivityRetrofitRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Secret.IP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        activityAPI = retrofit.create(ActivityAPI.class);
    }

    @Override
    public ResponseModel<Activity> getRecentActivities(String tag, int offset, int limit, String startDate, String endDate, String updatedDate) throws IOException {
        Log.d(TAG, "get recent activities");
        Response<ResponseModel<Activity>> response = activityAPI.getRecentActivities(tag, offset, limit, startDate, endDate, updatedDate).execute();
        ResponseModel<Activity> responseModel = response.body();
        assert responseModel != null;
        Log.d(TAG, responseModel.toString());
        return responseModel;
    }

    public interface ActivityAPI {
        String RESOURCE = "HZN/api/";

        //應該要拿到資料
        @Headers("Content-Type:application/x-www-form-urlencoded")
        @FormUrlEncoded
        @GET(RESOURCE + "{tag}/?{offset=}&{limit=}&{startDate=}&{endDate=}&{updatedDate=}")
        Call<ResponseModel<Activity>> getRecentActivities(@Path("tag") String tag,
                                                          @Path("offset") int offset,
                                                          @Path("limit") int limit,
                                                          @Path("startDate") String startDate,
                                                          @Path("endDate") String endDate,
                                                          @Path("updatedDate") String updatedDate);
    }
}
