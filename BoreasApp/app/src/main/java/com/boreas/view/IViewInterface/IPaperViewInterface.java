package com.boreas.view.IViewInterface;

public interface IPaperViewInterface extends ViewInterface {
    void onSuccess();

    void onUpdateSuccess();

    void onFailed(String msg);
}
