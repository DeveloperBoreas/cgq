package com.boreas.view.IViewInterface;

import com.boreas.view.ViewInterface;

public interface IPaperViewInterface extends ViewInterface {
    void onSuccess(boolean isEdit);

    void onFailed(String msg);
}
