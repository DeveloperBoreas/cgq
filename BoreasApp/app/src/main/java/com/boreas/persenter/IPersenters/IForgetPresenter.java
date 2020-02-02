package com.boreas.persenter.IPersenters;

import com.boreas.modle.LoginReceBean;
import com.boreas.persenter.Persenter;

public abstract class IForgetPresenter extends Persenter {

    public abstract void save(String phone);
}
