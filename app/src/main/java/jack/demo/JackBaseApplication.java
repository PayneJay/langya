package jack.demo;

import android.app.Application;

import jack.demo.utils.CommonUtils;

/**
 * ================================================
 * description:
 * ================================================
 * package_name: jack.demo
 * author: PayneJay
 * date: 2017/5/27.
 */

public class JackBaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CommonUtils.application = this;
    }
}
