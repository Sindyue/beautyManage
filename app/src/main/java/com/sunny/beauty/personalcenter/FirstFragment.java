package com.sunny.beauty.personalcenter;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sunny.beauty.R;
import com.sunny.beauty.ViewPagerActivity;
import com.sunny.beauty.rv.CaptureActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.app.Activity.RESULT_OK;


/**
 * author : wyy
 * time   : 2017/08/24
 * desc   :第一个碎片
 */

public class FirstFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "CenterFragment";
    private final int REQUEST_CODE_SCAN = 1;

    @InjectView(R.id.item1_tv)
    TextView item1Tv;
    @InjectView(R.id.item2_tv)
    TextView item2Tv;

    private String newStr = "CenterFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_one, container, false);
        ButterKnife.inject(this, view);
        initListener();
        return view;
    }

    private void initListener() {
        item1Tv.setOnClickListener(this);
        item2Tv.setOnClickListener(this);
    }

    public void setText(String page) {
        newStr = page;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item1_tv:
                startScanningActivity();
                break;
            case R.id.item2_tv:
                startViewPagerActivity();
                break;
            default:
                break;
        }
    }

    /**
     * Handle the scanning result.
     *
     * @param requestCode The request code. See at
     * @param resultCode  The result code.
     * @param data        The result.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SCAN:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (null != bundle) {
                        Toast.makeText(getActivity(), bundle.getString("result"), Toast.LENGTH_SHORT);
                    }
                }
                break;
            default:
                break;
        }
    }


    /**
     * Launch the camera
     */
    private void startScanningActivity() {
        try {
            Intent intent = new Intent(getActivity(), CaptureActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivityForResult(intent, REQUEST_CODE_SCAN);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动 viewpager 页
     */
    private void startViewPagerActivity() {
        try {
            Intent intent = new Intent(getActivity(), ViewPagerActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivityForResult(intent, REQUEST_CODE_SCAN);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
}
