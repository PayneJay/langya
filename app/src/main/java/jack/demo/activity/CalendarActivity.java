package jack.demo.activity;

import android.support.v4.view.ViewPager;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import jack.demo.JackBaseActivity;
import jack.demo.R;
import jack.demo.adapter.CalendarViewAdapter;
import jack.demo.model.CustomDate;
import jack.demo.widget.CalendarCard;
import jack.demo.widget.CalendarCard.OnCellClickListener;

import static jack.demo.activity.CalendarActivity.SildeDirection.NO_SILDE;

/**
 * Destriptions:
 * Created by weipengjie on 16/8/2.
 */
public class CalendarActivity extends JackBaseActivity implements OnCellClickListener {
    @Bind(R.id.vp_calendar)
    ViewPager mViewPager;
    @Bind(R.id.tvCurrentMonth)
    TextView monthText;
    @Bind(R.id.btnPreMonth)
    ImageButton preImgBtn;
    @Bind(R.id.btnNextMonth)
    ImageButton nextImgBtn;

    private CalendarViewAdapter<CalendarCard> adapter;
    private SildeDirection mDirection = NO_SILDE;
    private int mCurrentIndex = 498;

    enum SildeDirection {
        RIGHT, LEFT, NO_SILDE;
    }

    @Override
    protected void init() {
        CalendarCard[] views = new CalendarCard[3];
        for (int i = 0; i < 3; i++) {
            views[i] = new CalendarCard(this, this);
        }
        adapter = new CalendarViewAdapter<>(views);
        setViewPager();
    }

    @OnClick(R.id.btnPreMonth)
    void onPreMonthClicked() {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
    }

    @OnClick(R.id.btnNextMonth)
    void onNextMonthClicked() {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_calendar_layout;
    }

    private void setViewPager() {
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(498);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                measureDirection(position);
                updateCalendarView(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    @Override
    public void clickDate(CustomDate date) {

    }

    @Override
    public void changeDate(CustomDate date) {
        monthText.setText(date.month + "月");
    }

    /**
     * 计算方向
     *
     * @param arg0
     */
    private void measureDirection(int arg0) {

        if (arg0 > mCurrentIndex) {
            mDirection = SildeDirection.RIGHT;

        } else if (arg0 < mCurrentIndex) {
            mDirection = SildeDirection.LEFT;
        }
        mCurrentIndex = arg0;
    }

    // 更新日历视图
    private void updateCalendarView(int arg0) {
        CalendarCard[] mShowViews = adapter.getAllItems();
        if (mDirection == SildeDirection.RIGHT) {
            mShowViews[arg0 % mShowViews.length].rightSlide();
        } else if (mDirection == SildeDirection.LEFT) {
            mShowViews[arg0 % mShowViews.length].leftSlide();
        }
        mDirection = NO_SILDE;
    }

}
