package com.boreas.view.IViewInterface;

import com.boreas.view.ViewInterface;

public interface IClipueViewInterface extends ViewInterface {
    void onSuccess();

    void onFailed(String msg);
}
