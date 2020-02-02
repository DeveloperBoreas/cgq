package com.boreas.view.IViewInterface;

import com.boreas.view.ViewInterface;

public interface IForgetViewInterface extends ViewInterface {
    void onSuccess(String data);

    void onFailed(String msg);
}
