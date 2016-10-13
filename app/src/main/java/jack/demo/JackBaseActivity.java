package jack.demo;

import android.app.Activity;
import android.content.Intent;

import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushManager;

import jack.demo.activity.ProgressViewActivity;

/**
 * Destriptions:Activity基类
 * Created by weipengjie on 2016/10/13.
 */

public class JackBaseActivity extends Activity {

    @Override
    protected void onResume() {
        super.onResume();
        XGPushClickedResult click = XGPushManager.onActivityStarted(this);
        if (click != null) { // 判断是否来自信鸽的打开方式
            startActivity(new Intent(this, ProgressViewActivity.class));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        XGPushManager.onActivityStoped(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }
}
