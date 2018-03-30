package com.sunny.beauty.rv;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sunny.beauty.R;
import com.sunny.beauty.ViewPagerActivity;

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
 * desc   : 加载了一个 receylerView 列表数据
 */

public class CaptureActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "wyyCaptureActivity";

    @InjectView(R.id.content_rv)
    RecyclerView contentRv;
    @InjectView(R.id.store_house_ptr_frame)
    PtrFrameLayout ptrLayout;
    @InjectView(R.id.back_btn)
    TextView backBtn;
    @InjectView(R.id.new_btn)
    TextView newBtn;

    private RVAdapter mAdapter;
    private List<Map<Object, String>> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.inject(this);
        initListener();

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        contentRv.setLayoutManager(mLayoutManager);
        contentRv.setItemAnimator(new DefaultItemAnimator());
        contentRv.setHasFixedSize(true);
        mAdapter = new RVAdapter(this);
        contentRv.setAdapter(mAdapter);
        /*初始化搜索动画相关部分*/
        refreshData();
        Log.i(TAG, "onCreate: ");
    }


    @Override
    protected void onResume() {
        Log.i(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, "onRestart: ");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        //此方法不会走到
        Log.i(TAG, "onSaveInstanceState: ");
        outState.putString("text", "dddd");
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "onSaveInstanceState: 11");
        outState.putString("text", "fffffff");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i(TAG, "onRestoreInstanceState: savedInstanceState.getText = " + savedInstanceState.getString("text"));
        super.onRestoreInstanceState(savedInstanceState);
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.new_btn:
                Intent intent = new Intent(mContext, ViewPagerActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void initListener() {
        backBtn.setOnClickListener(this);
        newBtn.setOnClickListener(this);
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