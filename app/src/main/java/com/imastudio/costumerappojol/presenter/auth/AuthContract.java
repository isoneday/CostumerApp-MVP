package com.imastudio.costumerappojol.presenter.auth;

import android.content.DialogInterface;

import com.imastudio.costumerappojol.base.BasePresenter;
import com.imastudio.costumerappojol.base.BaseView;
import com.imastudio.costumerappojol.model.modelauth.ResponseAuth;

public interface AuthContract {

    interface Presenter extends BasePresenter {
         void prosesLogin(String email, String password, String device, DialogInterface dialogInterface);
         void prosesRegister(String email, String password, String nama, String phone, DialogInterface dialogInterface);
    }
    interface View extends BaseView<BasePresenter> {
         void showLoading(String register);
         void hideLoading();
         void showError(String localizedMessage);
         void showMsg(String msg);
         void pindahHalaman(ResponseAuth dataUser);

    }
}
