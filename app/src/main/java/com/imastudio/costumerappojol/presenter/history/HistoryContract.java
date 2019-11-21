package com.imastudio.costumerappojol.presenter.history;

import com.imastudio.costumerappojol.base.BasePresenter;
import com.imastudio.costumerappojol.base.BaseView;
import com.imastudio.costumerappojol.model.modelhistory.DataHistory;

import java.util.List;

public interface HistoryContract {
    interface Presenter extends BasePresenter {
      void  getDataHistory(String iduser, String s, String token, String device);
    }
    interface View extends BaseView<BasePresenter>{
        void showLoading(String pesanloading);
        void hideLoading();
        void showError(String localizedMessage);
        void showMsg(String msg);

        void dataHistory(List<DataHistory> dataHistory);
    }
}
