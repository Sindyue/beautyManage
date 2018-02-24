package com.sunny.beauty.view;

import android.os.Bundle;

import com.sunny.beauty.R;

import sunny.basemodel.BaseActivity;

/**
 * author : wyy
 * time   : 2018/02/24
 * desc   :自定义View类
 */

public class CustomViewActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
    }
}
