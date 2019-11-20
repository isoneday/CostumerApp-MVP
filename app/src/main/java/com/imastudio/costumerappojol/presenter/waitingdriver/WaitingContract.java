package com.imastudio.costumerappojol.presenter.waitingdriver;

import com.imastudio.costumerappojol.base.BasePresenter;
import com.imastudio.costumerappojol.base.BaseView;

public interface WaitingContract {
    interface Presenter extends BasePresenter{

        void cekStatusBooking(int idbooking);
        void cancelRequestBooking(int idbooking, String token, String device);
    }
    interface View extends BaseView<BasePresenter>{
        void showLoading(String pesanloading);
        void hideLoading();
        void showError(String localizedMessage);
        void showMsg(String msg);

        void getidDriver(String iddriver);

        void back();
    }
}
