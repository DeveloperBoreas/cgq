package com.boreas.persenter.PersenterImpl;

import com.boreas.framework.RxTimer;
import com.boreas.persenter.IPersenters.IMainPersenter;
import com.boreas.view.IViewInterface.IMainViewInterface;
import com.orhanobut.logger.Logger;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPersenterImpl extends IMainPersenter {
    private IMainViewInterface iMainViewInterface;

    public MainPersenterImpl(IMainViewInterface iMainViewInterface) {
        super();
        this.iMainViewInterface = iMainViewInterface;
    }

    @Override
    public void requestUserInfoList() {
        if (isNetWorkEnable()) {
            apiService.queryUserList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {
                        Logger.e("requestUserInfoList : " + data);
                        if (data.getRetCode() == 0) {
                            new RxTimer().timer(300, number -> iMainViewInterface.onSuccess(true, data.getData()));
                        } else {
                            new RxTimer().timer(300, number -> iMainViewInterface.onFailed("数据异常"));
                        }
                    }, throwable -> new RxTimer().timer(300, number -> iMainViewInterface.onFailed(throwable.getMessage())));
        } else {
            this.noNetWork();
        }
    }

    @Override
    public void deleteUserInfo(int id, int position) {
        if (isNetWorkEnable()) {
            apiService.deleteById(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {
                        if (data.getRetCode() == 0) {
                            new RxTimer().timer(300, number -> iMainViewInterface.onDeleteSuccess("删除成功", position));
                        } else {
                            new RxTimer().timer(300, number -> iMainViewInterface.onFailed("数据异常"));
                        }
                    }, throwable -> new RxTimer().timer(300, number -> iMainViewInterface.onFailed(throwable.getMessage())));
        } else {
            this.noNetWork();
        }
    }

    @Override
    public void noNetWork() {
        iMainViewInterface.showNoNetWork();
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
