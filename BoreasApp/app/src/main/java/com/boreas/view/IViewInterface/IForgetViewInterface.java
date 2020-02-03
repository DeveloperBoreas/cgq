package com.boreas.view.IViewInterface;

public interface IForgetViewInterface extends ViewInterface {
    void onSuccess(String data);

    void onFailed(String msg);
}
