package com.boreas.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.boreas.R;
import com.boreas.base.BaseActivity;
import com.boreas.databinding.ActivityEditUserBinding;
import com.boreas.framework.ClickProxy;
import com.boreas.framework.RxTimer;
import com.boreas.modle.ClipuesBean;
import com.boreas.modle.ComBean;
import com.boreas.modle.LoginReceBean;
import com.boreas.modle.PaperBean;
import com.boreas.modle.ProBean;
import com.boreas.modle.UserInfo;
import com.boreas.persenter.PersenterImpl.EditPersenter;
import com.boreas.utils.TimeUtil;
import com.boreas.view.IViewInterface.IEditViewInterface;

import java.util.ArrayList;
import java.util.Calendar;

public class EditUserActivity extends BaseActivity<ActivityEditUserBinding> implements IEditViewInterface {

    private ActivityEditUserBinding binding;
    private LoginReceBean loginReceBean;
    private ArrayList<LoginReceBean.DataBean.Clipue> clipues = new ArrayList<>();
    private ArrayList<LoginReceBean.DataBean.ResearchPro> pros = new ArrayList<>();
    private ArrayList<LoginReceBean.DataBean.ResearchPaper> papers = new ArrayList<>();
    private ArrayList<LoginReceBean.DataBean.Composition> compositions = new ArrayList<>();
    private int clipueChoice = -1;
    private int proChoice = -1;
    private int paperChoice = -1;
    private int comChoice = -1;
    private EditPersenter persenter;
    private LoginReceBean.DataBean.Clipue tempClipue;
    private LoginReceBean.DataBean.ResearchPro tempPro;
    private LoginReceBean.DataBean.ResearchPaper tempPaper;
    private LoginReceBean.DataBean.Composition tempCom;
    private static final int CLIPUE_REQUEST = 0001;
    private static final int PRO_REQUEST = 1001;
    private static final int PAPER_REQUEST = 2002;
    private static final int COM_REQUEST = 3003;
    private String sex = "男";
    private boolean isEdit;
    private boolean isEdit1;

    @Override
    public void initPersenter() {
        persenter = new EditPersenter(this);
    }

    @Override
    public void initViewData() {
        showLoadingDialog();
        persenter.queryPageAllMsg();
    }

    @Override
    public int getView() {
        return R.layout.activity_edit_user;
    }

