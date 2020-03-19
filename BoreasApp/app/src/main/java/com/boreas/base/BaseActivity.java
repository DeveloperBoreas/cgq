package com.boreas.base;

import android.app.ProgressDialog;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.boreas.App;
import com.boreas.api.ApiService;
import com.boreas.receiver.NetWorkRiciver;

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    private T binding;
    public ApiService apiService;
    public NetWorkRiciver netWorkReceiver;
    private IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setVissIble();
        this.binding = DataBindingUtil.setContentView(this, this.getView());
        this.initNetWorkChange();
        this.initPersenter();
        this.initView(this.binding);
        this.apiService = App.getInstance().getApiService();
        this.initViewData();
        this.registerReceiver(netWorkReceiver, filter);
    }

    public void setVissIble() {

    }

    private void initNetWorkChange() {
        netWorkReceiver = new NetWorkRiciver();
        filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
    }

    private ProgressDialog progressDialog;

    public void showLoadingDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(ProgressDialog.THEME_HOLO_LIGHT);
        }
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    public ProgressDialog showProgressLoadingDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMax(100);
        return progressDialog;
    }

    public void dimissLoadingDialog() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (netWorkReceiver != null) {
            unregisterReceiver(netWorkReceiver);
        }
    }

    public abstract void initPersenter();

    public abstract void initViewData();

    public abstract int getView();

    public abstract void initView(T t);

}
