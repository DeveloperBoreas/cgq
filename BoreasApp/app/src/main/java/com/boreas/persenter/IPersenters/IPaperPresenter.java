package com.boreas.persenter.IPersenters;

import com.boreas.modle.LoginReceBean;
import com.boreas.persenter.Persenter;

import java.util.ArrayList;

public abstract class IPaperPresenter extends Persenter {
    public abstract void save(LoginReceBean.DataBean.ResearchPaper paper);

    public abstract void saves(ArrayList<LoginReceBean.DataBean.ResearchPaper> papers);

    public abstract void update(LoginReceBean.DataBean.ResearchPaper paper);
}