    @Override
    public void initView(ActivityEditUserBinding activityEditUserBinding) {
        this.binding = activityEditUserBinding;
        this.binding.sex.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == 0) {
                sex = "男";
            } else {
                sex = "女";
            }
        });
        this.binding.birthday.setOnClickListener(new ClickProxy(view -> {
            showDateSwitcher();
        }));
        this.binding.clipue.setOnClickListener(new ClickProxy(view -> {
            if (this.clipues == null || this.clipues.size() == 0) {
                new AlertDialog.Builder(this)
                        .setMessage("没有录入系别，请先录入")
                        .setPositiveButton("确定", (dialog, which) -> {
                            Intent intent = new Intent(this, ClipueActivity.class);
                            this.startActivityForResult(intent, CLIPUE_REQUEST);
                        }).setNegativeButton("取消", null)
                        .create()
                        .show();
                return;
            }
            String[] items = new String[this.clipues.size()];
            for (int i = 0; i < this.clipues.size(); i++) {
                items[i] = this.clipues.get(i).getClipue_name();
            }
            clipueChoice = 0;
            AlertDialog.Builder builder = new AlertDialog.Builder(this).setIcon(R.drawable.logo).setTitle("系别")
                    .setSingleChoiceItems(items, 0, (dialogInterface, i) -> clipueChoice = i)
                    .setPositiveButton("确定", (dialogInterface, i) -> {
                        if (clipueChoice != -1) {
//                            Toast.makeText(EditUserActivity.this, "你选择了" + items[clipueChoice], Toast.LENGTH_LONG).show();
                            String clipueText = items[clipueChoice];
                            for (LoginReceBean.DataBean.Clipue clipue : this.clipues) {
                                if (clipue.getClipue_name().equals(clipueText)) {
                                    tempClipue = clipue;
                                    break;
                                }
                            }
                            this.binding.clipueText.setText(tempClipue.getClipue_name());
                        }
                    }).setNegativeButton("添加系别", ((dialog, which) -> {
                        Intent intent = new Intent(this, ClipueActivity.class);
                        this.startActivityForResult(intent, CLIPUE_REQUEST);
                    }));
            builder.create().show();
        }));
        this.binding.pro.setOnClickListener(new ClickProxy(view -> {
            if (this.pros == null || this.pros.size() == 0) {
                new AlertDialog.Builder(this)
                        .setMessage("没有录入项目信息，请先录入")
                        .setPositiveButton("确定", (dialog, which) -> {
                            Intent intent = new Intent(this, ProActivity.class);
                            this.startActivityForResult(intent, PRO_REQUEST);
                        }).setNegativeButton("取消", null)
                        .create()
                        .show();
                return;
            }
            String[] items = new String[this.pros.size()];
            for (int i = 0; i < this.pros.size(); i++) {
                items[i] = this.pros.get(i).getPro_name();
            }
            proChoice = 0;
            AlertDialog.Builder builder = new AlertDialog.Builder(this).setIcon(R.drawable.logo).setTitle("项目信息")
                    .setSingleChoiceItems(items, 0, (dialogInterface, i) -> proChoice = i)
                    .setPositiveButton("确定", (dialogInterface, i) -> {
                        if (proChoice != -1) {
                            String clipueText = items[proChoice];
                            for (LoginReceBean.DataBean.ResearchPro pro : this.pros) {
                                if (pro.getPro_name().equals(clipueText)) {
                                    tempPro = pro;
                                    break;
                                }
                            }
                            this.binding.proText.setText(tempPro.getPro_name());
                        }
                    }).setNegativeButton("添加项目", ((dialog, which) -> {
                        Intent intent = new Intent(this, ProActivity.class);
                        this.startActivityForResult(intent, PRO_REQUEST);
                    }));
            builder.create().show();
        }));
        this.binding.paper.setOnClickListener(new ClickProxy(view -> {
            if (this.papers == null || this.papers.size() == 0) {
                new AlertDialog.Builder(this)
                        .setMessage("没有录入论文信息，请先录入")
                        .setPositiveButton("确定", (dialog, which) -> {
                            Intent intent = new Intent(this, PaperActivity.class);
                            this.startActivityForResult(intent, PAPER_REQUEST);
                        }).setNegativeButton("取消", null)
                        .create()
                        .show();
                return;
            }
            String[] items = new String[this.papers.size()];
            for (int i = 0; i < this.papers.size(); i++) {
                items[i] = this.papers.get(i).getPaper_name();
            }
            paperChoice = 0;
            AlertDialog.Builder builder = new AlertDialog.Builder(this).setIcon(R.drawable.logo).setTitle("论文信息")
                    .setSingleChoiceItems(items, 0, (dialogInterface, i) -> paperChoice = i)
                    .setPositiveButton("确定", (dialogInterface, i) -> {
                        if (paperChoice != -1) {
                            String paperText = items[paperChoice];
                            for (LoginReceBean.DataBean.ResearchPaper paper : this.papers) {
                                if (paper.getPaper_name().equals(paperText)) {
                                    tempPaper = paper;
                                    break;
                                }
                            }
                            this.binding.paperText.setText(tempPaper.getPaper_name());
                        }
                    }).setNegativeButton("添加论文", ((dialog, which) -> {
                        Intent intent = new Intent(this, PaperActivity.class);
                        this.startActivityForResult(intent, PAPER_REQUEST);
                    }));
            builder.create().show();
        }));
        this.binding.comPosition.setOnClickListener(new ClickProxy(view -> {
            if (this.compositions == null || this.compositions.size() == 0) {
                new AlertDialog.Builder(this)
                        .setMessage("没有录入学术著作信息，请先录入")
                        .setPositiveButton("确定", (dialog, which) -> {
                            Intent intent = new Intent(this, ComPositionActivity.class);
                            this.startActivityForResult(intent, COM_REQUEST);
                        }).setNegativeButton("取消", null)
                        .create()
                        .show();
                return;
            }
            String[] items = new String[this.compositions.size()];
            for (int i = 0; i < this.compositions.size(); i++) {
                items[i] = this.compositions.get(i).getBook_name();
            }
            comChoice = 0;
            AlertDialog.Builder builder = new AlertDialog.Builder(this).setIcon(R.drawable.logo).setTitle("项目信息")
                    .setSingleChoiceItems(items, 0, (dialogInterface, i) -> comChoice = i)
                    .setPositiveButton("确定", (dialogInterface, i) -> {
                        if (comChoice != -1) {
                            String comText = items[comChoice];
                            for (LoginReceBean.DataBean.Composition composition : this.compositions) {
                                if (composition.getBook_name().equals(comText)) {
                                    tempCom = composition;
                                    break;
                                }
                            }
                            this.binding.comPositionText.setText(tempCom.getBook_name());
                        }
                    }).setNegativeButton("添加书籍", ((dialog, which) -> {
                        Intent intent = new Intent(this, ComPositionActivity.class);
                        this.startActivityForResult(intent, COM_REQUEST);
                    }));
            builder.create().show();
        }));
        this.binding.save.setOnClickListener(new ClickProxy(view -> {
            if (this.verParams()) {
                showLoadingDialog();
                UserInfo userInfo = new UserInfo();
                userInfo.setUser_alias(this.binding.alias.getText().toString().trim());
                userInfo.setUser_name(this.binding.name.getText().toString().trim());
                userInfo.setUser_password(this.binding.psd.getText().toString().trim());
                userInfo.setUser_birthday(this.binding.birthdayDate.getText().toString().trim());
                userInfo.setUser_jobtitle(this.binding.jobTitle.getText().toString().trim());
                for (LoginReceBean.DataBean.Clipue clipue : clipues) {
                    if (clipue.getClipue_name().equals(tempClipue.getClipue_name())) {
                        userInfo.setUser_clipue(clipue.getId() + "");
                        break;
                    }
                }
                if (tempPro != null) {
                    for (LoginReceBean.DataBean.ResearchPro pro : pros) {
                        if (pro.getPro_name().equals(tempPro.getPro_name())) {
                            userInfo.setUser_research_pro_ids(pro.getId() + "");
                            break;
                        }
                    }
                }
                if (tempPaper != null) {
                    for (LoginReceBean.DataBean.ResearchPaper paper : papers) {
                        if (paper.getPaper_name().equals(tempPaper.getPaper_name())) {
                            userInfo.setUser_research_paper_ids(paper.getId() + "");
                            break;
                        }
                    }
                }
                if (tempCom != null) {
                    for (LoginReceBean.DataBean.Composition composition : compositions) {
                        if (composition.getBook_name().equals(tempCom.getBook_name())) {
                            userInfo.setUser_composition_ids(composition.getId() + "");
                            break;
                        }
                    }
                }
                userInfo.setUser_sex(sex);
                userInfo.setUser_telephone(this.binding.phone.getText().toString().trim());
                if (this.isEdit) {
                    this.persenter.updateUser(userInfo);
                } else {
                    this.persenter.addUser(userInfo);
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

    private void setTextData(TextView view, TextView viewParent, String data) {
        view.setText(data + "");
        if (!isEdit) {
            viewParent.setVisibility(View.GONE);
        }
    }

    public void showDateSwitcher() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2020, 1, 1);
        TimePickerView pvTime = new TimePickerBuilder(this, (date, v) -> binding.birthdayDate.setText(TimeUtil.getTime(date)))
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

    private boolean verParams() {
        if (TextUtils.isEmpty(this.binding.alias.getText().toString().trim())) {
            Toast.makeText(this, "教师名称不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(this.binding.name.getText().toString().trim())) {
            Toast.makeText(this, "账号名称不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(this.binding.psd.getText().toString().trim())) {
            Toast.makeText(this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(this.binding.phone.getText().toString().trim())) {
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(sex)) {
            Toast.makeText(this, "性别不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(this.binding.clipueText.getText().toString().trim())) {
            Toast.makeText(this, "系别不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(this.binding.birthdayDate.getText().toString().trim())) {
            Toast.makeText(this, "生日不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CLIPUE_REQUEST && resultCode == Activity.RESULT_OK) {
            showLoadingDialog();
            persenter.queryClipue();
        } else if (requestCode == PRO_REQUEST && resultCode == Activity.RESULT_OK) {
            showLoadingDialog();
            persenter.queryPro();
        } else if (requestCode == PAPER_REQUEST && resultCode == Activity.RESULT_OK) {
            showLoadingDialog();
            persenter.queryPaper();
        } else if (requestCode == COM_REQUEST && resultCode == Activity.RESULT_OK) {
            showLoadingDialog();
            persenter.queryCom();
        }
    }

    @Override
    public void onSuccess(boolean isSuccess, Object data) {
        dimissLoadingDialog();
        if (data instanceof ClipuesBean) {
            ClipuesBean clipuesBean = (ClipuesBean) data;
            if (clipuesBean.getData() == null) {
                return;
            }
            this.clipues.clear();
            this.clipues.addAll(clipuesBean.getData());
            new RxTimer().timer(1000, number -> this.initUserInfo());
        } else if (data instanceof ProBean) {
            ProBean proBean = (ProBean) data;
            if (proBean.getData() == null) {
                return;
            }
            this.pros.clear();
            this.pros.addAll(proBean.getData());
        } else if (data instanceof PaperBean) {
            PaperBean paperBean = (PaperBean) data;
            if (paperBean.getData() == null) {
                return;
            }
            this.papers.clear();
            this.papers.addAll(paperBean.getData());
        } else if (data instanceof ComBean) {
            ComBean comBean = (ComBean) data;
            if (comBean.getData() == null) {
                return;
            }
            this.compositions.clear();
            this.compositions.addAll(comBean.getData());
        }
    }

    private void initUserInfo() {
        LoginReceBean.DataBean info = (LoginReceBean.DataBean) getIntent().getSerializableExtra("userInfo");
        isEdit = getIntent().getBooleanExtra("isEdit", false);
        if (info != null) {
            this.setEditData(this.binding.alias, info.getUser_alias());
            this.binding.name.setClickable(false);
            this.binding.name.setFocusable(false);
            this.binding.psd.setClickable(false);
            this.binding.psd.setFocusable(false);
            this.setEditData(this.binding.name, info.getName());
            this.setEditData(this.binding.psd, info.getPsd());
            this.setEditData(this.binding.jobTitle, info.getUser_jobtitle());
            this.setEditData(this.binding.phone, info.getUser_telephone());
            this.setTextData(this.binding.birthdayDate, this.binding.birthdayBg, info.getUser_birthday());
            String clipue = "";
            for (LoginReceBean.DataBean.Clipue tempClipue : this.clipues) {
                if (tempClipue.getId() == info.getUser_clipues().get(0).getId()) {
                    clipue = tempClipue.getClipue_name();
                    this.tempClipue = tempClipue;
                    break;
                }
            }
            this.setTextData(this.binding.clipueText, this.binding.clipueBg, clipue);
            String pro = "";
            if (info.getResearchPros() != null && info.getResearchPros().size() > 0) {
                for (LoginReceBean.DataBean.ResearchPro tempPro : pros) {
                    if (tempPro.getId() == info.getResearchPros().get(0).getId()) {
                        pro = tempPro.getPro_name();
                        this.tempPro = tempPro;
                        break;
                    }
                }
            }
            this.setTextData(this.binding.proText, this.binding.proBg, pro);
            if (!pro.equals("")) {
                this.binding.proText.setOnClickListener(new ClickProxy(v -> {
                    Intent intent = new Intent(this, ProActivity.class);
                    intent.putExtra("pro", info.getResearchPros().get(0));
                    intent.putExtra("isEdit",isEdit);
                    this.startActivity(intent);
                }));
            }
            String paper = "";
            if (info.getResearchPapers() != null && info.getResearchPapers().size() > 0) {
                for (LoginReceBean.DataBean.ResearchPaper tempPaper : papers) {
                    if (tempPaper.getId() == info.getResearchPapers().get(0).getId()) {
                        paper = tempPaper.getPaper_name();
                        this.tempPaper = tempPaper;
                        break;
                    }
                }
            }
            this.setTextData(this.binding.paperText, this.binding.paperBg, paper);
            if (!paper.equals("")) {
                this.binding.paperText.setOnClickListener(new ClickProxy(v -> {
                    Intent intent = new Intent(this, PaperActivity.class);
                    intent.putExtra("paper", info.getResearchPapers().get(0));
                    intent.putExtra("isEdit",isEdit);
                    this.startActivity(intent);
                }));
            }
            String com = "";
            if (info.getCompositions() != null && info.getCompositions().size() > 0) {
                for (LoginReceBean.DataBean.Composition tempCom : compositions) {
                    if (tempCom.getId() == info.getCompositions().get(0).getId()) {
                        com = tempCom.getBook_name();
                        this.tempCom = tempCom;
                        break;
                    }
                }
            }
            this.setTextData(this.binding.comPositionText, this.binding.comPositionBg, com);
            if (!com.equals("")) {
                this.binding.comPositionText.setOnClickListener(new ClickProxy(v -> {
                    Intent intent = new Intent(this, ComPositionActivity.class);
                    intent.putExtra("com", info.getCompositions().get(0));
                    intent.putExtra("isEdit",isEdit);
                    this.startActivity(intent);
                }));
            }
            if (!isEdit) {
                this.binding.save.setEnabled(false);
            }
            if (info.getUser_sex().equals("男")) {
                this.binding.male.setChecked(true);
                this.binding.femle.setChecked(false);
            } else {
                this.binding.male.setChecked(false);
                this.binding.femle.setChecked(true);
            }
            return;
        }
    }

    @Override
    public void onFailed(String msg) {
        dimissLoadingDialog();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdateSuccess(String msg) {
        this.dimissLoadingDialog();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoDataView() {
        dimissLoadingDialog();
    }

    @Override
    public void showNoNetWork() {
        dimissLoadingDialog();
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_OK);
        finish();
        super.onBackPressed();
    }
}
