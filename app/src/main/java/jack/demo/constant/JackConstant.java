package jack.demo.constant;

import android.graphics.Color;

/**
 * Destriptions:常量接口
 * Created by weipengjie on 16/7/19.
 */
public interface JackConstant {
    /**
     * 自定义加载进度常量定义
     */
    interface ProgressDefaults{
        /**
         * 判断数据的最小值
         */
        int MIN = 1;

        /**
         * 最大进度
         */
        int PROGRESS_BAR_MAX = 100;

        /**
         * 默认进度
         */
        int CURRENT_PROGRESS = 0;

        /**
         * 默认圆环宽度
         */
        float CIRCLES_WIDTH = 5;
        /**
         * 默认进度宽度
         */
        float CURRENT_SCHEDULE_WIDTH = 6;

        /**
         * 默认圆环颜色
         */
        int CIRCLES_COLOR = Color.GRAY;

        /**
         * 默认字体的粗细程度
         */
        float TEXT_CRUDE = 0;

        /**
         * 默认字体颜色
         */
        int TEXT_COLOR = Color.GRAY;

        /**
         * 默认字体大小
         */
        float TEXT_SIZE = 25;

        /**
         * 默认进度的颜色
         */
        int CURRENT_PROGRESS_COLOR = Color.YELLOW;

        /**
         * 是否显示百分比
         */
        boolean IS_PERCENT = true;

        /**
         * 默认风格
         */
        int STYLE = 1;
    }
}
