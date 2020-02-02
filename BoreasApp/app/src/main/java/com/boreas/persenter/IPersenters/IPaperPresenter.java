package com.boreas.persenter.IPersenters;

import com.boreas.modle.LoginReceBean;
import com.boreas.persenter.Persenter;

public abstract class IPaperPresenter extends Persenter {
    public abstract void save(LoginReceBean.DataBean.ResearchPaper paper, boolean isEdit);
}
