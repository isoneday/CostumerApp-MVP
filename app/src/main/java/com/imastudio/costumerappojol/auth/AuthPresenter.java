package com.imastudio.costumerappojol.auth;

import android.content.DialogInterface;

import com.imastudio.costumerappojol.base.BaseView;
import com.imastudio.costumerappojol.model.ResponseRegister;
import com.imastudio.costumerappojol.network.InitRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthPresenter implements  AuthContract.Presenter {

    AuthContract.View authview;
    BaseView view;
    public AuthPresenter(AuthContract.View view) {
        this.authview = view;
    }

    @Override
    public void prosesLogin() {

    }

    @Override
    public void prosesRegister(String email, String password, String nama, String phone, DialogInterface dialogInterface) {
        authview.showLoading("Register");
        InitRetrofit.getInstance().
                registerUser(nama,phone,
                        password,email).enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                authview.hideLoading();
                if (response.isSuccessful()){
                    String msg = response.body().getMsg();
                    String result = response.body().getResult();
                    if (result.equals("true")){
                        authview.showMsg(msg);
                        dialogInterface.dismiss();
                    }else{
                        authview.showMsg(msg);

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                authview.showError(t.getLocalizedMessage());
                authview.hideLoading();
            }
        });


    }


    @Override
    public void onAttach(BaseView view) {
        this.view=view;
    }

    @Override
    public void onDetach() {
        view = null;
    }
}
