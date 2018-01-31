package jack.demo.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.waka.listbase.BaseHolder;
import com.waka.listbase.RefreshBaseAdapter;

import jack.demo.R;
import jack.demo.holder.ItemInfo;
import jack.demo.holder.MonthBodyHolder;

/**
 * ================================================
 * description:
 * ================================================
 * package_name: jack.demo.adapter
 * author: PayneJay
 * date: 2018/1/31.
 */

public class MyCalendarAdapter extends RefreshBaseAdapter {
    public MyCalendarAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate;
        BaseHolder holder;
        switch (viewType) {
            case ItemInfo.MonthBodyItemInfo.MONTH_BODY_TYPE:
                inflate = this.inflate.inflate(R.layout.layout_month_item, parent, false);
                holder = new MonthBodyHolder(inflate);
                break;
            default:
                holder = null;
        }
        return holder;
    }
}
