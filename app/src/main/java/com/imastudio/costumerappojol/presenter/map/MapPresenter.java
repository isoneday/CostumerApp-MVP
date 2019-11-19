package com.imastudio.costumerappojol.presenter.map;

import com.imastudio.costumerappojol.base.BaseView;
import com.imastudio.costumerappojol.model.modelmap.Distance;
import com.imastudio.costumerappojol.model.modelmap.Duration;
import com.imastudio.costumerappojol.model.modelmap.LegsItem;
import com.imastudio.costumerappojol.model.modelmap.ResponseMap;
import com.imastudio.costumerappojol.model.modelmap.RoutesItem;
import com.imastudio.costumerappojol.network.InitRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapPresenter implements MapContract.Presenter {
MapContract.View mapView;
BaseView view;

public MapPresenter(MapContract.View view){
    mapView=view;
}


    @Override
    public void getDataMap(String origin, String destination, String key) {
        mapView.showLoading();
        InitRetrofit.getInstanceGoogle().getDataMap(origin, destination, key)
                .enqueue(new Callback<ResponseMap>() {
                    @Override
                    public void onResponse(Call<ResponseMap> call, Response<ResponseMap> response) {
                        mapView.hideLoading();
                        if (response.isSuccessful()){
                            String status = response.body().getStatus();
                            if (status.equals("OK")){
                                List<RoutesItem> dataMap = response.body().getRoutes();
                                List<LegsItem> legs = dataMap.get(0).getLegs();
                                Distance distance = legs.get(0).getDistance();
                                Duration duration = legs.get(0).getDuration();
                                Double harga = Math.ceil((distance.getValue()/1000)*5000);
                                mapView.getDataInfoOrder(duration.getText(),distance.getText(),
                                        harga.toString());
                                String dataGaris = dataMap.get(0).getOverviewPolyline().getPoints();
                                mapView.dataGaris(dataGaris);
                                mapView.showMsg("tampil data");
                            }else{
                                mapView.showMsg("gagal tampil data");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseMap> call, Throwable t) {
                    mapView.showError(t.getLocalizedMessage());
                    mapView.hideLoading();
                    }
                });
    }

    @Override
    public void requestOrder() {

    }

    @Override
    public void onAttach(BaseView view) {
    this.view=view;
    }

    @Override
    public void onDetach() {
    view=null;
    }
}
