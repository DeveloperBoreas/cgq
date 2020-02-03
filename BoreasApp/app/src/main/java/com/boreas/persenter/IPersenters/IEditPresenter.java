package com.boreas.persenter.IPersenters;

import com.boreas.modle.UserInfo;
import com.boreas.persenter.Persenter;

public abstract class IEditPresenter extends Persenter {

    public IEditPresenter() {
        super();
    }

    public abstract void queryClipue();

    public abstract void queryPro();

    public abstract void queryPaper();

    public abstract void queryCom();

    public abstract void queryPageAllMsg();

    public abstract void addUser(UserInfo userInfo);

    public abstract void updateUser(UserInfo userInfo);

    public abstract void uploadFile(String path, int id);
}
