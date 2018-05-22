package com.ood.clean.waterball.agentlearningapp.modles.repositories;

import android.util.Log;

import com.ood.clean.waterball.agentlearningapp.Secret;
import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.ResponseModel;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.SignInModel;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.SignUpModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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
        return userAPI.signIn(signInModel.getAccount(), signInModel.getPassword()).execute().body();
    }

    @Override
    public ResponseModel<User> signUp(SignUpModel signUpModel) throws IOException {
        Log.d(TAG, "signUp" + signUpModel.getName());
        return userAPI.signUp(signUpModel.getName(),
                        signUpModel.getGender(),
                        signUpModel.getAccount(),
                        signUpModel.getPassword(),
                        signUpModel.getCityId(),
                        signUpModel.getAge()).execute().body();
    }

    @Override
    public void removeUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    public interface UserAPI{
        String RESOURCE = "HZN/api/user";

        @Headers("Content-Type:application/json")
        @FormUrlEncoded
        @POST(RESOURCE + "/signUp")
        public Call<ResponseModel<User>> signUp(@Field("name") String name,
                                                @Field("gender") boolean gender,
                                                @Field("account") String account,
                                                @Field("password") String password,
                                                @Field("cityId") int cityId,
                                                @Field("age") int age);

        @Headers("Content-Type:application/json")
        @FormUrlEncoded
        @POST(RESOURCE + "/signIn")
        public Call<ResponseModel<User>> signIn(@Field("account") String account,
                                                @Field("password") String password);
    }
}
