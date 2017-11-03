package sunny.basemodel.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;

import sunny.basemodel.R;


/**
 * Created by wyy on 2016/5/26.
 */

public class CustomProgressDialog extends Dialog {
    public CustomProgressDialog(Context context, String strMessage) {
        this(context, R.style.CustomProgressDialog, strMessage);
    }

    public CustomProgressDialog(Context context, int theme, String strMessage) {
        super(context, theme);
        this.setContentView(R.layout.custom_progress_dialog);
        this.getWindow().getAttributes().gravity = Gravity.CENTER;
        /*TextView tvMsg = (TextView) this.findViewById(R.id.id_tv_loadingmsg);
        if (tvMsg != null) {
            tvMsg.setText(strMessage);
        }*/

        /*帧动画的启动*/
        /* ImageView mProgressBar = (ImageView) findViewById(R.id.loadingImageView);
        AnimationDrawable animationDrawable = (AnimationDrawable) mProgressBar.getDrawable();
        if (animationDrawable != null) {
            animationDrawable.start();
        }*/
    }

    //用于控制失去焦点后是否消失
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus) {
            //dismiss();
        }
    }
}
