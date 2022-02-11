package com.example.amicus.API;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JSONPlaceHolderApi {
    @POST("/autorization")
    Call<AuthorizationResponce> authUser(@Body AuthorizationBody authorizationBody);
}
