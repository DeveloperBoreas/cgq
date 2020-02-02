package com.boreas.view;

import android.app.AlertDialog;
import android.text.TextUtils;
import android.widget.Toast;

import com.boreas.R;
import com.boreas.base.BaseActivity;
import com.boreas.databinding.ActivityForGetBinding;
import com.boreas.framework.ClickProxy;
import com.boreas.persenter.PersenterImpl.ForgetPresenter;
import com.boreas.view.IViewInterface.IForgetViewInterface;

public class ForGetActivity extends BaseActivity<ActivityForGetBinding> implements IForgetViewInterface {

    private ActivityForGetBinding binding;
    private ForgetPresenter presenter;

    @Override
    public void initPersenter() {
        this.presenter = new ForgetPresenter(this);
    }

    @Override
    public void initViewData() {

    }

    @Override
    public int getView() {
        return R.layout.activity_for_get;
    }

    @Override
    public void initView(ActivityForGetBinding activityForGetBinding) {
        this.binding = activityForGetBinding;
        this.binding.forgetSure.setOnClickListener(new ClickProxy(view -> {
            if (this.verParams()) {
                this.showLoadingDialog();
                this.presenter.save(this.binding.forgetPsdInput.getText().toString().trim());
            }
        }));
    }

    private boolean verParams() {
        String phone = this.binding.forgetPsdInput.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onSuccess(String msg) {
        this.dimissLoadingDialog();
        // TODO: 2020/1/29 弹框展示当前账号密码
        try {
            String[] split = msg.split(",");
            StringBuilder sb = new StringBuilder();
            sb.append("账号：")
                    .append(split[0])
                    .append(";")
                    .append(split[1])
                    .append(";");
            new AlertDialog.Builder(this)
                    .setMessage(sb.toString())
                    .create()
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailed(String msg) {
        this.dimissLoadingDialog();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoDataView() {
        this.dimissLoadingDialog();
        Toast.makeText(this, "无数据", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoNetWork() {
        this.dimissLoadingDialog();
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
    }
}
