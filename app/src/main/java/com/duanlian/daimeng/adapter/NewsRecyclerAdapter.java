package com.duanlian.daimeng.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.duanlian.daimeng.R;
import com.duanlian.daimeng.bean.YiYuanNewsBean;
import com.duanlian.daimeng.ui.activity.NewsDetailsActivity;

import java.util.List;

/**
 * 新闻recyclerView的adapter
 */

public class NewsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<YiYuanNewsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> mList;
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


    public NewsRecyclerAdapter(Context context, List<YiYuanNewsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlist) {
        this.mContext = context;
        this.mList = contentlist;
    }

    @Override
    public int getItemViewType(int position) {
        //如果position加1正好等于所有item的总和,说明是最后一个item,将它设置为脚布局
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = View.inflate(mContext, R.layout.item_news_recycler, null);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        } else if (viewType == TYPE_FOOTER) {
            View view = View.inflate(mContext, R.layout.recycler_load_more_layout, null);
            FootViewHolder footViewHolder = new FootViewHolder(view);
            return footViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).tvTitle.setText(mList.get(position).getTitle());
            ((MyViewHolder) holder).tvSource.setText(mList.get(position).getSource());
            ((MyViewHolder) holder).tvDate.setText(mList.get(position).getPubDate());
            //item的进场动画,有bug,待解决
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anim_bottom_in);
            holder.itemView.startAnimation(animation);
            if (mList.get(position).getImageurls().size() != 0) {
                ((MyViewHolder) holder).mImageView.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(mList.get(position).getImageurls().get(0).getUrl()).into(((MyViewHolder) holder).mImageView);
            } else {
                ((MyViewHolder) holder).mImageView.setVisibility(View.GONE);
            }
            ((MyViewHolder) holder).mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, NewsDetailsActivity.class);
                    intent.putExtra("url", mList.get(position).getLink());
                    mContext.startActivity(intent);
                }
            });
        } else if (holder instanceof FootViewHolder) {
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            if (position == 0) {//如果第一个就是脚布局,,那就让他隐藏
                footViewHolder.mProgressBar.setVisibility(View.GONE);
                footViewHolder.tv_line1.setVisibility(View.GONE);
                footViewHolder.tv_line2.setVisibility(View.GONE);
            } else {


                switch (footer_state) {//根据状态来让脚布局发生改变
//                case PULL_LOAD_MORE://上拉加载
//                    footViewHolder.mProgressBar.setVisibility(View.GONE);
//                    footViewHolder.tv_state.setText("上拉加载更多");
//                    break;
                    case LOADING_MORE:
                        footViewHolder.mProgressBar.setVisibility(View.VISIBLE);
                        footViewHolder.tv_line1.setVisibility(View.GONE);
                        footViewHolder.tv_line2.setVisibility(View.GONE);
                        footViewHolder.tv_state.setText("正在加载...");
                        break;
                    case NO_MORE:
                        footViewHolder.mProgressBar.setVisibility(View.GONE);
                        footViewHolder.tv_line1.setVisibility(View.VISIBLE);
                        footViewHolder.tv_line2.setVisibility(View.VISIBLE);
                        footViewHolder.tv_state.setText("我是有底线的");
                        footViewHolder.tv_state.setTextColor(Color.parseColor("#ff00ff"));
                        break;
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() + 1 : 0;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate, tvSource;
        private ImageView mImageView;
        RelativeLayout mLayout;


        public MyViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvDate = (TextView) view.findViewById(R.id.tv_date);
            tvSource = (TextView) view.findViewById(R.id.tv_source);
            mImageView = (ImageView) view.findViewById(R.id.imageview);
            mLayout = (RelativeLayout) view.findViewById(R.id.relative_item);
        }
    }

    /**
     * 脚布局的ViewHolder
     */
    public static class FootViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar mProgressBar;
        private TextView tv_state;
        private TextView tv_line1;
        private TextView tv_line2;


        public FootViewHolder(View itemView) {
            super(itemView);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.progressbar);
            tv_state = (TextView) itemView.findViewById(R.id.foot_view_item_tv);
            tv_line1 = (TextView) itemView.findViewById(R.id.tv_line1);
            tv_line2 = (TextView) itemView.findViewById(R.id.tv_line2);

        }
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

    public void setDataChange(List<YiYuanNewsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list) {
        this.mList = list;
        notifyDataSetChanged();

    }
}
