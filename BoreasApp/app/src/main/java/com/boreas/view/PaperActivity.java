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
import com.boreas.databinding.ActivityPaperBinding;
import com.boreas.framework.ClickProxy;
import com.boreas.modle.LoginReceBean;
import com.boreas.persenter.PersenterImpl.ComPresenter;
import com.boreas.persenter.PersenterImpl.PaperPresenter;
import com.boreas.utils.TimeUtil;
import com.boreas.view.IViewInterface.IPaperViewInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

import jxl.Sheet;
import jxl.Workbook;

import static com.boreas.view.ProActivity.getFilePathFromContentUri;

public class PaperActivity extends BaseActivity<ActivityPaperBinding> implements IPaperViewInterface {

    private ActivityPaperBinding binding;
    private PaperPresenter paperPresenter;
    private boolean isEdit;
    private int tempPaperId = -1;

    @Override
    public void initPersenter() {
        this.paperPresenter = new PaperPresenter(this);
    }

    @Override
    public void initViewData() {

    }

    @Override
    public int getView() {
        return R.layout.activity_paper;
    }

    @Override
    public void initView(ActivityPaperBinding activityPaperBinding) {
        this.binding = activityPaperBinding;
        LoginReceBean.DataBean.ResearchPaper tempPaper = (LoginReceBean.DataBean.ResearchPaper) getIntent().getSerializableExtra("paper");
        isEdit = getIntent().getBooleanExtra("isEdit", false);
        if (tempPaper != null) {
            this.tempPaperId = tempPaper.getId();
            this.setEditData(this.binding.alias, tempPaper.getPaper_name());
            this.setEditData(this.binding.author, tempPaper.getPaper_author());
            this.setEditData(this.binding.otherAuthor, tempPaper.getPaper_otherauthor() == null ? "" : tempPaper.getPaper_otherauthor());
            this.setEditData(this.binding.coding, tempPaper.getPaper_periodical_code());
            this.setTextData(this.binding.publishDateText, this.binding.publishDate, tempPaper.getPaper_publish_date());
            this.setEditData(this.binding.bearPalm, tempPaper.getPaper_bear_palm() == null ? "" : tempPaper.getPaper_bear_palm());
            if (!isEdit) {
                this.binding.save.setEnabled(false);
                this.binding.save.setVisibility(View.GONE);
            }
        }
        this.binding.publishDate.setOnClickListener(new ClickProxy(view -> {
            this.showDateSwitcher();
        }));
        this.binding.save.setOnClickListener(new ClickProxy(view -> {
            if (this.verParams()) {
                LoginReceBean.DataBean.ResearchPaper paper = new LoginReceBean.DataBean.ResearchPaper();
                paper.setPaper_author(this.binding.author.getText().toString().trim());
                paper.setPaper_otherauthor(this.binding.otherAuthor.getText().toString().trim());
                paper.setPaper_name(this.binding.alias.getText().toString().trim());
                paper.setPaper_periodical_code(this.binding.coding.getText().toString().trim());
                paper.setPaper_publish_date(this.binding.publishDateText.getText().toString().trim());
                paper.setPaper_bear_palm(this.binding.bearPalm.getText().toString().trim());
                this.showLoadingDialog();
                if (isEdit) {
                    paper.setId(tempPaperId);
                    this.paperPresenter.update(paper);
                } else {
                    this.paperPresenter.save(paper);
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
        ArrayList<LoginReceBean.DataBean.ResearchPaper> datas = null;
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
                LoginReceBean.DataBean.ResearchPaper paper = new LoginReceBean.DataBean.ResearchPaper();
                String paper_name = sheet.getCell(1, i).getContents();
                String paper_author = sheet.getCell(2, i).getContents();
                String paper_otherauthor = sheet.getCell(3, i).getContents();
                String paper_periodical_code = sheet.getCell(4, i).getContents();
                String paper_category = sheet.getCell(5, i).getContents();
                String paper_publish_date = sheet.getCell(6, i).getContents();
                String paper_bear_palm = sheet.getCell(7, i).getContents();
                if (!TextUtils.isEmpty(paper_name)) {

                    paper.setPaper_name(paper_name);
                }
                if (!TextUtils.isEmpty(paper_author)) {
                    paper.setPaper_author(paper_author);
                }
                if (!TextUtils.isEmpty(paper_otherauthor)) {
                    paper.setPaper_otherauthor(paper_otherauthor);
                }
                if (!TextUtils.isEmpty(paper_periodical_code)) {
                    paper.setPaper_periodical_code(paper_periodical_code);
                }
                if (!TextUtils.isEmpty(paper_category)) {
                    paper.setPaper_category(paper_category);
                }
                if (!TextUtils.isEmpty(paper_publish_date)) {
                    paper.setPaper_publish_date(paper_publish_date);
                }
                if (!TextUtils.isEmpty(paper_bear_palm)) {
                    paper.setPaper_bear_palm(paper_bear_palm);
                }
                if(paper.getPaper_author() != null &&
                        paper.getPaper_bear_palm()!= null &&
                        paper.getPaper_category()!=null &&
                        paper.getPaper_name()!=null&&
                        paper.getPaper_otherauthor()!=null&&
                        paper.getPaper_periodical_code()!=null&&
                        paper.getPaper_publish_date()!=null
                        ){
                    datas.add(paper);
                }

            }
            book.close();
        } catch (Exception e) {
            Log.e("yy", "e" + e);
        }finally {
            if (datas != null) {
                //上传服务器
                paperPresenter.saves(datas);
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
            Toast.makeText(this, "论文名称不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(this.binding.author.getText().toString().trim())) {
            Toast.makeText(this, "作者不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(this.binding.coding.getText().toString().trim())) {
            Toast.makeText(this, "刊物编码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(this.binding.publishDateText.getText().toString().trim())) {
            Toast.makeText(this, "发布日期不能为空", Toast.LENGTH_SHORT).show();
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
        TimePickerView pvTime = new TimePickerBuilder(this, (date, v) -> binding.publishDateText.setText(TimeUtil.getTime(date)))
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
        Toast.makeText(this, "添加论文成功!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdateSuccess() {
        this.dimissLoadingDialog();
        Toast.makeText(this, "更新论文成功!", Toast.LENGTH_SHORT).show();
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
