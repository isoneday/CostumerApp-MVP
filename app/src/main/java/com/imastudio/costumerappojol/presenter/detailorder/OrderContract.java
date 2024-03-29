package com.imastudio.costumerappojol.presenter.detailorder;

import com.imastudio.costumerappojol.base.BasePresenter;
import com.imastudio.costumerappojol.base.BaseView;

public interface OrderContract {
    interface Presenter extends BasePresenter{
        void detailRute(String origin, String desti, String key);
        void completeBooking(String iduser, String idbooking, String token, String device);
    }
    interface View extends BaseView<BasePresenter>{
        void showLoading(String pesanloading);
        void hideLoading();
        void showError(String localizedMessage);
        void showMsg(String msg);

        void getDataMap(String dataGaris);

        void pindahHalaman();
    }
}
