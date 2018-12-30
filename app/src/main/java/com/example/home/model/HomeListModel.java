package com.example.home.model;

import com.example.network.OkHttp3;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeListModel implements IHomeListModel {
    public static final String TAG = "HomeListModel";
    //接口变量
    ModuleInterface moduleInterface;
    //构造器
    public HomeListModel(ModuleInterface moduleInterface) {
        this.moduleInterface = moduleInterface;
    }

    @Override
    public void getModuleDate(String url) {
        //网络请求OKHTTP
        OkHttp3.OkHttpget(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                moduleInterface.LoadSuccess(response.body().string());
            }
        });
    }

    //定义接口
    public interface ModuleInterface{
        //定义成功失败回调的方法
        void LoadSuccess(String data);
        void LoadFailed();
    }
}
