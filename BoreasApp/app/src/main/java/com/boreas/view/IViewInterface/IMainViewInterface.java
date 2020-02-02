package com.boreas.view.IViewInterface;

import com.boreas.view.ViewInterface;

public interface IMainViewInterface<T> extends ViewInterface {
    void onSuccess(boolean isSuccess, T data);

    void onFailed(String msg);

    void onDeleteSuccess(String msg, int index);
}
