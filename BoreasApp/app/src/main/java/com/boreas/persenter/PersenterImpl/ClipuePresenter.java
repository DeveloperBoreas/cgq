package com.boreas.persenter.PersenterImpl;

import com.boreas.framework.RxTimer;
import com.boreas.persenter.IPersenters.IClipuePresenter;
import com.boreas.view.IViewInterface.IClipueViewInterface;
import com.orhanobut.logger.Logger;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ClipuePresenter extends IClipuePresenter {
    private IClipueViewInterface clipueViewInterface;

    public ClipuePresenter(IClipueViewInterface clipueViewInterface) {
        super();
        this.clipueViewInterface = clipueViewInterface;
    }

    @Override
    public void saveClipue(String clipueName) {
        if (isNetWorkEnable()) {
            apiService.insertClipue(clipueName)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {
                        Logger.e(data.toString());
                        if (data.getRetCode() == 0) {
                            new RxTimer().timer(300, number -> clipueViewInterface.onSuccess());
                        } else {
                            new RxTimer().timer(300, number -> clipueViewInterface.onFailed(data.getMsg()));
                        }
                    }, e -> {
                        new RxTimer().timer(300, number -> clipueViewInterface.onFailed(e.getMessage()));
                    });
        } else {
            this.noNetWork();
        }
    }

    @Override
    public void noNetWork() {
        clipueViewInterface.showNoNetWork();
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
