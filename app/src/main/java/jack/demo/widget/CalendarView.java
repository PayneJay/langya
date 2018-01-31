package jack.demo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

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
        todayPaint.setColor(Color.RED);
        todayPaint.setTextSize(daySize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        int currentMonthDays = DateUtils.getCurrentMonthDay();

//        int week = DateUtils.getWeekOfMonth();
//        float startX = mCellWidth * mWeek;
        for (int i = 1; i <= monthDays; i++) {
            float startX = mCellWidth * (i % TOTAL_COL);
            float startY = mCellWidth * (i / TOTAL_COL);
            drawCell(canvas, startX, startY);
            drawDate(canvas, startX, startY, i);
        }
    }

    public void setMonthDays(int monthDays) {
        this.monthDays = monthDays;
    }

    public void setWeek(int mWeek) {
        this.mWeek = mWeek;
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
        int dayOfMonth = DateUtils.getDayOfMonth();
        if (dayOfMonth == day) {
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
