package com.boreas.persenter;

import android.content.Context;

import com.boreas.App;
import com.boreas.api.ApiService;
import com.boreas.utils.NetWorkUtil;

public abstract class Persenter {
    public Context context;
    public ApiService apiService;

    public Persenter() {
        this.context = App.getInstance().getApplicationContext();
        this.apiService = App.getInstance().getApiService();
    }

    public boolean isNetWorkEnable() {
        if (context == null) {
            throw new NullPointerException("---------BaseRequest  context is not NULL !!!!!!!!!!!!!");
        }
        return NetWorkUtil.isNetWorkEnable(context);
    }


    public abstract void noNetWork();

    public abstract void onCreate();

    public abstract void onResume();

    public abstract void onDestory();
}
