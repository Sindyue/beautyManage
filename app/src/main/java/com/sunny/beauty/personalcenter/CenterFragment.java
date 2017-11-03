package com.sunny.beauty.personalcenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunny.beauty.R;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * author : wyy
 * time   : 2017/08/24
 * desc   :
 */

public class CenterFragment extends Fragment {
    private static final String TAG = "CenterFragment";

    @InjectView(R.id.content_tv)
    TextView contentTv;

    private String newStr = "CenterFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_center, container, false);
        ButterKnife.inject(this, view);
        contentTv.setText(newStr);
        return view;
    }

    public void setText(String page) {
        newStr = page;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
