package com.boreas.view.IViewInterface;

import com.boreas.view.ViewInterface;

public interface IProViewInterface extends ViewInterface {
    void onSuccess(boolean isEdit);

    void onFailed(String msg);
}
