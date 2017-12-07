package com.sunny.beauty;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import sunny.basemodel.BaseActivity;
import sunny.basemodel.util.PV;

/**
 * author : wyy
 * time   : 2017/09/11
 * desc   : 加载了一个 receylerView 列表数据
 */

public class ViewPagerActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @InjectView(R.id.content_vp)
    ViewPager contentVp;
    @InjectView(R.id.view_group_ll)
    LinearLayout viewGroupLl;


    /**
     * 装点点的ImageView数组
     */
    private ImageView[] tips;

    /**
     * 装ImageView数组
     */
    private ImageView[] mImageViews;

    /**
     * @param savedInstanceState
     */
    private int[] imgIdArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        ButterKnife.inject(this);
        initListener();
        initData();
    }

    private void initData() {
        imgIdArray = new int[]{R.drawable.view_pager_1,
                R.drawable.view_pager_2,
                R.drawable.view_pager_3,
                R.drawable.view_pager_4};

        //将点点 加入到 viewGroup中
        tips = new ImageView[imgIdArray.length];
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(10, 10));
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            viewGroupLl.addView(imageView, layoutParams);
        }

        //将图片装载到数组中
        mImageViews = new ImageView[imgIdArray.length];
        for (int i = 0; i < mImageViews.length; i++) {
            ImageView imageView = new ImageView(this);

            //获取图片的宽高
            Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), imgIdArray[i]);
            int y = bitmap.getHeight();
            int x = bitmap.getWidth();

            float f1, f2;
            f1 = x / PV.DisplayWidth;//宽比
            f2 = y / PV.DisplayHeight;//高比
            if ((f1 != 0 && f2 != 0) && (x > PV.DisplayWidth || y > PV.DisplayHeight)) {
                if (f1 > f2) {//以宽比为基准
                    x = PV.DisplayWidth;
                    y = (int) (y / f1);
                } else {//以高比为基准
                    y = PV.DisplayHeight;
                    x = (int) (y / f2);
                }
            }

            //imageView.setLayoutParams(new LinearLayout.LayoutParams(x, y));
            imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setImageBitmap(bitmap);

            mImageViews[i] = imageView;
            //imageView.setBackgroundResource(imgIdArray[i]);

        }

        //设置adapter
        contentVp.setAdapter(new PictureSlidePagerAdapter());
        //设置监听，主要是设置点点的背景
        contentVp.setOnPageChangeListener(this);

        //设置ViewPager的默认项，设置为长度的 100 倍，这样子开始就能往左滑动
        contentVp.setCurrentItem(0);
    }

    private void initListener() {
        contentVp.setOnPageChangeListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setImageBackground(position % mImageViews.length);
    }

    private void setImageBackground(int selectItems) {
        for (int i = 0; i < tips.length; i++) {
            if (i == selectItems) {
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class PictureSlidePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imgIdArray.length;//<span style="white-space: pre;">指定ViewPager的总页数</span>
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mImageViews[position % mImageViews.length]);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageViews[position % mImageViews.length], 0);
            return mImageViews[position % mImageViews.length];
        }
    }
}