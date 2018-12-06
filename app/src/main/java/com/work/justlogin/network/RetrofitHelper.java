package com.work.justlogin.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    public static String BASE_URL = "https://api.myjson.com/bins/";

    public RetrofitAPIInterface getRetrofitAPIInterface(){
        final Retrofit retrofit = createRetrofit();
        return retrofit.create(RetrofitAPIInterface.class);
    }

    private OkHttpClient createOkHttpClient() {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        return httpClient.build();
    }

    /**
     * Creates a pre configured Retrofit instance
     */
    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClient())
                .build();
    }
}
