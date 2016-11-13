package com.duanlian.daimeng.ui.fragment.music;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.duanlian.daimeng.R;
import com.duanlian.daimeng.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;


public class LocalMusicFragment extends BaseFragment {


    ListView mListView;

    public static LocalMusicFragment newInstance() {
        LocalMusicFragment fragment = new LocalMusicFragment();
        return fragment;
    }


    @Override
    public View initView() {
        View view = View.inflate(getBaseActivity(), R.layout.fragment_local_music, null);
        mListView = (ListView) view.findViewById(R.id.id_stickynavlayout_innerscrollview);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("ListView----item--->>>>" + i);
        }
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, list);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "click " + position, Toast.LENGTH_LONG).show();
            }
        });
    }

}
