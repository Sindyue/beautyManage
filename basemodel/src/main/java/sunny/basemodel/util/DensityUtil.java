package sunny.basemodel.util;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.Display;
import android.view.View;

/**
 * 尺寸转换的工具类
 */
public class DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Fragment context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dip2px(Activity context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Fragment context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2dip(Activity context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int theTranslationXlength(Activity context, View viewInCenter) {
        Display dp = context.getWindowManager().getDefaultDisplay();
        int screenWidth = dp.getWidth();
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        viewInCenter.measure(width, height);
        int viewWidth = viewInCenter.getMeasuredWidth();
        int length = (screenWidth - viewWidth) / 2 - DensityUtil.dip2px(context, 10f);
        return length;
    }

    /**
     * 获取屏幕三等分之后的图片宽度
     */
    public static int getOneOfThirdImgWidth(Context context) {
        int width = dip2px(context, 80);
        if (PV.DisplayWidth != 0) {
            width = (PV.DisplayWidth - dip2px(context, 40)) / 3;
        }
        return width;
    }

}
