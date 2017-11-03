package sunny.basemodel;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import sunny.basemodel.util.SystemBarTintManager;
import sunny.basemodel.view.CustomProgressDialog;

/**
 * author : wyy
 * time   : 2017/08/24
 * desc   :
 */

public class BaseActivity extends AppCompatActivity {
    //    private SystemBarTintManager tintManager;
    protected Context mContext;
    protected CustomProgressDialog mCustomProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        //initSystemBar();
        //initNetSetting();
    }

    /**
     * 友盟统计需要添加的
     */
    @Override
    protected void onResume() {
        super.onResume();
        //MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //MobclickAgent.onPause(this);
    }

    private void initNetSetting() {
        //安卓2.3以后访问网络增加内容//android.os.networkonmainthredexception
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }

    /**
     * 初始化状态栏（沉浸）
     */
    protected void initSystemBar() {
        if (Build.VERSION.SDK_INT >= 19) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            //if (true) {
            winParams.flags |= bits;
            //} else {
            // winParams.flags &= ~bits;
            //}
            win.setAttributes(winParams);
        }
        // create our manager instance after the content view is set
        SystemBarTintManager tintManager = new SystemBarTintManager(this);

        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.common_theme_color);//通知栏所需颜色
    }

    /**
     * 状态栏透明，和内容部分颜色相同
     */
    protected void initFullSystemBar() {
        //设置设置Activity全屏(限4.0以上版本)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            getWindow().addFlags(0x00000100);
            getWindow().addFlags(0x00000200);
        }
        //设置设置Activity全屏&导航栏透明(限4.4及以上版本)
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    public void showProgressDialog() {
        if (mCustomProgressDialog == null) {
            mCustomProgressDialog = new CustomProgressDialog(mContext, "请稍候...");
            mCustomProgressDialog.setCanceledOnTouchOutside(false);
        }
        mCustomProgressDialog.show();
    }

    public void closeProgressDialog() {
        if (mCustomProgressDialog != null && mCustomProgressDialog.isShowing()) {
            mCustomProgressDialog.dismiss();
        }
    }
}
