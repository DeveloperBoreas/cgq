package com.boreas.persenter.PersenterImpl;

import com.boreas.framework.RxTimer;
import com.boreas.modle.LoginReceBean;
import com.boreas.persenter.IPersenters.IPaperPresenter;
import com.boreas.view.IViewInterface.IPaperViewInterface;
import com.orhanobut.logger.Logger;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PaperPresenter extends IPaperPresenter {
    private IPaperViewInterface iPaperViewInterface;

    public PaperPresenter(IPaperViewInterface iPaperViewInterface) {
        super();
        this.iPaperViewInterface = iPaperViewInterface;
    }

    @Override
    public void save(LoginReceBean.DataBean.ResearchPaper paper, boolean isEdit) {
        if (isNetWorkEnable()) {
            apiService.insertPaper(paper)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {
                        if (data.getRetCode() == 0) {
                            new RxTimer().timer(300, number -> iPaperViewInterface.onSuccess(isEdit));
                        } else {
                            new RxTimer().timer(300, number -> iPaperViewInterface.onFailed(data.getMsg()));
                        }
                    }, e -> {
                        Logger.e(e.getMessage());
                        new RxTimer().timer(300, number -> iPaperViewInterface.onFailed(e.getMessage()));
                    });
        } else {
            this.noNetWork();
        }
    }

    @Override
    public void noNetWork() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestory() {

    }
}
