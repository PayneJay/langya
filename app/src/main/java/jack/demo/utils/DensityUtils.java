package jack.demo.utils;

import android.content.Context;

/**
 * Destriptions:
 * Created by weipengjie on 16/8/2.
 */
public class DensityUtils {
    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param ctx
     * @param pxValue
     * @return
     */
    public static int px2dip(Context ctx, float pxValue) {
        float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
