package com.boreas.persenter.IPersenters;

import com.boreas.modle.LoginReceBean;
import com.boreas.persenter.Persenter;

import java.util.ArrayList;

public abstract class IComPresenter extends Persenter {

    public abstract void saves(ArrayList<LoginReceBean.DataBean.Composition> compositions);

    public abstract void save(LoginReceBean.DataBean.Composition composition);

    public abstract void update(LoginReceBean.DataBean.Composition composition);
}
