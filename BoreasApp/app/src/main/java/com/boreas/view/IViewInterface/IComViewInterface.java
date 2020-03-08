package com.boreas.view.IViewInterface;

public interface IComViewInterface extends ViewInterface {
    void onSuccess();

    void onUpdateSuccess();

    void onFailed(String msg);

    void onAddCompositionsSuccess();
}
