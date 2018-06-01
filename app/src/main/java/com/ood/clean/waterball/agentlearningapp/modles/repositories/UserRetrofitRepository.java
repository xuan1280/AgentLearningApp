package com.ood.clean.waterball.agentlearningapp.modles.repositories;

import android.util.Log;

import com.ood.clean.waterball.agentlearningapp.Secret;
import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.ResponseModel;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.SignInModel;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.SignUpModel;
import com.ood.clean.waterball.agentlearningapp.utils.ResponseUtils;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class UserRetrofitRepository implements UserRepository {
    private final static String TAG = "UserRetrofitRepository";
    private UserAPI userAPI;

    public UserRetrofitRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Secret.IP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userAPI = retrofit.create(UserAPI.class);
    }

    @Override
    public ResponseModel<User> signIn(SignInModel signInModel) throws IOException {
        Log.d(TAG, "signIn" + signInModel.getAccount());
        Response<ResponseModel<User>> response = userAPI.signIn(signInModel.getAccount(), signInModel.getPassword()).execute();
        ResponseModel<User> responseModel = ResponseUtils.getBody(response);
        assert responseModel != null;
        Log.d(TAG, responseModel.toString());
        return responseModel;
    }

    @Override
    public ResponseModel<User> signUp(SignUpModel signUpModel) throws IOException {
        Log.d(TAG, "signUp" + signUpModel.getName());
        Response<ResponseModel<User>> response = userAPI.signUp(signUpModel.getName(), signUpModel.getGender(), signUpModel.getAccount(), signUpModel.getPassword(), signUpModel.getCityId(), signUpModel.getAge()).execute();
        ResponseModel<User> responseModel = ResponseUtils.getBody(response);
        assert responseModel != null;
        Log.d(TAG, responseModel.toString());
        return responseModel;
    }

    @Override
    public ResponseModel<User> actionOnActivity(int userId, int activityId, String action, boolean value) throws IOException {
        return null;
    }

    @Override
    public ResponseModel<User> pushUserPreferences(int userId, int activityId, int browsingTime, int clickTime) throws IOException {
        return null;
    }

    @Override
    public ResponseModel<User> getUserRelatedActivities(int userId, String type, int count) throws IOException {
        Response<ResponseModel<User>> response = userAPI.getUserRelatedActivities(userId, type, count).execute();
        ResponseModel<User> responseModel = ResponseUtils.getBody(response);
        assert responseModel != null;
        Log.d(TAG, responseModel.toString());
        return responseModel;
    }

    @Override
    public ResponseModel<User> getAssociationBetweenUserAndActivity(int userId, int activityId) throws IOException {
        Response<ResponseModel<User>> response = userAPI.getAssociationBetweenuserAndActivity(userId, activityId).execute();
        ResponseModel<User> responseModel = ResponseUtils.getBody(response);
        assert responseModel != null;
        Log.d(TAG, responseModel.toString());
        return responseModel;
    }

    @Override
    public void removeUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    public interface UserAPI{
        String RESOURCE = "HZN/api/User";

        @Headers("Content-Type:application/x-www-form-urlencoded")
        @FormUrlEncoded
        @POST(RESOURCE + "/signUp")
        Call<ResponseModel<User>> signUp(@Field("name") String name,
                                         @Field("gender") boolean gender,
                                         @Field("account") String account,
                                         @Field("password") String password,
                                         @Field("cityId") int cityId,
                                         @Field("age") int age);

        @Headers("Content-Type:application/x-www-form-urlencoded")
        @FormUrlEncoded
        @POST(RESOURCE + "/signIn")
        Call<ResponseModel<User>> signIn(@Field("account") String account,
                                         @Field("password") String password);

        @Headers("Content-Type:application/x-www-form-urlencoded")
        @FormUrlEncoded
        @POST(RESOURCE + "/{userId}/")
        Call<ResponseModel<User>> actionOnActivity(@Path("userId") int userId,
                                                   @Field("activityId") int activityId,
                                                   @Field("action") String action,
                                                   @Field("value") boolean value);

        @Headers("Content-Type:application/x-www-form-urlencoded")
        @GET(RESOURCE + "/{userId}/related/")
        Call<ResponseModel<User>> getUserRelatedActivities(@Path("userId") int userId,
                                                           @Query("type") String type,
                                                           @Query("count") int count);

        @Headers("Content-Type:application/x-www-form-urlencoded")
        @FormUrlEncoded
        @POST(RESOURCE + "/{userId}/pref/")
        Call<ResponseModel<User>> pushUserPreferences(@Path("userId") int userId,
                                                   @Field("activityId") int activityId,
                                                   @Field("browsingTime") int browsingTime,
                                                   @Field("clickTime") int clickTime);

        @Headers("Content-Type:application/x-www-form-urlencoded")
        @GET(RESOURCE + "/{userId}/association/{activityId}")
        Call<ResponseModel<User>> getAssociationBetweenuserAndActivity(@Path("userId") int userId,
                                                           @Path("activityId") int activityId);

    }
}
