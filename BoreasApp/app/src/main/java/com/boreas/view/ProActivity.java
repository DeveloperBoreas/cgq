package com.boreas.view;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.boreas.R;
import com.boreas.base.BaseActivity;
import com.boreas.databinding.ActivityProBinding;
import com.boreas.framework.ClickProxy;
import com.boreas.modle.LoginReceBean;
import com.boreas.persenter.PersenterImpl.ProPresenter;
import com.boreas.utils.TimeUtil;
import com.boreas.view.IViewInterface.IProViewInterface;

import java.util.Calendar;

public class ProActivity extends BaseActivity<ActivityProBinding> implements IProViewInterface {
    private ActivityProBinding binding;
    private ProPresenter proPresenter;
    private boolean isEdit;
    private int tempProId = -1;

    @Override
    public void initPersenter() {
        proPresenter = new ProPresenter(this);
    }

    @Override
    public void initViewData() {

    }

    @Override
    public int getView() {
        return R.layout.activity_pro;
    }

    @Override
    public void initView(ActivityProBinding activityProBinding) {
        this.binding = activityProBinding;
        LoginReceBean.DataBean.ResearchPro tempPro = (LoginReceBean.DataBean.ResearchPro) getIntent().getSerializableExtra("pro");
        isEdit = getIntent().getBooleanExtra("isEdit", false);
        if (tempPro != null) {
            tempProId = tempPro.getId();
            this.setEditData(this.binding.alias, tempPro.getPro_name());
            this.setEditData(this.binding.level, tempPro.getPro_level() + "");
            this.setEditData(this.binding.exp, tempPro.getPro_money() + "万");
            this.setEditData(this.binding.status, tempPro.getPro_current_status() + "%");
            this.setTextData(this.binding.finishDateText, this.binding.finishDate, tempPro.getPro_finish_date());
            this.setEditData(this.binding.bearPalm, tempPro.getPro_bear_palm() == null ? "" : tempPro.getPro_bear_palm());
            if (!isEdit) {
                this.binding.save.setEnabled(false);
                this.binding.save.setVisibility(View.GONE);
            }
        }
        this.binding.finishDate.setOnClickListener(new ClickProxy(view -> {
            this.showDateSwitcher();
        }));
        this.binding.save.setOnClickListener(new ClickProxy(v -> {
            if (this.verParams()) {
                LoginReceBean.DataBean.ResearchPro pro = new LoginReceBean.DataBean.ResearchPro();
                pro.setPro_bear_palm(this.binding.bearPalm.getText().toString().trim());
                pro.setPro_current_status(this.binding.status.getText().toString().trim());
                pro.setPro_finish_date(this.binding.finishDateText.getText().toString().trim());
                pro.setPro_money(this.binding.exp.getText().toString().trim());
                pro.setPro_level(Integer.parseInt(this.binding.level.getText().toString().trim()));
                pro.setPro_name(this.binding.alias.getText().toString().trim());
                this.showLoadingDialog();
                if (isEdit) {
                    pro.setId(tempProId == -1 ? -1 : tempProId);
                    proPresenter.update(pro);
                } else {
                    proPresenter.save(pro);
                }
            }
        }));
    }

    private void setEditData(EditText view, String data) {
        view.setText(data + "");
        if (!isEdit) {
            view.setClickable(false);
            view.setFocusable(false);
        }
    }

    private void setTextData(TextView view, RelativeLayout viewParent, String data) {
        view.setText(data + "");
        if (!isEdit) {
            viewParent.setVisibility(View.GONE);
        }
    }

    private boolean verParams() {
        if (TextUtils.isEmpty(this.binding.alias.getText().toString().trim())) {
            Toast.makeText(this, "项目名称不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(this.binding.level.getText().toString().trim())) {
            Toast.makeText(this, "项目级别不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(this.binding.exp.getText().toString().trim())) {
            Toast.makeText(this, "项目经费不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(this.binding.status.getText().toString().trim())) {
            Toast.makeText(this, "项目完成状态不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(this.binding.finishDateText.getText().toString().trim())) {
            Toast.makeText(this, "项目完成日期不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void showDateSwitcher() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2020, 1, 1);
        TimePickerView pvTime = new TimePickerBuilder(this, (date, v) -> binding.finishDateText.setText(TimeUtil.getTime(date)))
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setType(new boolean[]{true, true, true, false, false, false})
                .isDialog(false) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .setItemVisibleCount(4) //若设置偶数，实际值会加1（比如设置6，则最大可见条目为7）
                .setLineSpacingMultiplier(2.0f)
                .isAlphaGradient(true)
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                dialogWindow.setDimAmount(0.3f);
            }
        }
        pvTime.show();
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
    public void onSuccess() {
        this.dimissLoadingDialog();
        Toast.makeText(this, "添加项目成功!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdateSuccess() {
        this.dimissLoadingDialog();
        Toast.makeText(this, "更新项目成功!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed(String msg) {
        this.dimissLoadingDialog();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_OK);
        finish();
        super.onBackPressed();
    }
}
