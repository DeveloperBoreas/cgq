package com.boreas.view.IViewInterface;

public interface IMainViewInterface<T> extends ViewInterface {
    void onSuccess(boolean isSuccess, T data);

    void onFailed(String msg);

    void onDeleteSuccess(String msg, int index);

    void onUpdateProgressCurrent(int num);

    void onExportFileSuccess(String path);
}
