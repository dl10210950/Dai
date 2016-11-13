package com.duanlian.daimeng.ui.fragment.music;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.duanlian.daimeng.R;
import com.duanlian.daimeng.adapter.NetMusicRecycleAdapter;
import com.duanlian.daimeng.base.BaseFragment;

import java.util.ArrayList;


public class NetMusicFragment extends BaseFragment {


    RecyclerView mRecycleView;

    public static NetMusicFragment newInstance() {
        NetMusicFragment fragment = new NetMusicFragment();
        return fragment;
    }



    @Override
    public View initView() {
        View view = View.inflate(getBaseActivity(),R.layout.fragment_net_music,null);
        mRecycleView = (RecyclerView) view.findViewById(R.id.id_stickynavlayout_innerscrollview);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false));
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("RecycleView----item-->>>>" + i);
        }

        NetMusicRecycleAdapter adapter = new NetMusicRecycleAdapter(list);
        mRecycleView.setAdapter(adapter);
        adapter.setOnItemOnClickListener(new NetMusicRecycleAdapter.OnItemOnClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(getActivity(), "click " + position, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                Toast.makeText(getActivity(), "Long-click " + position, Toast.LENGTH_LONG).show();
            }
        });
    }

}
