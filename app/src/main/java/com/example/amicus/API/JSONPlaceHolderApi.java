package com.example.amicus.API;

import com.example.amicus.CardResponce;
import com.example.amicus.PassagerData;
import com.example.amicus.UpdateProfile;
import com.example.amicus.VoditelData;

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

    @POST("addtravel/{id}")
    Call<AddTravelResponce> addTravel(@Body AddBody addBody, @Path ("id") int groupId);

    @Multipart
    @POST("/uploadphoto/{id}")
    Call<ResponseBody> uploadphoto(@Path("id") int groupId,@Part MultipartBody.Part part, @Part("somebody") RequestBody requestBody);

    @GET("/getuserdata/{id}")
    Call<GetUserData> getUsData(@Path("id") int groupId);

    @GET("/gettravelswhereuserdriver/{id}")
    Call<List<VoditelData>> getVodila(@Path("id") int groupId);

    @GET("/gettravelwhereuserpassenger/{id}")
    Call<List<PassagerData>> getPassager(@Path("id") int groupId);

    @GET("/getuserscards/{id}")
    Call<List<CardResponce>> cardListResponce(@Path("id") int groupId);

    @GET("/addusertotravel/{travelid}/{userid}")
    Call<AddTravelToresponce> humanAdd(@Path("travelid") int groupId,@Path("userid") int humanId);

    @POST("/updateautodata")
    Call<UpdateAuto> updateAuto(@Body UpdateBody addBody);

    @GET("/delauto/{id}")
    Call<UpdateAuto> delAuto(@Path("id") String groupId);

    @GET("/deluser/{id}")
    Call<UpdateAuto> delUser(@Path("id") int groupId);

    @POST("/updateuserdata")
    Call<UpdateAuto> upUser(@Body UpdateProfile addBody);
}