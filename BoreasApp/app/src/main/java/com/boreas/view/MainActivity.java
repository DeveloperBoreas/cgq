package com.boreas.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.boreas.R;
import com.boreas.adapter.UserListAdapter;
import com.boreas.base.BaseActivity;
import com.boreas.databinding.ActivityMainBinding;
import com.boreas.framework.ClickProxy;
import com.boreas.modle.LoginReceBean;
import com.boreas.persenter.PersenterImpl.MainPersenterImpl;
import com.boreas.view.IViewInterface.IMainViewInterface;

import java.util.ArrayList;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements IMainViewInterface<ArrayList<LoginReceBean.DataBean>> {

    private ActivityMainBinding binding;
    private MainPersenterImpl persenter;
    private UserListAdapter adapter;
    private static final int REQUEST = 1111;

    @Override
    public void initPersenter() {
        persenter = new MainPersenterImpl(this);
    }

    @Override
    public void initViewData() {
        this.showLoadingDialog();
        persenter.requestUserInfoList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_edit_user, menu);
        return true;
    }

    private ProgressDialog progressDialog;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_export) {
            progressDialog = showProgressLoadingDialog();
            progressDialog.show();
            persenter.exportFile(true);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onUpdateProgressCurrent(int num) {
        Log.e("aaa","-----"+num);
        progressDialog.setProgress(num);
    }

    @Override
    public int getView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(ActivityMainBinding binding) {
        this.binding = binding;
        this.binding.userListView.setLayoutManager(new LinearLayoutManager(this));
        this.binding.userListView.setItemAnimator(new DefaultItemAnimator());
        adapter = new UserListAdapter(this, new ArrayList(), R.layout.userlist_item);
        adapter.setOnItemClickListener((parent, view, position) -> {
            LoginReceBean.DataBean bean = this.adapter.getListData().get(position);
            if (bean.getUser_alias().equals("admin")) {
                return;
            }
            Intent intent = new Intent(this, EditUserActivity.class);
            intent.putExtra("userInfo", bean);
            intent.putExtra("isEdit", true);
            this.startActivity(intent);
        });
        this.adapter.setOnItemLongClickListener((parent, view, position) -> {
            LoginReceBean.DataBean bean = this.adapter.getListData().get(position);
            if (bean.getUser_alias().equals("admin")) {
                return true;
            }
            new AlertDialog.Builder(this)
                    .setTitle("确定删除")
                    .setPositiveButton("确定", (dialogInterface, i) -> {
                        showLoadingDialog();
                        persenter.deleteUserInfo(adapter.getListData().get(position).getUser_id(), position);
                    })
                    .setNegativeButton("取消", null)
                    .create()
                    .show();
            return false;
        });
        this.binding.userListView.setAdapter(adapter);
        this.binding.add.setOnClickListener(new ClickProxy(view -> {
            Intent intent = new Intent(this, EditUserActivity.class);
            startActivityForResult(intent, REQUEST);
        }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST && resultCode == Activity.RESULT_OK) {
            showLoadingDialog();
            persenter.requestUserInfoList();
        }
    }

    @Override
    public void showNoDataView() {

    }

    @Override
    public void showNoNetWork() {

    }

    @Override
    public void onSuccess(boolean isSuccess, ArrayList<LoginReceBean.DataBean> data) {
        this.dimissLoadingDialog();
        if (data != null && data.size() > 0) {
            adapter.reset(data);
        }
    }

    @Override
    public void onFailed(String msg) {
        this.dimissLoadingDialog();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteSuccess(String msg, int index) {
        this.dimissLoadingDialog();
        this.adapter.delete(index);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onExportFileSuccess(String name) {
        this.dimissLoadingDialog();
        Toast.makeText(this, "导出文件名称：" + name, Toast.LENGTH_SHORT).show();
    }
}
