package com.boreas.view.IViewInterface;

import com.boreas.view.ViewInterface;

public interface ILoginViewInterface<T> extends ViewInterface {
    void onSuccess(boolean isSuccess, T data);

    void onFailed(String msg);
}
