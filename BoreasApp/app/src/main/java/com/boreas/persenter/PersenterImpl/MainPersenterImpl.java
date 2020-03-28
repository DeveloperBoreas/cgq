package com.boreas.persenter.PersenterImpl;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.boreas.framework.RxTimer;
import com.boreas.persenter.IPersenters.IMainPersenter;
import com.boreas.view.IViewInterface.IMainViewInterface;
import com.orhanobut.logger.Logger;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPersenterImpl extends IMainPersenter {
    private IMainViewInterface iMainViewInterface;
    private Context context;

    public MainPersenterImpl(IMainViewInterface iMainViewInterface) {
        super();
        this.iMainViewInterface = iMainViewInterface;
    }

    public MainPersenterImpl setContext(Context context) {
        this.context = context;
        return this;
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
    public void exportFile(boolean delete) {
        if (isNetWorkEnable()) {
            apiService.download(delete)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {
                        Log.e("----", "----------------------" + Environment.getExternalStorageDirectory().getAbsolutePath());
                        Headers headers = data.headers();
                        Log.e("----", "----------------------" + headers.get("fileName"));
                        Log.e("----", "----------------------" + Environment.getExternalStorageState());
                        this.writeResponseToDisk(headers.get("fileName"), data.body(), this.iMainViewInterface);
                        this.iMainViewInterface.onExportFileSuccess(headers.get("fileName"));
                    }, throwable -> new RxTimer().timer(300, number -> iMainViewInterface.onFailed(throwable.getMessage())));
        } else {
            this.noNetWork();
        }
    }

    private void writeResponseToDisk(String fileName, ResponseBody response, IMainViewInterface iMainViewInterface) {

        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {

            is = response.byteStream();
            long total = response.contentLength();
            File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(externalFilesDir,fileName);
            fos = new FileOutputStream(file);
//            File tempFile = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            long sum = 0;
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
                sum += len;
                int progress = (int) (sum * 1.0f / total * 100);
                iMainViewInterface.onUpdateProgressCurrent(progress);
//                Log.e("writeResponseToDisk", "下载完成进度 ：" + progress + "_________" + len + "_________" + sum + "_______" + total);
            }
            fos.flush();
            Logger.e("下载完成");
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
        } catch (Exception e) {
            Logger.e("下载失败");
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {

            }

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
