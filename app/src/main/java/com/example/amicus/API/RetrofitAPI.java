package com.example.amicus.API;

import com.example.amicus.BuildConfig;
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAPI {
    public static final String BASE_URL = "https://амикусдрайв.рф";
    public static Retrofit mRetrofit;
    private static RetrofitAPI mInstance;


    private RetrofitAPI() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new OkHttpProfilerInterceptor());
        }
        OkHttpClient client = builder.build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static RetrofitAPI getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitAPI();
        }
        return mInstance;
    }

    public JSONPlaceHolderApi getJSONApi() {
        return mRetrofit.create(JSONPlaceHolderApi.class);
    }

}
