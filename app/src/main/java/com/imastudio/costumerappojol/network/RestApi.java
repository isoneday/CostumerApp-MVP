package com.imastudio.costumerappojol.network;


import com.imastudio.costumerappojol.model.modelauth.ResponseAuth;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
}