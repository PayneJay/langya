package jack.demo.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Destriptions:操作系统和应用层的一个工具类
 * Created by weipengjie on 16/7/20.
 */
public class AppUtils {
    /**
     * 检测Service是否已启动
     * @param serviceClassName
     * @return
     */
    public static boolean isServiceRunning(Context context, String serviceClassName){
        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningServiceInfo> services = activityManager.getRunningServices(Integer.MAX_VALUE);

        for (ActivityManager.RunningServiceInfo runningServiceInfo : services) {
            if (runningServiceInfo.service.getClassName().equals(serviceClassName)){
                return true;
            }
        }
        return false;
    }

    /**
     * 检测某Activity是否在当前Task的栈顶
     * @param context
     * @param cmdName
     * @return
     */
    public static boolean isTopActivy(Context context, String cmdName){
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
        String cmpNameTemp = null;
        if(null != runningTaskInfos){
            cmpNameTemp=(runningTaskInfos.get(0).topActivity).toString();
            Log.e("cmpname","cmpname:"+cmpNameTemp);
        }
        if(null == cmpNameTemp)return false;
        return cmpNameTemp.equals(cmdName);
    }

    /**
     * 判断app是否正在运行
     * @param ctx
     * @param packageName
     * @return
     */
    public static boolean isAppRunning(Context ctx,String packageName) {
        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);

        List<RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();

        if(runningAppProcesses!=null)
        {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {

                if(runningAppProcessInfo.processName.startsWith(packageName))
                {
                    return true;
                }
            }
        }

        return false;
    }
    /**
     * app 是否在后台运行
     * @param ctx
     * @param packageName　
     * @return
     */
    public static boolean isAppRunningBackground(Context ctx,String packageName) {
        ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);

        List<RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();

        if(runningAppProcesses!=null)
        {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {

                if(runningAppProcessInfo.processName.startsWith(packageName))
                {
                    return runningAppProcessInfo.importance!= RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                            && runningAppProcessInfo.importance!= RunningAppProcessInfo.IMPORTANCE_VISIBLE; //排除无界面的app
                }
            }
        }

        return false;
    }

    /**
     * 得到top task
     * @param context
     * @return
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
     * @param context
     * @return
     */
    public static String getRunningActivityName(Context context){
        ActivityManager activityManager=(ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String runningActivity=activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
        return runningActivity;
    }
}
