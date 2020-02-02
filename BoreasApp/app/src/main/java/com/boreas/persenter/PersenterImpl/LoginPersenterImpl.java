package com.boreas.persenter.PersenterImpl;

import com.boreas.framework.RxTimer;
import com.boreas.persenter.IPersenters.ILoginPersenter;
import com.boreas.view.IViewInterface.ILoginViewInterface;
import com.orhanobut.logger.Logger;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginPersenterImpl extends ILoginPersenter {

    private ILoginViewInterface viewInterface;

    public LoginPersenterImpl(ILoginViewInterface viewInterface) {
        super();
        this.viewInterface = viewInterface;
    }

    @Override
    public void noNetWork() {
        viewInterface.showNoNetWork();
    }

    @Override
    public void register(String name, String psd) {
    }

    @Override
    public void login(String name, String psd) {
        if (isNetWorkEnable()) {
            this.apiService.login(name, psd)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(s -> {
                        Logger.e("onSuccess:" + s);
                        if (s.getRetCode() == 0) {
                            new RxTimer().timer(300, number -> viewInterface.onSuccess(true, s));
                        } else {
                            new RxTimer().timer(300,number -> viewInterface.onFailed(s.getMsg()));
                        }
                    }, throwable -> viewInterface.onFailed(throwable.getMessage()));
        } else {
            this.noNetWork();
        }
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
