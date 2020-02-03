package com.boreas.persenter.PersenterImpl;

import com.boreas.modle.UserInfo;
import com.boreas.persenter.IPersenters.IEditPresenter;
import com.boreas.view.IViewInterface.IEditViewInterface;
import com.orhanobut.logger.Logger;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class EditPersenter extends IEditPresenter {
    private IEditViewInterface viewInterface;

    public EditPersenter(IEditViewInterface viewInterface) {
        super();
        this.viewInterface = viewInterface;
    }

    @Override
    public void queryClipue() {
        if (isNetWorkEnable()) {
            apiService.queryClipue()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {
                        if (data.getRetCode() == 0) {
                            Logger.e(data.toString());
                            viewInterface.onSuccess(true, data);
                        } else {
                            viewInterface.onFailed("数据返回异常");
                        }
                    }, e -> {
                        viewInterface.onFailed(e.getMessage());
                    });
        } else {
            this.noNetWork();
        }
    }

    @Override
    public void queryPro() {
        if (isNetWorkEnable()) {
            apiService.queryPro()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {
                        if (data.getRetCode() == 0) {
                            Logger.e(data.toString());
                            viewInterface.onSuccess(true, data);
                        } else {
                            viewInterface.onFailed("数据返回异常");
                        }
                    }, e -> {
                        viewInterface.onFailed(e.getMessage());
                    });
        } else {
            this.noNetWork();
        }
    }

    @Override
    public void queryPaper() {
        if (isNetWorkEnable()) {
            apiService.queryPaper()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {
                        if (data.getRetCode() == 0) {
                            Logger.e(data.toString());
                            viewInterface.onSuccess(true, data);
                        } else {
                            viewInterface.onFailed("数据返回异常");
                        }
                    }, e -> {
                        viewInterface.onFailed(e.getMessage());
                    });
        } else {
            this.noNetWork();
        }
    }

    @Override
    public void queryCom() {
        if (isNetWorkEnable()) {
            apiService.queryCom()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {
                        if (data.getRetCode() == 0) {
                            Logger.e(data.toString());
                            viewInterface.onSuccess(true, data);
                        } else {
                            viewInterface.onFailed("数据返回异常");
                        }
                    }, e -> {
                        viewInterface.onFailed(e.getMessage());
                    });
        } else {
            this.noNetWork();
        }
    }

    @Override
    public void queryPageAllMsg() {
        if (isNetWorkEnable()) {
            Observable.merge(apiService.queryClipue(), apiService.queryPro(), apiService.queryPaper(), apiService.queryCom())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object o) throws Exception {
                            Logger.e("queryPageAllMsg :" + o.toString());
                            viewInterface.onSuccess(true, o);
                        }
                    }, e -> {
                        Logger.e("queryPageAllMsg :" + e.getMessage());
                        viewInterface.onFailed(e.getMessage());
                    });
        } else {
            this.noNetWork();
        }
    }


    @Override
    public void addUser(UserInfo userInfo) {
        if (isNetWorkEnable()) {
            apiService.insertUserInfo(userInfo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {
                        if (data.getRetCode() == 0) {
                            Logger.e(data.toString());
                            viewInterface.onSuccess(true, data);
                        } else {
                            viewInterface.onFailed(data.getMsg());
                        }
                    }, e -> {
                        viewInterface.onFailed(e.getMessage());
                    });
        } else {
            this.noNetWork();
        }
    }

    @Override
    public void updateUser(UserInfo userInfo) {
        if (isNetWorkEnable()) {
            apiService.updateUserInfo(userInfo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {
                        if (data.getRetCode() == 0) {
                            viewInterface.onUpdateSuccess("更新教师信息成功");
                        } else {
                            viewInterface.onFailed(data.getMsg());
                        }
                    }, e -> {
                        viewInterface.onFailed(e.getMessage());
                    });
        } else {
            this.noNetWork();
        }
    }

    @Override
    public void uploadFile(String path, int id) {
        if (isNetWorkEnable()) {
            File file = new File(path);
            RequestBody fileRQ = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName() + "@@@@@" + id, fileRQ);
            apiService.uploadFile(part)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {
                        Logger.e(data.toString());
                        if (data.getRetCode() == 0) {
                            viewInterface.onUpLoadSuccess("上传成功" + data.getMsg());
                        } else {
                            viewInterface.onFailed(data.getMsg());
                        }
                    }, e -> {
                        viewInterface.onFailed(e.getMessage());
                    });
        } else {
            this.noNetWork();
        }
    }

    @Override
    public void noNetWork() {
        viewInterface.showNoNetWork();
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
