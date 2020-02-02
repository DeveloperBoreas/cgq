package com.boreas.persenter.IPersenters;

import com.boreas.persenter.Persenter;

public abstract class ILoginPersenter extends Persenter {
    public ILoginPersenter() {
        super();
    }

    public abstract void register(String name, String psd);

    public abstract void login(String name, String psd);
}
