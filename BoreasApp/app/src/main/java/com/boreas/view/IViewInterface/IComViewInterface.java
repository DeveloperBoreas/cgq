package com.boreas.view.IViewInterface;

import com.boreas.view.ViewInterface;

public interface IComViewInterface extends ViewInterface {
    void onSuccess(boolean  isEdit);

    void onFailed(String msg);
}
