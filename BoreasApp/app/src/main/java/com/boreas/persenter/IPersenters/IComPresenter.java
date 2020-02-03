package com.boreas.persenter.IPersenters;

import com.boreas.modle.LoginReceBean;
import com.boreas.persenter.Persenter;

public abstract class IComPresenter extends Persenter {

    public abstract void save(LoginReceBean.DataBean.Composition composition);

    public abstract void update(LoginReceBean.DataBean.Composition composition);
}
