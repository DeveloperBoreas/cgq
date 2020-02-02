package com.boreas.persenter.IPersenters;

import com.boreas.persenter.Persenter;

public abstract class IClipuePresenter extends Persenter {

    public IClipuePresenter() {
        super();
    }

    public abstract void saveClipue(String clipueName);
}
