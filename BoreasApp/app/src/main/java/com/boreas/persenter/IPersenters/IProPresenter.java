package com.boreas.persenter.IPersenters;

import com.boreas.modle.LoginReceBean;
import com.boreas.persenter.Persenter;

public abstract class IProPresenter extends Persenter {

    public abstract void save(LoginReceBean.DataBean.ResearchPro pro, boolean isEdit);
}
