package com.example.myapp;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.Color;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

import java.io.File;

public class MyApp extends Application {
    private File file;

    @Override
    public void onCreate() {
        super.onCreate();
        String mPath = getExternalCacheDir().getPath() + "/day04_test";
        file = new File(mPath);
        //初始化ImageLoader
        ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(this)
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024)) //可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)  // 内存缓存的最大值
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb sd卡(本地)缓存的最大值
                .diskCacheFileCount(100)  // 可以缓存的文件数量
                .diskCache(new UnlimitedDiskCache(file))//UnlimitedDiskCache 限制这个图片的缓存路径
                .defaultDisplayImageOptions(Options())
                .build();
        ImageLoader.getInstance().init(build);//全局初始化此配置
    }

    private DisplayImageOptions Options() {
        return new DisplayImageOptions.Builder()
                //.showStubImage(R.mipmap.bw_er)//缓冲过程中图片
                //.showImageForEmptyUri(R.mipmap.bw_empty)// 设置图片Uri为空或是错误的时候显示的图片
                // .showImageOnFail(R.mipmap.bw_wf)// 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)
                .cacheOnDisc(true)//缓存到硬盘
                .bitmapConfig(Bitmap.Config.ARGB_8888) //设置图片的解码类型
                // .displayer(new RoundedBitmapDisplayer(30,10))//圆角
                .displayer(new CircleBitmapDisplayer(Color.RED, 10))//圆形
                .build();
    }

}
