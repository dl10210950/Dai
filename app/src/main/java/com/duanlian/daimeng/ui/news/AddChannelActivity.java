
package com.duanlian.daimeng.ui.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.duanlian.daimeng.R;
import com.duanlian.daimeng.adapter.NewsChannelGridViewAdapter;
import com.duanlian.daimeng.ui.start.MainActivity;

import java.util.ArrayList;
import java.util.List;

import static com.duanlian.daimeng.constant.LocalData.channel;

public class AddChannelActivity extends AppCompatActivity {
    private GridView mGridView;
    private NewsChannelGridViewAdapter mGridViewAdapter;
    private List<String> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_channel);
        initView();
    }

    private void initView() {
        mGridView = (GridView) findViewById(R.id.gv_channel);
        mList = new ArrayList<>();
        for (int i = 0; i < channel.length; i++) {
            mList.add(channel[i]);
        }
        mGridViewAdapter = new NewsChannelGridViewAdapter(this, mList);
        mGridView.setAdapter(mGridViewAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mGridViewAdapter.choiceState(position);
            }
        });
        findViewById(R.id.header_layout_leftview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void addChannel(View view) {
        switch (view.getId()) {
            case R.id.tv_check_all:
                mGridViewAdapter.changeState(2);
                break;
            case R.id.tv_invert:
                mGridViewAdapter.changeState(1);
                break;
            case R.id.btn_sure:
                Intent intent = new Intent(AddChannelActivity.this, MainActivity.class);
                intent.putExtra("intentType", 4);
                startActivity(intent);
                break;
        }
    }
}
