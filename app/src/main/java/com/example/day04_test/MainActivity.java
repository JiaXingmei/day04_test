package com.example.day04_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.home.adapter.HomeAdapte;
import com.example.home.bean.HomeBean;
import com.example.home.presenter.HomeListPresenter;
import com.example.home.view.IHomeListView;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class MainActivity extends AppCompatActivity implements IHomeListView {

    private TextView viewById;
    private PullToRefreshListView pullView;
    private HomeListPresenter homeListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewById = findViewById(R.id.tv);
        pullView = findViewById(R.id.pulltorefrereshlistview_id);
        //初始化presenter
        homeListPresenter = new HomeListPresenter(this);
        homeListPresenter.getPresenterDate();
        pullView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }

    @Override
    public void getViewData(String mViewData) {
        Gson gson = new Gson();
        HomeBean homeBean = gson.fromJson(mViewData, HomeBean.class);
        HomeBean.ResultBean list = homeBean.getResult();
        HomeAdapte homeAdapter = new HomeAdapte(MainActivity.this, list);
        pullView.setAdapter(homeAdapter);
    }
}
