package com.boreas.adapter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.recyclerview.widget.RecyclerView;

import com.boreas.framework.ClickProxy;

import java.util.List;


public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerHolder> {

    private static final String TAG = BaseRecyclerAdapter.class.getSimpleName();

    private Context context;
    private List<T> list;
    private LayoutInflater inflater;
    private int itemLayoutId;
    private boolean isScrolling;
    private OnItemClickListener listener;
    private OnItemLongClickListener longClickListener;
    private RecyclerView recyclerView;

    /**
     * 在RecyclerView提供数据的时候调用
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView parent, View view, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(RecyclerView parent, View view, int position);
    }

    public void insert(T item, int position) {
        list.add(position, item);
        notifyItemInserted(position);
    }

    public void delete(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void reset(List<T> list) {
        if (list == null) {
            throw new NullPointerException(TAG + "__________list is not null!!!!! ");
        }
        this.list = list;
        notifyDataSetChanged();
    }

    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException(TAG + "__________list is not null!!!!! ");
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public BaseRecyclerAdapter(Context context, List<T> list, int itemLayoutId) {
        this.context = context;
        this.list = list;
        this.itemLayoutId = itemLayoutId;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public BaseRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(itemLayoutId, parent, false);
        return BaseRecyclerHolder.getRecyclerHolder(context, view);
    }

    @Override
    public void onBindViewHolder(final BaseRecyclerHolder holder, int position) {

        if (listener != null) {
//            holder.itemView.setBackgroundResource(R.drawable.ic_launcher_background);
        }
        holder.itemView.setOnClickListener(new ClickProxy(view -> {
            if (listener != null && view != null && recyclerView != null) {
                int position1 = recyclerView.getChildAdapterPosition(view);
                listener.onItemClick(recyclerView, view, position1);
            }
        }));


        holder.itemView.setOnLongClickListener(view -> {
            if (longClickListener != null && view != null && recyclerView != null) {
                int position12 = recyclerView.getChildAdapterPosition(view);
                longClickListener.onItemLongClick(recyclerView, view, position12);
                return true;
            }
            return false;
        });

        convert(holder, list.get(position), position, isScrolling);

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public List<T> getListData() {
        return this.list;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    /**
     * 填充RecyclerView适配器的方法，子类需要重写
     *
     * @param holder      ViewHolder
     * @param item        子项
     * @param position    位置
     * @param isScrolling 是否在滑动
     */
    public abstract void convert(BaseRecyclerHolder holder, T item, int position, boolean isScrolling);
}
