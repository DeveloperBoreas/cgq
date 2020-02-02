package com.boreas.adapter;

import android.content.Context;
import android.widget.TextView;

import com.boreas.R;
import com.boreas.adapter.adapter.BaseRecyclerAdapter;
import com.boreas.adapter.adapter.BaseRecyclerHolder;
import com.boreas.modle.LoginReceBean;
import com.boreas.weight.CircleImageView;

import java.util.List;

public class UserListAdapter extends BaseRecyclerAdapter<LoginReceBean.DataBean> {

    public UserListAdapter(Context context, List<LoginReceBean.DataBean> list, int itemLayoutId) {
        super(context, list, itemLayoutId);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, LoginReceBean.DataBean item, int position, boolean isScrolling) {
        CircleImageView headIcon = holder.getView(R.id.headIcon);
        TextView name = holder.getView(R.id.name);
        TextView sex = holder.getView(R.id.sex);
        TextView clipue = holder.getView(R.id.clipue);
        headIcon.setImageResource(R.drawable.bt);
        name.setText(item.getUser_alias());
        sex.setText(item.getUser_sex());
        if (item.getUser_clipues().size() > 0) {
            clipue.setText(item.getUser_clipues().get(0).getClipue_name());
        }
    }
}
