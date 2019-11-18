package com.imastudio.costumerappojol.network;


import com.imastudio.costumerappojol.model.ResponseRegister;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestApi {


    @FormUrlEncoded
    @POST("daftar")
    Call<ResponseRegister> registerUser(
        @Field("nama") String strnama,
        @Field("phone") String strphone,
        @Field("password") String strpassword,
        @Field("email") String stremail
    );
}