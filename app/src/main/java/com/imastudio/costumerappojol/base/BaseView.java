package com.imastudio.costumerappojol.base;

public interface BaseView <T extends BasePresenter> {
     void onAttachView();
     void onDetachView();

}
