package jack.demo.holder;

import android.view.View;
import android.widget.TextView;

import com.waka.listbase.BaseHolder;

import jack.demo.R;
import jack.demo.model.Month;
import jack.demo.widget.CalendarView;

/**
 * ================================================
 * description:
 * ================================================
 * package_name: jack.demo.holder
 * author: PayneJay
 * date: 2018/1/31.
 */

public class MonthBodyHolder extends BaseHolder<Month> {
    private CalendarView mCalendarView;
    private TextView mTitleView;

    public MonthBodyHolder(View inflate) {
        super(inflate);
    }

    @Override
    public void onInitView() {
        mTitleView = findViewById(R.id.tv_month_title);
        mCalendarView = findViewById(R.id.calendar_view);
    }

    @Override
    public void onBind(Month month) {
        mTitleView.setText(month.monthText);
        mCalendarView.setMonthDays(month.monthDays);
        mCalendarView.setWeek(month.week);
        mCalendarView.invalidate();
    }
}
