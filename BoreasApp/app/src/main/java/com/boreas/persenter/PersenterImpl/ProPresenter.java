package com.boreas.persenter.PersenterImpl;

import com.boreas.framework.RxTimer;
import com.boreas.modle.LoginReceBean;
import com.boreas.persenter.IPersenters.IProPresenter;
import com.boreas.view.IViewInterface.IProViewInterface;
import com.orhanobut.logger.Logger;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProPresenter extends IProPresenter {
    private IProViewInterface iProViewInterface;

    public ProPresenter(IProViewInterface iProViewInterface) {
        super();
        this.iProViewInterface = iProViewInterface;
    }

    @Override
    public void save(LoginReceBean.DataBean.ResearchPro pro, boolean isEdit) {
        if (isNetWorkEnable()) {
            apiService.insertPro(pro)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {
                        if (data.getRetCode() == 0) {
                            new RxTimer().timer(300, number -> iProViewInterface.onSuccess(isEdit));
                        } else {
                            new RxTimer().timer(300, number -> iProViewInterface.onFailed(data.getMsg()));
                        }
                    }, e -> {
                        Logger.e(e.getMessage());
                        new RxTimer().timer(300, number -> iProViewInterface.onFailed(e.getMessage()));
                    });
        } else {
            this.noNetWork();
        }
    }

    @Override
    public void noNetWork() {

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
