package com.example.amicus.API;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface JSONPlaceHolderApi {

    @POST("/registration")
    Call<RegistrationResponce> regUser(@Body RegistrationBody registrationBody);

    @POST("/authorization")
    Call<AuthorizationResponce> authUser(@Body AuthorizationBody authorizationBody);

    @GET("/getusersauto/{id}")
    Call<List<AutoResponce>> autoUser(@Path("id") int groupId);

    @POST("/searchtravel")
    Call<List<SerachTravel>> searchTrav(@Body SaerchBody body);

    @POST("/addauto")
    Call<AddAuto> addauto(@Body AddBodyAuto body);

    @POST("addtravel")
    Call<AddTravelResponce> addTravel(@Body AddBody addBody);

    @Multipart
    @POST("/uploadphoto")
    Call<ResponseBody> uploadphoto(@Part MultipartBody.Part part, @Part("somebody") RequestBody requestBody);

}