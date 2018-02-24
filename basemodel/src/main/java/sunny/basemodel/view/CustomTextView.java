package sunny.basemodel.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import sunny.basemodel.R;

/**
 * author : wyy
 * time   : 2018/02/24
 * desc   : 自定义的TextView，可去除一般 TextView 文字上下的边距
 */

public class CustomTextView extends AppCompatTextView {

    private Context mContext;

    private Paint paint;        //绘制图形的画笔
    private int textColor;      //文字颜色
    private int textSize;       //文字大小
    private String viewText;    //绘制的文字
    private float textWidth;    //绘制的文字宽度
    private float textHeight;   //绘制的文字高度
    private int padding;        //文字的内边距


    //当不需要使用 xml 声明或者不需要使用 inflate 动态加载时候，实现此构造函数即可
    public CustomTextView(Context context) {
        this(context, null);
    }

    //当需要在 xml 中声明此控件，则需要实现此构造函数。并且在构造函数中把自定义的属性与控件的数据成员连接起来。
    public CustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //接受一个 style 资源
    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initPaintInfo();
        initLayout(attrs, defStyleAttr);
    }

    /**
     * 初始化布局相关文件
     *
     * @param attrs
     * @param defStyleAttr
     */
    private void initLayout(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = mContext.obtainStyledAttributes(attrs,
                R.styleable.CustomTextViewStyle, defStyleAttr, 0);
        textColor = a.getColor(R.styleable.CustomTextViewStyle_viewTextColor, 0xff333333);
        paint.setColor(textColor);

        textSize = a.getDimensionPixelSize(R.styleable.CustomTextViewStyle_viewTextSize, 30);
        paint.setTextSize(textSize);

        viewText = a.getString(R.styleable.CustomTextViewStyle_viewText);
        padding = a.getDimensionPixelSize(R.styleable.CustomTextViewStyle_viewPadding, 2);

        //文字笔
        textWidth = paint.measureText(viewText);
        textHeight = textSize;//这样计算出来的高度，仅仅是字体所占的高度，没有上下左右边距
        //paint.descent() - paint.ascent(); 这样计算出来的高度，会包含文字和 ascent 之间的空白区域

        //Log.i("wyy", "initLayout - paint.descent() = " + paint.descent() + ";  paint.ascent() = " + paint.ascent());

        a.recycle();
    }

    /**
     * 绘制画笔相关的初始化操作
     */
    private void initPaintInfo() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置抗锯齿
        //paint.setAntiAlias(true);
        //设置画笔的样式
        paint.setStrokeCap(Paint.Cap.ROUND);
        //设置画笔粗细大小
        paint.setStrokeWidth(3);
        //设置画笔的所画图形的样式
        paint.setStyle(Paint.Style.FILL);
        //设置画笔颜色
        //paint.setColor(0xffff6000);//0xffffff00
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension((int) textWidth + padding * 2, (int) textHeight + padding * 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将坐标原点移至控件中心
        canvas.translate(getWidth() / 2, getHeight() / 2);

        //x 轴
        canvas.drawLine(-getWidth() / 2, 0, getWidth() / 2, 0, paint);
        //y 轴
        canvas.drawLine(0, -getHeight() / 2, 0, getHeight() / 2, paint);


        //文字 baseline 在 y 轴方向的位置
        float baseLineY = Math.abs(paint.ascent() + paint.descent()) / 2;
        canvas.drawText(viewText, -textWidth / 2, baseLineY, paint);

    }
}
