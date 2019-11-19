package com.imastudio.costumerappojol.presenter.map;

import com.imastudio.costumerappojol.base.BasePresenter;
import com.imastudio.costumerappojol.base.BaseView;

public interface MapContract {
    interface Presenter extends BasePresenter{
        void getDataMap(String s, String s1, String key);
        void requestOrder();
    }
    interface View extends BaseView<BasePresenter>{
        void showLoading();
        void hideLoading();
        void showError(String localizedMessage);
        void showMsg(String msg);
        void getDataInfoOrder(String text, String distanceText, String s);
        void dataBooking();
        void dataGaris(String dataGaris);
    }
}
