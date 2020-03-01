package com.boreas.view.IViewInterface;

public interface IProViewInterface extends ViewInterface {
    void onSuccess();

    void onUpdateSuccess();

    void onFailed(String msg);

    void onAddProsSuccess();
}
