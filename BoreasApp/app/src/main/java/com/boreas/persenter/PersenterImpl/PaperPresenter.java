package com.boreas.persenter.PersenterImpl;

import com.boreas.framework.RxTimer;
import com.boreas.modle.LoginReceBean;
import com.boreas.persenter.IPersenters.IPaperPresenter;
import com.boreas.view.IViewInterface.IPaperViewInterface;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PaperPresenter extends IPaperPresenter {
    private IPaperViewInterface iPaperViewInterface;

    public PaperPresenter(IPaperViewInterface iPaperViewInterface) {
        super();
        this.iPaperViewInterface = iPaperViewInterface;
    }


    @Override
    public void saves(ArrayList<LoginReceBean.DataBean.ResearchPaper> papers) {
        if (isNetWorkEnable()) {
            apiService.insertPapers(papers)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {
                        if (data.getRetCode() == 0) {
                            new RxTimer().timer(300, number -> iPaperViewInterface.onAddPapersSuccess());
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
    public void save(LoginReceBean.DataBean.ResearchPaper paper) {
        if (isNetWorkEnable()) {
            apiService.insertPaper(paper)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {
                        if (data.getRetCode() == 0) {
                            new RxTimer().timer(300, number -> iPaperViewInterface.onSuccess());
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
    public void update(LoginReceBean.DataBean.ResearchPaper paper) {
        if (isNetWorkEnable()) {
            apiService.updatePaper(paper)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data -> {
                        if (data.getRetCode() == 0) {
                            new RxTimer().timer(300, number -> iPaperViewInterface.onUpdateSuccess());
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
        iPaperViewInterface.showNoNetWork();
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
