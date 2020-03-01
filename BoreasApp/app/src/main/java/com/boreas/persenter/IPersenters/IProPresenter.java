package com.boreas.persenter.IPersenters;

import com.boreas.modle.LoginReceBean;
import com.boreas.persenter.Persenter;

import java.util.ArrayList;

public abstract class IProPresenter extends Persenter {

    public abstract void saves(ArrayList<LoginReceBean.DataBean.ResearchPro> pros);

    public abstract void save(LoginReceBean.DataBean.ResearchPro pro);

    public abstract void update(LoginReceBean.DataBean.ResearchPro pro);
}
