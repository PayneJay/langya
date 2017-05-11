package jack.demo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * ================================================
 * description:带上传菊花的ImageView
 * ================================================
 * package_name: jack.demo.widget
 * author: PayneJay
 * date: 2017/5/5.
 */

@SuppressLint("AppCompatCustomView")
public class ProgressImageView extends ImageView {
    //loading宽度
    private int mWidth;
    //loading高度
    private int mHeight;
    //可用宽度（loading的大小不能比图片本身大）
    private int mEnableLength;
    //loading条的间距
    private int mLoadingPadding;
    //loading条宽度
    private int mWidthRect;

    private int pos = 0;
    private boolean mShowing = false;
    private boolean mDrawError = false;
    private Paint mPaint;
    private Rect mRect;
    private String[] color = {"#939393", "#939393", "#939393", "#6ad6e1", "#6ad6e1", "#6ad6e1"};
    private Handler mHandler = new Handler();
    private Bitmap mErrorBitmap;

    public ProgressImageView(Context context) {
        super(context);
        initPaint();
    }

    public ProgressImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public ProgressImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mShowing) {
            drawLoading(canvas);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    invalidate();
                }
            }, 100);
        }
        if (mDrawError && mErrorBitmap != null) {
            canvas.drawBitmap(mErrorBitmap, getMeasuredWidth() / 2 - mErrorBitmap.getWidth() / 2,
                    getMeasuredHeight() / 2 - mErrorBitmap.getHeight() / 2, mPaint);
        }
    }

    /**
     * 展示菊花的方法
     */
    public void showLoading(int width, int height) {
        mWidth = width;
        mHeight = height;
        mEnableLength = Math.min(mWidth, mHeight);
        int max = 200;
        if (mEnableLength > max) {
            mEnableLength = max;
        }

        int mHeightRect = mEnableLength / 6;
        mWidthRect = mHeightRect / 4;
        mLoadingPadding = mEnableLength / 6;
        mShowing = true;
        invalidate();
    }

    /**
     * 隐藏菊花
     */
    public void hideLoading() {
        mShowing = false;
        invalidate();
        mHandler.removeCallbacksAndMessages(null);
    }


    public void drawLoading(Canvas canvas) {
        if (mRect == null) {
            mRect = new Rect((mWidth - mWidthRect) / 2, mHeight / 2 - mEnableLength / 2 +
                    mLoadingPadding, (mWidth + mWidthRect) /
                    2, mHeight / 2 - mEnableLength / 6);
        }

        for (int i = 0; i < 12; i++) {
            if (i - pos >= 5) {
                mPaint.setColor(Color.parseColor(color[5]));
            } else if (i - pos >= 0 && i - pos < 5) {
                mPaint.setColor(Color.parseColor(color[i - pos]));
            } else if (i - pos >= -7 && i - pos < 0) {
                mPaint.setColor(Color.parseColor(color[5]));
            } else if (i - pos >= -11 && i - pos < -7) {
                mPaint.setColor(Color.parseColor(color[12 + i - pos]));
            }

            canvas.drawRect(mRect, mPaint);  //绘制
            canvas.rotate(30, mWidth / 2, mWidth / 2);    //旋转
        }

        pos++;
        if (pos > 11) {
            pos = 0;
        }
    }

    public void drawErrorBitmap(Bitmap bitmap) {
        this.mErrorBitmap = bitmap;
        mDrawError = true;
        invalidate();
    }

    public void dissmissErrorBitmap() {
        mDrawError = false;
        invalidate();
    }

    public boolean ismShowing() {
        return mShowing;
    }

    public void setmShowing(boolean mShowing) {
        this.mShowing = mShowing;
    }
}
