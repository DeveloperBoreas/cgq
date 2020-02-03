package com.boreas.view.IViewInterface;

public interface IEditViewInterface extends ViewInterface {
    void onSuccess(boolean isSuccess, Object data);

    void onFailed(String msg);

    void onUpdateSuccess(String msg);

    void onUpLoadSuccess(String msg);
}
