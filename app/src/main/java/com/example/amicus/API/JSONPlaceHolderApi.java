package com.example.amicus.API;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

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
    Call<AddTravelResponce> addTravel(@Body AddBody addBody);

    @Multipart
    @POST("/uploadphoto")
    Call<ResponseBody> uploadphoto(@Part MultipartBody.Part part, @Part("somebody") RequestBody requestBody);

}