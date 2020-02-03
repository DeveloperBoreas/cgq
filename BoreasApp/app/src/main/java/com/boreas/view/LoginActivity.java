package com.boreas.view;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.boreas.App;
import com.boreas.R;
import com.boreas.base.BaseActivity;
import com.boreas.databinding.ActivityLoginBinding;
import com.boreas.framework.ClickProxy;
import com.boreas.modle.LoginReceBean;
import com.boreas.persenter.PersenterImpl.LoginPersenterImpl;
import com.boreas.view.IViewInterface.ILoginViewInterface;
import com.bumptech.glide.Glide;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements ILoginViewInterface<LoginReceBean> {

    private ActivityLoginBinding binding;
    private LoginPersenterImpl persenter;

    @Override
    public void initPersenter() {
        persenter = new LoginPersenterImpl(this);
    }

    @Override
    public void initViewData() {

    }

    @Override
    public int getView() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(ActivityLoginBinding activityLoginBinding) {
        this.binding = activityLoginBinding;
//        Glide.with(this).load("http://172.20.10.12:8080/images/a.jpg").into(this.binding.logo);
        this.binding.login.setOnClickListener(new ClickProxy(view -> {
            if (this.verLoginParams()) {
                this.showLoadingDialog();
                this.persenter.login(binding.userNameInput.getText().toString().trim(), binding.userPsdInput.getText().toString().trim());
            }
        }));
        this.binding.forgetPsd.setOnClickListener(new ClickProxy(view -> {
            Intent intent = new Intent(this, ForGetActivity.class);
            startActivity(intent);
        }));
    }

    private boolean verLoginParams() {
        String userName = binding.userNameInput.getText().toString().trim();
        String userPsd = binding.userPsdInput.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(userPsd)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onSuccess(boolean isSuccess, LoginReceBean data) {
        this.dimissLoadingDialog();
        if (data.getRetCode() == 0) {
            App.getInstance().setName(binding.userNameInput.getText().toString().trim());
            App.getInstance().setPsd(binding.userPsdInput.getText().toString().trim());
            if (data.getData().getUser_permission() == 1) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, EditUserActivity.class);
                intent.putExtra("userInfo", data.getData());
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, data.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailed(String msg) {
        this.dimissLoadingDialog();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showNoDataView() {

    }

    @Override
    public void showNoNetWork() {

    }

}
