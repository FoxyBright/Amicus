package com.example.amicus.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JSONPlaceHolderApi {
    @POST("/autorization")
    Call<AuthorizationResponce> authUser(@Body AuthorizationBody authorizationBody);

    @POST("/registration")
    Call<RegistrationResponce> regUser(@Body RegistrationBody registrationBody);

    @POST("/getusersauto")
    Call<List<AutoResponce>> autoUser(@Body AutoBody autoBody);

    @POST("/searchtravel")
    Call<List<SerachTravel>> searchTrav(@Body SaerchBody body);

    @POST("addtravel")
    Call<AddTravelResponce> addTravel (@Body AddBody addBody);
}
