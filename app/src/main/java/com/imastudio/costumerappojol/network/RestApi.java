package com.imastudio.costumerappojol.network;


import com.imastudio.costumerappojol.model.modelauth.ResponseAuth;
import com.imastudio.costumerappojol.model.modelmap.ResponseMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApi {


    @FormUrlEncoded
    @POST("daftar")
    Call<ResponseAuth> registerUser(
            @Field("nama") String strnama,
            @Field("phone") String strphone,
            @Field("password") String strpassword,
            @Field("email") String stremail
    );

    @FormUrlEncoded
    @POST("login")
    Call<ResponseAuth> loginUser(
            @Field("f_password") String strpassword,
            @Field("f_email") String stremail,
            @Field("device") String strdevice
    );

    @GET("json")
    Call<ResponseMap> getDataMap(
            @Query("origin") String origin,
            @Query("destination") String destination,
            @Query("key") String key
    );

}