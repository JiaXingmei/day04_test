package com.example.home.presenter;

import android.util.Log;

import com.example.api.Api;
import com.example.day04_test.MainActivity;
import com.example.home.model.HomeListModel;

public class HomeListPresenter implements IHomeListPresenter,HomeListModel.ModuleInterface {
    MainActivity mainActivity;
    private final HomeListModel homeListModel;
    private String modelDate;

    public HomeListPresenter(MainActivity mainActivity) {
        //view层
        this.mainActivity = mainActivity;
        //初始化model
        homeListModel = new HomeListModel(HomeListPresenter.this);
    }

    @Override
    public void getPresenterDate() {
        //回调model
        homeListModel.getModuleDate(Api.SHOPLIST);
        Log.i("","getModelDate: "+modelDate);
    }

    @Override
    public void LoadSuccess(String data) {
        //data
        mainActivity.getViewData(data);
    }

    @Override
    public void LoadFailed() {

    }
}
