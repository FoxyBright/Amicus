package com.example.amicus.API;

import com.example.amicus.Registration;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JSONPlaceHolderApi {
    @POST("/autorization")
    Call<AuthorizationResponce> authUser(@Body AuthorizationBody authorizationBody);

    @POST("/registration")
    Call<RegistrationResponce> regUser(@Body RegistrationBody registrationBody);
}
