package jack.demo.holder;

import com.waka.listbase.BaseItemInfo;

import jack.demo.model.Month;

/**
 * ================================================
 * description:
 * ================================================
 * package_name: jack.demo.holder
 * author: PayneJay
 * date: 2018/1/31.
 */

public class ItemInfo {
    public static class MonthBodyItemInfo extends BaseItemInfo<Month> {
        public static final int MONTH_BODY_TYPE = 1;

        @Override
        public int getViewType() {
            return MONTH_BODY_TYPE;
        }
    }
}
