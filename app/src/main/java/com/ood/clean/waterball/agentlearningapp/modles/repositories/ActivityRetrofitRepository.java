package com.ood.clean.waterball.agentlearningapp.modles.repositories;

import android.util.Log;

import com.ood.clean.waterball.agentlearningapp.Secret;
import com.ood.clean.waterball.agentlearningapp.modles.entities.Activity;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.ResponseModel;
import com.ood.clean.waterball.agentlearningapp.utils.ResponseUtils;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;


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
    public ResponseModel<List<Activity>> getRecentActivities(String tag, int offset, int limit, String startDate, String endDate, String updatedDate) throws Exception {
        Log.d(TAG, "get recent activities");
        Response<ResponseModel<List<Activity>>> response = activityAPI.getRecentActivities(tag, offset, limit, startDate, endDate, updatedDate).execute();
        ResponseModel<List<Activity>> responseModel = ResponseUtils.getBody(response);
        Log.d(TAG, response.toString());
        return responseModel;
    }

    @Override
    public ResponseModel<List<Activity>> getRecentActivities(int offset, int limit) throws Exception {
        Log.d(TAG, "get recent activities");
        Response<ResponseModel<List<Activity>>> response = activityAPI.getRecentActivities(offset, limit).execute();
        ResponseModel<List<Activity>> responseModel = ResponseUtils.getBody(response);
        Log.d(TAG, response.toString());
        return responseModel;
    }

    public interface ActivityAPI {
        String RESOURCE = "HZN/api/";

        //應該要拿到資料
        @Headers("Content-Type:application/json")
        @GET(RESOURCE)
        Call<ResponseModel<List<Activity>>> getRecentActivities(@Path("tag") String tag,
                                                          @Query("offset") int offset,
                                                          @Query("limit") int limit,
                                                          @Query("startDate") String startDate,
                                                          @Query("endDate") String endDate,
                                                          @Query("updatedDate") String updatedDate);

        @Headers("Content-Type:application/json")
        @GET(RESOURCE)
        Call<ResponseModel<List<Activity>>> getRecentActivities(
                                                                @Query("offset") int offset,
                                                                @Query("limit") int limit);
    }
}
