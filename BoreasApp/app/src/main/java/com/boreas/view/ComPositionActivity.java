package com.boreas.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

import jxl.Sheet;
import jxl.Workbook;

import static com.boreas.view.ProActivity.getFilePathFromContentUri;

public class ComPositionActivity extends BaseActivity<ActivityComPositionBinding> implements IComViewInterface {
    private ActivityComPositionBinding binding;
    private ComPresenter presenter;//ComPresenter   这不是有吗？
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
        this.binding.openFile.setOnClickListener(new ClickProxy(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");//无类型限制
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, 1);
        }));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            if (uri.getScheme().equalsIgnoreCase("content")) {
                this.readExcel(getFilePathFromContentUri(uri, getContentResolver()));
            } else {
                Toast.makeText(this, "Scheme not mather", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void readExcel(String uri) {
        this.showLoadingDialog();
        ArrayList<LoginReceBean.DataBean.Composition> datas = null;
        try {
            File file = new File(uri);
            Log.e("yy", "file=" + file.getAbsolutePath());
            InputStream is = new FileInputStream(file);
            Workbook book = Workbook.getWorkbook(is);
            book.getNumberOfSheets();
            Sheet sheet = book.getSheet(0);
            int Rows = sheet.getRows();
            int columns = sheet.getColumns();
            if (Rows <= 2) {
                Toast.makeText(this, "该文件里边数据是空", Toast.LENGTH_SHORT).show();
                return;
            }
            datas = new ArrayList<>();
            for (int i = 2; i < Rows; ++i) { //获取行数 ，
                LoginReceBean.DataBean.Composition composition = new LoginReceBean.DataBean.Composition();
                String  book_name = sheet.getCell(1, i).getContents();
                String book_auther = sheet.getCell(2, i).getContents();
                String book_other_auther = sheet.getCell(3, i).getContents();
                String book_periodical_code = sheet.getCell(4, i).getContents();
                String book_press = sheet.getCell(5, i).getContents();
                String book_press_date = sheet.getCell(6, i).getContents();
                String book_char_num = sheet.getCell(7, i).getContents();
                String book_money = sheet.getCell(8, i).getContents();
                String book_bear_palm = sheet.getCell(9, i).getContents();
                if (!TextUtils.isEmpty( book_name)) {
                    composition.setBook_name( book_name);
                }
                if (!TextUtils.isEmpty(book_auther)) {
                    composition.setBook_auther(book_auther);
                }
                if (!TextUtils.isEmpty(book_other_auther)) {
                    composition.setBook_other_auther(book_other_auther);
                }
                if (!TextUtils.isEmpty( book_periodical_code)) {
                    composition.setBook_periodical_code( book_periodical_code);
                }
                if (!TextUtils.isEmpty( book_press)) {
                    composition.setBook_press( book_press);
                }
                if (!TextUtils.isEmpty(book_press_date)) {
                    composition.setBook_press_date(book_press_date);
                }
                if (!TextUtils.isEmpty(book_char_num)) {
                    composition.setBook_char_num(book_char_num);
                }
                if (!TextUtils.isEmpty(book_money)) {
                    composition.setBook_money(book_money);
                }
                if (!TextUtils.isEmpty(book_bear_palm)) {
                    composition.setBook_bear_palm(book_bear_palm);
                }


                if(composition.getBook_name() != null &&
                        composition.getBook_auther()!= null &&
                        composition.getBook_bear_palm()!=null &&
                        composition.getBook_char_num()!=null&&
                        composition.getBook_money()!=null&&
                         composition.getBook_other_auther()!=null&&
                         composition.getBook_periodical_code()!=null&&
                         composition.getBook_press()!=null&&
                         composition.getBook_press_date()!=null){
                    datas.add(composition);
                }
            }
            book.close();
        } catch (Exception e) {
            Log.e("yy", "e" + e);
        }finally {
            if (datas != null) {
                //上传服务器
                presenter.saves(datas);
            }
        }
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
    public void onAddCompositionsSuccess() {
        this.dimissLoadingDialog();
        Toast.makeText(this, "导入成功!", Toast.LENGTH_SHORT).show();
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
