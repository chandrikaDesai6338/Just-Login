package com.work.justlogin.network;


import com.work.justlogin.model.Response1;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitAPIInterface {

/*    @FormUrlEncoded
    @POST("s8cgi")
    Call<Response1> insertUser(
            @Field("password") String password,
            @Field("email") String email,
            Callback<Response1> callback);*/

    @GET("s8cgi")
    Call<Response1> insertUser();

}
