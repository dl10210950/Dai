package com.duanlian.daimeng.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.duanlian.daimeng.R;
import com.duanlian.daimeng.database.DataBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 赛事选择页面的gridView的adapter
 */
public class NewsChannelGridViewAdapter extends BaseAdapter {
    private Context mContext;
    List<String> channelList;
    private boolean[] isCheck;
    ViewHolder holder = null;
    DataBase dataBase;


    public NewsChannelGridViewAdapter(Context context, List<String> channelList) {
        this.mContext = context;
        this.channelList = channelList;
         dataBase = new DataBase(context);
        if (channelList != null) {
            isCheck = new boolean[channelList.size()];
            for (int i = 0; i < channelList.size(); i++) {
                isCheck[i] = false;
            }
        }
    }

    @Override
    public int getCount() {
         return channelList != null ? channelList.size() : null;
    }

    @Override
    public Object getItem(int position) {
        return channelList != null ? channelList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return channelList != null ? position : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_gridview_channel, null);
            holder.textView = (TextView) convertView.findViewById(R.id.tv_league);
            holder.mRelativeLayout = (RelativeLayout) convertView.findViewById(R.id.ll_competition);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(channelList.get(position));
        if (isCheck[position]) {
            holder.mRelativeLayout.setBackgroundResource(R.drawable.channel_grid_shap_two);
            holder.textView.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        } else {
            holder.mRelativeLayout.setBackgroundResource(R.drawable.channel_grid_shap_one);
            holder.textView.setTextColor(mContext.getResources().getColor(R.color.black_444444));

        }
        List<String> choiceChannel = getData();

            for (int j = 0; j < choiceChannel.size(); j++) {
                if (channelList.get(position).equals(choiceChannel.get(j))) {
                    holder.mRelativeLayout.setBackgroundResource(R.drawable.channel_grid_shap_two);
                    holder.textView.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                    isCheck[position] = true;
                }

        }
        return convertView;
    }

    public void choiceState(int post) {
        isCheck[post] = isCheck[post] == true ? false : true;
        if (isCheck[post]) {
            dataBase.insert(channelList.get(post));
        } else {
            dataBase.delete(channelList.get(post));
        }
        this.notifyDataSetChanged();
    }

    public void changeState(int type) {
        if (type == 1) {//反选
            for (int i = 0; i < channelList.size(); i++) {
                isCheck[i] = isCheck[i] == true ? false : true;
                if (isCheck[i]) {
                    dataBase.insert(channelList.get(i));
                } else {
                    dataBase.delete(channelList.get(i));
                }
            }
        } else if (type == 2) {//全选
            for (int i = 0; i < channelList.size(); i++) {
                isCheck[i] = true;
                if (isCheck[i]) {
                    dataBase.insert(channelList.get(i));
                } else {
                    dataBase.delete(channelList.get(i));
                }
            }
        }

        notifyDataSetChanged();

    }

    static class ViewHolder {
        TextView textView;
        RelativeLayout mRelativeLayout;

    }

    /**
     * 通过查找数据库,拿到里面的数据
     */
    private List<String> getData() {
        List<String> list = new ArrayList<>();
        Cursor query = DataBase.getInstances(mContext).query();
        if (query.moveToFirst()) {
            do {
                String channel = query.getString(query.getColumnIndex("channel"));
                list.add(channel);
            } while (query.moveToNext());
        }
        //关闭查询游标
        query.close();
        return list;
    }

}
