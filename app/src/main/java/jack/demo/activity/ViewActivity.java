package jack.demo.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;

import com.waka.listbase.BaseItemInfo;
import com.waka.view.SwipeRefreshRecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import jack.demo.R;
import jack.demo.adapter.MyCalendarAdapter;
import jack.demo.holder.ItemInfo;
import jack.demo.model.CustomDate;
import jack.demo.model.Month;
import jack.demo.utils.DateUtils;

/**
 * ================================================
 * description:
 * ================================================
 * package_name: jack.demo.activity
 * author: PayneJay
 * date: 2018/1/31.
 */

public class ViewActivity extends JackBaseActivity {
    //展示从当前月份起往后多少个月
    private static final int FROM_MONTH_ON = 20;

    @BindView(R.id.swipe_refresh_recycler_view)
    SwipeRefreshRecyclerView mRecyclerView;

    private List<BaseItemInfo> mData = new ArrayList<>();

    @Override
    protected void init() {
        initData();

        MyCalendarAdapter mAdapter = new MyCalendarAdapter(this);
        mAdapter.setData(mData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setColorSchemeColors(0xFF437845, 0xFFE44F98, 0xFF2FAC21)
                .setDistanceToTriggerSync(300)
                .setSize(SwipeRefreshLayout.DEFAULT)
                .setRefreshAble(false)
                .setLoadMoreAble(false)
                .setItemAnimator(new DefaultItemAnimator())
                .setLayoutManager(linearLayoutManager)
                .setAdapter(mAdapter);
    }

    private void initData() {
        for (int i = 0; i < FROM_MONTH_ON; i++) {
            Month monthBean = new Month();
            int year = DateUtils.getYear() + (i / 12);
            int month = DateUtils.getMonth() + (i % 12);
            Date date = DateUtils.getDateFromString(year, month);
            monthBean.monthText = DateUtils.Date2Str(date);
            monthBean.monthDays = DateUtils.getMonthDays(year, month);
            monthBean.week = DateUtils.getWeekDayFromDate(year, month);
            monthBean.customDate = new CustomDate(year, month, DateUtils.getDayOfMonth());

            ItemInfo.MonthBodyItemInfo bodyItemInfo = new ItemInfo.MonthBodyItemInfo();
            bodyItemInfo.setData(monthBean);
            mData.add(bodyItemInfo);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_general_view;
    }

}
