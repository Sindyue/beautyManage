package com.sunny.beauty.rv;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sunny.beauty.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import sunny.basemodel.BaseActivity;
import sunny.basemodel.refresh.LoadingFooter;

/**
 * author : wyy
 * time   : 2017/09/11
 * desc   :
 */

public class CaptureActivity extends BaseActivity {

    @InjectView(R.id.content_rv)
    RecyclerView contentRv;
    @InjectView(R.id.store_house_ptr_frame)
    PtrFrameLayout ptrLayout;

    private RVAdapter mAdapter;
    private List<Map<Object, String>> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.inject(this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        contentRv.setLayoutManager(mLayoutManager);
        contentRv.setItemAnimator(new DefaultItemAnimator());
        contentRv.setHasFixedSize(true);
        mAdapter = new RVAdapter(this);
        contentRv.setAdapter(mAdapter);
        /*初始化搜索动画相关部分*/
        refreshData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private void refreshData() {
        getDataFromNet();
        mAdapter.setDatas(mDataList);

        // MaterialHeader
        /*final MaterialHeader header = new MaterialHeader(this);
        int[] colors = getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, LocalDisplay.dp2px(15), 0, LocalDisplay.dp2px(10));
        header.setPtrFrameLayout(ptrLayout);
        // 为布局设置头部和底部布局
        ptrLayout.setLoadingMinTime(1000);
        ptrLayout.setHeaderView(header);
        ptrLayout.addPtrUIHandler(header);*/

        // 为布局设置头部和底部布局
        ptrLayout.setHeaderView(new MyPtrRefresher(this));
        // ptrLayout.setFooterView(new MyPtrRefresher(MainActivity.this));
        ptrLayout.addPtrUIHandler(new MyPtrHandler(this, ptrLayout));
        initEvent();
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        // 为布局设置下拉刷新和上拉加载的回调事件
        ptrLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, contentRv, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mAdapter.setDefaultFooterStatus(mContext, LoadingFooter.State.Loading);
                ptrLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setDefaultFooterStatus(mContext, LoadingFooter.State.TheEnd);
                        ptrLayout.refreshComplete();
                    }
                }, 1500);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void getDataFromNet() {
        Map<Object, String> map;
        for (int i = 1; i < 20; i++) {
            map = new HashMap<>();
            map.put("title", "Num" + i);
            mDataList.add(map);
        }
    }


}