package com.sunny.beauty;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;

import com.sunny.beauty.personalcenter.CenterFragment;
import com.sunny.beauty.personalcenter.FirstFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import sunny.basemodel.BaseActivity;
import sunny.basemodel.util.PV;

/**
 * 主页面fdsh
 */
public class MainActivity extends BaseActivity {

    @InjectView(R.id.frame_content)
    FrameLayout frameContent;
    @InjectView(R.id.layout_write_fl)
    FrameLayout layoutWriteFl;
    @InjectView(R.id.layout_done_fl)
    FrameLayout layoutDoneFl;
    @InjectView(R.id.layout_center_fl)
    FrameLayout layoutCenterFl;
    @InjectView(R.id.frameMenu)
    FrameLayout frameMenu;

    private FragmentManager fragmentManager;
    private CenterFragment fragmentTwo, mCenterFragment;
    private FirstFragment fragmentOne;
    private final int REQUEST_CODE_SCAN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initPVvalue();
        initData();
    }

    private void initPVvalue() {
        //获取屏幕宽高
        if (PV.DisplayWidth <= 0) {
            DisplayMetrics metric = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metric);
            PV.DisplayWidth = metric.widthPixels; // 屏幕宽度（像素）
            PV.DisplayHeight = metric.heightPixels;//屏幕高度(像素)
        }
    }

    private void initData() {
        fragmentManager = this.getSupportFragmentManager();
        setTabSelection(1);
    }

    @OnClick({R.id.layout_write_fl, R.id.layout_done_fl, R.id.layout_center_fl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_write_fl:
                setTabSelection(1);
                break;
            case R.id.layout_done_fl:
                setTabSelection(2);
                break;
            case R.id.layout_center_fl:
                setTabSelection(3);
                break;
            default:
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标。1表示问答，2表示农友圈，3表示个人中心。
     */
    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        resetStatus();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 1:
                // 当点击了联系人tab时，改变控件的图片和文字颜色
                layoutWriteFl.setSelected(true);
                if (fragmentOne == null) {
                    // 如果ContactsFragment为空，则创建一个并添加到界面上
                    fragmentOne = new FirstFragment();
                    fragmentOne.setText("fragmentOne");
                    transaction.add(R.id.frame_content, fragmentOne);
                } else {
                    // 如果ContactsFragment不为空，则直接将它显示出来
                    transaction.show(fragmentOne);
                }
                break;
            case 2:
                layoutDoneFl.setSelected(true);
                if (fragmentTwo == null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    fragmentTwo = new CenterFragment();
                    transaction.add(R.id.frame_content, fragmentTwo);
                    fragmentTwo.setText("fragmentTwo");
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(fragmentTwo);
                }
                break;
            case 3:
                // 当点击了设置tab时，改变控件的图片和文字颜色
                layoutCenterFl.setSelected(true);
                if (mCenterFragment == null) {
                    // 如果SettingFragment为空，则创建一个并添加到界面上
                    mCenterFragment = new CenterFragment();
                    transaction.add(R.id.frame_content, mCenterFragment);
                    mCenterFragment.setText("center");
                } else {
                    // 如果SettingFragment不为空，则直接将它显示出来
                    transaction.show(mCenterFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    /**
     * 重置三个按钮的选中事件
     */
    private void resetStatus() {
        layoutCenterFl.setSelected(false);
        layoutDoneFl.setSelected(false);
        layoutWriteFl.setSelected(false);
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (fragmentOne != null) {
            transaction.hide(fragmentOne);
        }
        if (fragmentTwo != null) {
            transaction.hide(fragmentTwo);
        }
        if (mCenterFragment != null) {
            transaction.hide(mCenterFragment);
        }
    }
}
