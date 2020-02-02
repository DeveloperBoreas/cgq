package com.boreas;

import android.app.Application;

import com.boreas.api.ApiService;
import com.boreas.api.RetrofitClient;

public class App extends Application {

    private ApiService apiService;
    private static App app;
    private String name;
    private String psd;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        apiService = RetrofitClient.getInstance().getApiService();
    }

    public static App getInstance() {
        return app;
    }

    public ApiService getApiService() {
        return this.apiService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsd() {
        return psd;
    }

    public void setPsd(String psd) {
        this.psd = psd;
    }

}
