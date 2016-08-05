package jack.demo.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.List;

/**
 * Destriptions:App相关辅助类
 * Created by weipengjie on 16/7/20.
 */
public class AppUtils {

    private AppUtils() {
        /**cannot be instantiated **/
        throw new UnsupportedOperationException("cannot be instantiated");

    }

    /**
     * 检测Service是否已启动
     *
     * @param serviceClassName 服务名称
     * @return boolean值
     */
    public static boolean isServiceRunning(Context context, String serviceClassName) {
        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningServiceInfo> services = activityManager.getRunningServices(Integer.MAX_VALUE);

        for (ActivityManager.RunningServiceInfo runningServiceInfo : services) {
            if (runningServiceInfo.service.getClassName().equals(serviceClassName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测某Activity是否在当前Task的栈顶
     *
     * @param context 上下文
     * @param cmdName Activity名称
     * @return boolean值
     */
    public static boolean isTopActivy(Context context, String cmdName) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
        String cmpNameTemp = null;
        if (null != runningTaskInfos) {
            cmpNameTemp = (runningTaskInfos.get(0).topActivity).toString();
            Log.e("cmpname", "cmpname:" + cmpNameTemp);
        }
        if (null == cmpNameTemp) return false;
        return cmpNameTemp.equals(cmdName);
    }

    /**
     * 判断app是否正在运行
     *
     * @param ctx 上下文
     * @param packageName 应用程序包名
     * @return boolean值
     */
    public static boolean isAppRunning(Context ctx, String packageName) {
        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);

        List<RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();

        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {

                if (runningAppProcessInfo.processName.startsWith(packageName)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * app 是否在后台运行
     *
     * @param ctx 上下文
     * @param packageName 应用程序包名
     * @return 是否在后台运行
     */
    public static boolean isAppRunningBackground(Context ctx, String packageName) {
        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);

        List<RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();

        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {

                if (runningAppProcessInfo.processName.startsWith(packageName)) {
                    return runningAppProcessInfo.importance != RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                            && runningAppProcessInfo.importance != RunningAppProcessInfo.IMPORTANCE_VISIBLE; //排除无界面的app
                }
            }
        }

        return false;
    }

    /**
     * 得到top task
     *
     * @param context 上下文
     * @return 栈顶任务
     */
    public static RunningTaskInfo getTopTaskInfo(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
        if (runningTaskInfos != null && !runningTaskInfos.isEmpty()) {
            return runningTaskInfos.get(0);
        }
        return null;
    }

    /**
     * 获取当前Activity的名称
     *
     * @param context 上下文
     * @return 当前Activity的名称
     */
    public static String getRunningActivityName(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String runningActivity = activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
        return runningActivity;
    }

    /**
     * 获取应用程序名称
     * @param context 上下文
     * @return 应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context 上下文
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
