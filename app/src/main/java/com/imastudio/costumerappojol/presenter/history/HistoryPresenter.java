package com.imastudio.costumerappojol.presenter.history;

import com.imastudio.costumerappojol.base.BaseView;
import com.imastudio.costumerappojol.model.modelhistory.DataHistory;
import com.imastudio.costumerappojol.model.modelhistory.ResponseHistory;
import com.imastudio.costumerappojol.network.InitRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryPresenter implements HistoryContract.Presenter {

    HistoryContract.View historyView;
    BaseView view;
    public static List<DataHistory> dataHistory;
    public static List<DataHistory> dataHistoryComplete;

    public HistoryPresenter(HistoryContract.View view) {
        historyView = view;
    }

    @Override
    public void getDataHistory(String iduser, String status, String token, String device) {

        historyView.showLoading("proses get data history");
        if (status.equals("2")) {
            InitRetrofit.getInstance().getDataHistory(iduser, status, token, device).enqueue(new Callback<ResponseHistory>() {
                @Override
                public void onResponse(Call<ResponseHistory> call, Response<ResponseHistory> response) {
                    historyView.hideLoading();
                    if (response.isSuccessful()) {
                        String result = response.body().getResult();
                        String msg = response.body().getMsg();
                        if (result.equals("true")) {
                            historyView.showMsg(msg);
                            dataHistory = response.body().getData();
                            historyView.dataHistory(dataHistory);
                        } else {
                            historyView.showMsg(msg);

                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseHistory> call, Throwable t) {
                    historyView.showError(t.getLocalizedMessage());
                    historyView.hideLoading();

                }
            });
        } else if (status.equals("4")) {
            InitRetrofit.getInstance().getDataHistory(iduser, status, token, device).enqueue(new Callback<ResponseHistory>() {
                @Override
                public void onResponse(Call<ResponseHistory> call, Response<ResponseHistory> response) {
                    historyView.hideLoading();
                    if (response.isSuccessful()) {
                        String result = response.body().getResult();
                        String msg = response.body().getMsg();
                        if (result.equals("true")) {
                            historyView.showMsg(msg);
                            dataHistoryComplete = response.body().getData();
                            historyView.dataHistory(dataHistoryComplete);
                        } else {
                            historyView.showMsg(msg);

                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseHistory> call, Throwable t) {
                    historyView.showError(t.getLocalizedMessage());
                    historyView.hideLoading();

                }
            });
        }

    }

    @Override
    public void onAttach(BaseView view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        view = null;
    }
}
