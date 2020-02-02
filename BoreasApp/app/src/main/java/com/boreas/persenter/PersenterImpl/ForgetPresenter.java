package com.boreas.persenter.PersenterImpl;

import com.boreas.framework.RxTimer;
import com.boreas.persenter.IPersenters.IForgetPresenter;
import com.boreas.view.IViewInterface.IForgetViewInterface;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ForgetPresenter extends IForgetPresenter {
    private IForgetViewInterface iForgetViewInterface;

    public ForgetPresenter(IForgetViewInterface iForgetViewInterface) {
        super();
        this.iForgetViewInterface = iForgetViewInterface;
    }

    @Override
    public void save(String phone) {
        if (isNetWorkEnable()) {
            this.apiService.forgetUserInfo(phone)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {
                        if (data.getRetCode() == 0) {
                            new RxTimer().timer(500, number -> this.iForgetViewInterface.onSuccess(data.getMsg()));
                        } else {
                            new RxTimer().timer(500, number -> this.iForgetViewInterface.onFailed(data.getMsg()));
                        }
                    }, throwable -> {
                        this.iForgetViewInterface.onFailed(throwable.getMessage());
                    });
        } else {
            this.noNetWork();
        }
    }

    @Override
    public void noNetWork() {
        this.iForgetViewInterface.showNoNetWork();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestory() {

    }
}
