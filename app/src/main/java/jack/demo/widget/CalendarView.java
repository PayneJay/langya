package jack.demo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import jack.demo.model.CustomDate;
import jack.demo.model.Month;
import jack.demo.utils.DateUtils;
import jack.demo.utils.ScreenUtils;

/**
 * ================================================
 * description:
 * ================================================
 * package_name: jack.demo.widget
 * author: PayneJay
 * date: 2018/1/30.
 */

public class CalendarView extends View {
    private static final int TOTAL_COL = 7; // 7列
    /**
     * 单元格宽度
     */
    private float mCellWidth;
    /**
     * 文字距离单元格边框的距离
     */
    private float mPadding = 20;
    /**
     * 日期文字大小
     */
    private int daySize = 40;
    /**
     * 绘制星期的画笔
     */
    private Paint weekPaint;
    /**
     * 绘制日期的画笔
     */
    private Paint dayPaint;
    /**
     * 当天画笔
     */
    private Paint todayPaint;
    /**
     * 单元格画笔
     */
    private Paint cellPaint;

    private int monthDays;
    private int mWeek;
    private CustomDate mCustomDate;
    private Context mContext;

    public CalendarView(Context context) {
        super(context);
        init(context);
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        initPaint();
        mCellWidth = ScreenUtils.getScreenWidth(context) / TOTAL_COL;
    }

    private void initPaint() {
        cellPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        cellPaint.setAntiAlias(true);
        cellPaint.setColor(Color.GRAY);
        cellPaint.setStyle(Paint.Style.STROKE);

        weekPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        weekPaint.setAntiAlias(true);

        dayPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dayPaint.setAntiAlias(true);
        dayPaint.setTextSize(daySize);

        todayPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        todayPaint.setAntiAlias(true);
        todayPaint.setFakeBoldText(true);
        todayPaint.setColor(Color.RED);
        todayPaint.setTextSize(daySize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int cellCount = mWeek + monthDays;
        int row;
        if ((cellCount % TOTAL_COL) == 0) {
            row = cellCount / TOTAL_COL;
        } else {
            row = (cellCount / TOTAL_COL) + 1;
        }

        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize((int) mCellWidth * row, heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float startX;
        for (int i = 0; i < monthDays; i++) {
            startX = mCellWidth * ((i + mWeek) % TOTAL_COL);
            float startY = mCellWidth * ((i + mWeek) / TOTAL_COL);
            drawCell(canvas, startX, startY);
            drawDate(canvas, startX, startY, i + 1);
        }
    }

    public static int getDefaultSize(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }

    public void setMonth(Month month) {
        this.mCustomDate = month.customDate;
        this.mWeek = month.week;
        this.monthDays = month.monthDays;
    }

    /**
     * 绘制日期
     *
     * @param canvas
     * @param startX
     * @param startY
     * @param day
     */
    private void drawDate(Canvas canvas, float startX, float startY, int day) {
        startX = startX + mPadding;
        startY = startY + 3 * mPadding;
        if (DateUtils.getDayOfMonth() == day && DateUtils.isToday(mCustomDate)) {
            canvas.drawText(day + "", startX, startY, todayPaint);
        } else {
            canvas.drawText(day + "", startX, startY, dayPaint);
        }
    }

    /**
     * 绘制单元格
     *
     * @param canvas
     * @param startX
     * @param startY
     */
    private void drawCell(Canvas canvas, float startX, float startY) {
        canvas.drawRect(startX, startY, startX + mCellWidth, startY + mCellWidth, cellPaint);
    }
}
