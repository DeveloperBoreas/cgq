package com.boreas.view.IViewInterface;

public interface ILoginViewInterface<T> extends ViewInterface {
    void onSuccess(boolean isSuccess, T data);

    void onFailed(String msg);
}
