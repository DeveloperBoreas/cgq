package com.boreas.view;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;

import com.boreas.R;
import com.boreas.base.BaseActivity;
import com.boreas.databinding.ActivityClipueBinding;
import com.boreas.framework.ClickProxy;
import com.boreas.persenter.PersenterImpl.ClipuePresenter;
import com.boreas.view.IViewInterface.IClipueViewInterface;

public class ClipueActivity extends BaseActivity<ActivityClipueBinding> implements IClipueViewInterface {

    private ActivityClipueBinding binding;
    private ClipuePresenter presenter;

    @Override
    public void initPersenter() {
        presenter = new ClipuePresenter(this);
    }

    @Override
    public void initViewData() {

    }

    @Override
    public int getView() {
        return R.layout.activity_clipue;
    }

    @Override
    public void initView(ActivityClipueBinding activityClipueBinding) {
        this.binding = activityClipueBinding;
        this.binding.save.setOnClickListener(new ClickProxy(view -> {
            String clipueName = this.binding.alias.getText().toString().trim();
            if (TextUtils.isEmpty(clipueName)) {
                Toast.makeText(this, "请输入系别名称：", Toast.LENGTH_SHORT).show();
                return;
            }
            this.showLoadingDialog();
            presenter.saveClipue(clipueName);

        }));
    }

    @Override
    public void onSuccess() {
        this.dimissLoadingDialog();
        Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        this.binding.alias.setText("");
    }

    @Override
    public void onFailed(String msg) {
        this.dimissLoadingDialog();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoDataView() {
        this.dimissLoadingDialog();
    }

    @Override
    public void showNoNetWork() {
        this.dimissLoadingDialog();
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_OK);
        finish();
        super.onBackPressed();
    }
}
