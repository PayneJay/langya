package jack.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushManager;

import butterknife.BindView;
import butterknife.OnClick;
import jack.demo.activity.ProgressViewActivity;

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

    private FrameLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        initView();
    }

    private void initView() {
        rootView = (FrameLayout) findViewById(R.id.activity_content_layout);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        View mView = LayoutInflater.from(this).inflate(getContentView(), null);
        rootView.addView(mView, params);

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
}
