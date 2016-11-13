package com.duanlian.daimeng.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.duanlian.daimeng.R;

import java.util.ArrayList;
public class NetMusicRecycleAdapter extends RecyclerView.Adapter {

    private ArrayList<String> list = new ArrayList<String>();

    public NetMusicRecycleAdapter(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        setEvent(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.getTextView().setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.id_tv);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    protected void setEvent(final ViewHolder viewHolder) {
        viewHolder.getTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemOnClickListener != null) {
                    onItemOnClickListener.onItemClickListener(v, viewHolder.getLayoutPosition());
                }
            }
        });
        viewHolder.getTextView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemOnClickListener != null) {
                    onItemOnClickListener.onItemLongClickListener(v, viewHolder.getLayoutPosition());
                }
                return false;
            }
        });
    }

    public interface OnItemOnClickListener {
        void onItemClickListener(View view, int position);

        void onItemLongClickListener(View view, int position);
    }

    private OnItemOnClickListener onItemOnClickListener;

    public void setOnItemOnClickListener(OnItemOnClickListener onItemOnClickListener) {
        this.onItemOnClickListener = onItemOnClickListener;
    }
}
