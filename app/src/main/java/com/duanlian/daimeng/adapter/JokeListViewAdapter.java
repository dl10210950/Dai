package com.duanlian.daimeng.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.duanlian.daimeng.R;
import com.duanlian.daimeng.bean.YiYuanJokeText;

import java.util.List;

/**
 * 笑话页面的ListView的adapter
 */

public class JokeListViewAdapter extends BaseAdapter {
    private Context mContext;
    List<YiYuanJokeText.ContentlistBean> mList;
    //普通布局的type
    static final int TYPE_ITEM = 0;
    //脚布局
    static final int TYPE_FOOTER = 1;
    //    //上拉加载更多
//    static final int PULL_LOAD_MORE = 0;
    //正在加载更多
    static final int LOADING_MORE = 1;
    //没有更多
    static final int NO_MORE = 2;
    //脚布局当前的状态,默认为没有更多
    int footer_state = 1;

    public JokeListViewAdapter(Context context, List<YiYuanJokeText.ContentlistBean> contentlist) {
        this.mContext = context;
        this.mList = contentlist;
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() + 1 : 0;
    }

    @Override
    public Object getItem(int position) {
        return mList != null ? mList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return mList != null ? position : 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mList.size()) {
            return TYPE_ITEM;  //返回普通布局
        } else {
            return TYPE_FOOTER; //返回脚布局
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        ViewHolder holder = null;
        FootViewHolder footHolder = null;
        if (convertView == null) {
            switch (itemViewType) {
                case TYPE_ITEM:
                    convertView = View.inflate(mContext, R.layout.item_joke_listview, null);
                    holder = new ViewHolder();
                    holder.date = (TextView) convertView.findViewById(R.id.tv_date);
                    holder.text = (TextView) convertView.findViewById(R.id.tv_text);
                    holder.title = (TextView) convertView.findViewById(R.id.tv_title);
                    convertView.setTag(holder);
                    break;
                case TYPE_FOOTER:
                    convertView = View.inflate(mContext, R.layout.recycler_load_more_layout, null);
                    footHolder = new FootViewHolder();
                    footHolder.mProgressBar = (ProgressBar) convertView.findViewById(R.id.progressbar);
                    footHolder.tv_state = (TextView) convertView.findViewById(R.id.foot_view_item_tv);
                    footHolder.tv_line1 = (TextView) convertView.findViewById(R.id.tv_line1);
                    footHolder.tv_line2 = (TextView) convertView.findViewById(R.id.tv_line2);
                    convertView.setTag(footHolder);
                    break;
            }
        } else {
            switch (itemViewType) {
                case TYPE_ITEM:
                    holder = (ViewHolder) convertView.getTag();
                    break;
                case TYPE_FOOTER:
                    footHolder = (FootViewHolder) convertView.getTag();
                    break;
            }
        }

        switch (itemViewType) {
            case TYPE_ITEM:
                String s = mList.get(position).ct.toString();
                String substring = s.substring(0, 16);
                holder.date.setText(substring);
                String s1 = mList.get(position).text.toString();
                String replace;
                String replace1 = null;
                if (s1.contains("<p>")) {
                    replace = s1.replace("<p>", "");
                    if (replace.contains("</p>")) {
                        replace1 = replace.replace("</p>", "");
                    }
                    holder.text.setText(replace1);
                } else {
                    holder.text.setText(s1);
                }
                holder.title.setText(mList.get(position).title);
                break;
            case TYPE_FOOTER:
                switch (footer_state) {//根据状态来让脚布局发生改变
//                case PULL_LOAD_MORE://上拉加载
//                    footViewHolder.mProgressBar.setVisibility(View.GONE);
//                    footViewHolder.tv_state.setText("上拉加载更多");
//                    break;
                    case LOADING_MORE:
                        footHolder.mProgressBar.setVisibility(View.VISIBLE);
                        footHolder.tv_line1.setVisibility(View.GONE);
                        footHolder.tv_line2.setVisibility(View.GONE);
                        footHolder.tv_state.setText("正在加载...");
                        break;
                    case NO_MORE:
                        footHolder.mProgressBar.setVisibility(View.GONE);
                        footHolder.tv_line1.setVisibility(View.VISIBLE);
                        footHolder.tv_line2.setVisibility(View.VISIBLE);
                        footHolder.tv_state.setText("我是有底线的");
                        footHolder.tv_state.setTextColor(Color.parseColor("#ff00ff"));
                        break;
                }

                break;
        }
        return convertView;
    }

    static class ViewHolder {
        TextView date, text, title;
    }

    /**
     * 脚布局的holder
     */
    class FootViewHolder {
        private ProgressBar mProgressBar;
        private TextView tv_state;
        private TextView tv_line1;
        private TextView tv_line2;
    }

    /**
     * 改变脚布局的状态的方法,在activity根据请求数据的状态来改变这个状态
     *
     * @param state
     */
    public void changeState(int state) {
        this.footer_state = state;
        notifyDataSetChanged();
    }

    public void setDataChange(List<YiYuanJokeText.ContentlistBean> contentlist) {
        this.mList = contentlist;
        notifyDataSetChanged();

    }
}
