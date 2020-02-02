package com.boreas.view.IViewInterface;

import com.boreas.view.ViewInterface;

public interface IEditViewInterface extends ViewInterface {
    void onSuccess(boolean isSuccess, Object data);

    void onFailed(String msg);

    void onUpdateSuccess(String msg);
}
