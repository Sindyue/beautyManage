package com.sunny.beauty.rv;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.sunny.beauty.R;

/**
 * author : wyy
 * time   : 2017/09/12
 * desc   :
 */

public class MyPtrRefresher extends LinearLayout {
    public MyPtrRefresher(Context context) {
        super(context, null);
        LayoutInflater.from(context).inflate(R.layout.view_ptrrefresher, this);
    }

    public MyPtrRefresher(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        LayoutInflater.from(context).inflate(R.layout.view_ptrrefresher, this);
    }

    public MyPtrRefresher(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_ptrrefresher, this);
    }
}