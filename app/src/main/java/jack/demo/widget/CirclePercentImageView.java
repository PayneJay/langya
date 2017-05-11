package jack.demo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import jack.demo.R;

/**
 * ================================================
 * description:
 * ================================================
 * package_name: jack.demo.widget
 * author: PayneJay
 * date: 2017/5/5.
 */

@SuppressLint("AppCompatCustomView")
public class CirclePercentImageView extends ImageView {
    /**
     * 是否使用进度模式
     */
    private boolean useProgress;
    /**
     * 背景阴影颜色
     */
    private int shadowColor;
    /**
     * 最大进度
     */
    private int progressMax;
    /**
     * 当前进度
     */
    private int progress;
    /**
     * 背景画笔
     */
    private Paint paint;
    /**
     * 背景宽度
     */
    private int mWidth;
    /**
     * 背景高度
     */
    private int mHeight;
    /**
     * 半径
     */
    private int mRadius;

    public CirclePercentImageView(Context context) {
        super(context);
        init(context, null);
    }

    public CirclePercentImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray t = getResources().obtainAttributes(attrs, R.styleable.ProgressImageView);
            useProgress = t.getBoolean(R.styleable.ProgressImageView_useProgress, false);
            shadowColor = t.getColor(R.styleable.ProgressImageView_roundShadowColor, Color.parseColor("#a6292929"));
            progressMax = t.getInteger(R.styleable.ProgressImageView_progressMax, 100);

            t.recycle();
        } else {
            shadowColor = Color.parseColor("#a6292929");
        }

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!useProgress) {
            return;
        }

        if (progress == 0) {
            paint.setColor(shadowColor);
            canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

            mWidth = getWidth() / 2;
            mHeight = getHeight() / 2;
            mRadius = 2 * mWidth / 3;
            paint.setColor(shadowColor);
            canvas.drawCircle(mWidth, mHeight, mRadius, paint);
        }

        paint.setColor(Color.GREEN);
        RectF rect = new RectF(0, 0, mWidth, mHeight);
        canvas.drawArc(rect, 0, 360 * progress / progressMax, true, paint);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        postInvalidate();
    }
}
