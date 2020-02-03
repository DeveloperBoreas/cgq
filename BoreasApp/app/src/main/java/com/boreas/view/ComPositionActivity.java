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
import com.boreas.databinding.ActivityComPositionBinding;
import com.boreas.framework.ClickProxy;
import com.boreas.modle.LoginReceBean;
import com.boreas.persenter.PersenterImpl.ComPresenter;
import com.boreas.utils.TimeUtil;
import com.boreas.view.IViewInterface.IComViewInterface;

import java.util.Calendar;

public class ComPositionActivity extends BaseActivity<ActivityComPositionBinding> implements IComViewInterface {
    private ActivityComPositionBinding binding;
    private ComPresenter presenter;
    private boolean isEdit;
    private int tempComId = -1;

    @Override
    public void initPersenter() {
        presenter = new ComPresenter(this);
    }

    @Override
    public void initViewData() {

    }

    @Override
    public int getView() {
        return R.layout.activity_com_position;
    }

    @Override
    public void initView(ActivityComPositionBinding viewDataBinding) {
        this.binding = viewDataBinding;
        LoginReceBean.DataBean.Composition com = (LoginReceBean.DataBean.Composition) getIntent().getSerializableExtra("com");
        isEdit = getIntent().getBooleanExtra("isEdit", false);
        if (com != null) {
            this.tempComId = com.getId();
            this.setEditData(this.binding.alias, com.getBook_name());
            this.setEditData(this.binding.author, com.getBook_auther());
            this.setEditData(this.binding.otherAuthor, com.getBook_other_auther() == null ? "" : com.getBook_other_auther());
            this.setEditData(this.binding.coding, com.getBook_periodical_code());
            this.setEditData(this.binding.press, com.getBook_press());
            this.setEditData(this.binding.numberWords, com.getBook_char_num());
            this.setEditData(this.binding.price, com.getBook_money());
            this.setTextData(this.binding.pressDateText, this.binding.pressDate, com.getBook_press_date());
            this.setEditData(this.binding.bearPalm, com.getBook_bear_palm() == null ? "" : com.getBook_bear_palm());
            if (!isEdit) {
                this.binding.save.setEnabled(false);
                this.binding.save.setVisibility(View.GONE);
            }
        }
        this.binding.pressDate.setOnClickListener(new ClickProxy(view -> {
            this.showDateSwitcher();
        }));
        this.binding.save.setOnClickListener(new ClickProxy(view -> {
            if (this.verParams()) {
                LoginReceBean.DataBean.Composition composition = new LoginReceBean.DataBean.Composition();
                composition.setId(tempComId == -1 ? -1 : tempComId);
                composition.setBook_name(this.binding.alias.getText().toString().trim());
                composition.setBook_auther(this.binding.author.getText().toString().trim());
                composition.setBook_other_auther(this.binding.otherAuthor.getText().toString().trim());
                composition.setBook_periodical_code(this.binding.coding.getText().toString().trim());
                composition.setBook_char_num(this.binding.numberWords.getText().toString().trim());
                composition.setBook_money(this.binding.price.getText().toString().trim());
                composition.setBook_press(this.binding.press.getText().toString().trim());
                composition.setBook_press_date(this.binding.pressDateText.getText().toString().trim());
                composition.setBook_bear_palm(this.binding.bearPalm.getText().toString().trim());
                this.showLoadingDialog();
                if (isEdit) {
                    composition.setId(tempComId);
                    this.presenter.update(composition);
                } else {
                    this.presenter.save(composition);
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
            Toast.makeText(this, "书籍名称不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(this.binding.author.getText().toString().trim())) {
            Toast.makeText(this, "第一作者不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(this.binding.coding.getText().toString().trim())) {
            Toast.makeText(this, "书籍不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(this.binding.press.getText().toString().trim())) {
            Toast.makeText(this, "出版社不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(this.binding.numberWords.getText().toString().trim())) {
            Toast.makeText(this, "字数不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(this.binding.price.getText().toString().trim())) {
            Toast.makeText(this, "单价不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(this.binding.pressDateText.getText().toString().trim())) {
            Toast.makeText(this, "出版日期不能为空", Toast.LENGTH_SHORT).show();
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
        TimePickerView pvTime = new TimePickerBuilder(this, (date, v) -> binding.pressDateText.setText(TimeUtil.getTime(date)))
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
    public void onSuccess() {
        this.dimissLoadingDialog();
        Toast.makeText(this, "添加书籍成功!", Toast.LENGTH_SHORT).show();
    }

    public void onUpdateSuccess() {
        this.dimissLoadingDialog();
        Toast.makeText(this, "更新书籍成功!", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_OK);
        finish();
        super.onBackPressed();
    }
}
