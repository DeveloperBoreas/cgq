package com.boreas.persenter.IPersenters;

import com.boreas.persenter.Persenter;

public abstract class IMainPersenter extends Persenter {
    public abstract void requestUserInfoList();

    public abstract void deleteUserInfo(int id, int position);

    public abstract void exportFile(boolean delete);
}
