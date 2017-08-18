package jack.demo.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jack.demo.R;
import jack.demo.constant.JackConstant;
import jack.demo.utils.ToastUtils;

/**
 * Destriptions:Activity基类
 * Created by weipengjie on 2016/10/13.
 */

public abstract class JackBaseActivity extends Activity {
    @BindView(R.id.iv_top_back)
    protected ImageView ivTopBack;
    @BindView(R.id.tv_top_title)
    protected TextView tvTopTitle;
    @BindView(R.id.iv_top_save)
    protected ImageView ivTopSave;

    protected boolean isExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        initView();
    }

    private void initView() {
        FrameLayout rootView = (FrameLayout) findViewById(R.id.activity_content_layout);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        View mView = LayoutInflater.from(this).inflate(getContentView(), null);
        rootView.addView(mView, params);
        ButterKnife.bind(this);

        init();
    }

    protected abstract void init();

    /**
     * @return 当前activity的layoutID
     */
    protected abstract int getContentView();

    /**
     * 点击左上角返回
     */
    @OnClick(R.id.iv_top_back)
    protected void topLeftBack() {
        finish();
    }

    /**
     * 点击右上角保存
     */
    @OnClick(R.id.iv_top_save)
    protected void topRightSave() {

    }

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

    /**
     * double quite the app
     */
    public void exitByDoubleClick() {
        if (!isExit) {
            isExit = true;
            ToastUtils.showShort(this, "再按一下退出！");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            Intent intent = new Intent(JackConstant.Activity.ROOT_FILTER);
            intent.putExtra(JackConstant.Activity.ROOT_UPDATE,
                    JackConstant.Activity.ROOT_CLOSE_APP);
            sendBroadcast(intent);
            ActivityManager acManager = (ActivityManager) this
                    .getSystemService(ACTIVITY_SERVICE);
            acManager.killBackgroundProcesses(getPackageName());
            finish();
        }
    }
}
