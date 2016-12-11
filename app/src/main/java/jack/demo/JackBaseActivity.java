package jack.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushManager;

import jack.demo.activity.ProgressViewActivity;

/**
 * Destriptions:Activity基类
 * Created by weipengjie on 2016/10/13.
 */

public class JackBaseActivity extends Activity implements View.OnClickListener {
    ImageView ivTopBack;
    TextView tvTopTitle;
    ImageView ivTopSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        initView();
    }

    private void initView() {
        ivTopBack = (ImageView) findViewById(R.id.iv_top_back);
        tvTopTitle = (TextView) findViewById(R.id.tv_top_title);
        ivTopSave = (ImageView) findViewById(R.id.iv_top_save);
        ivTopBack.setOnClickListener(this);
        tvTopTitle.setOnClickListener(this);
        ivTopSave.setOnClickListener(this);
    }

    /**
     * 点击左上角返回
     */
    protected void topLeftBack() {
        finish();
    }

    /**
     * 点击右上角保存
     */
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_top_back:
                topLeftBack();
                break;
            case R.id.iv_top_save:
                topRightSave();
                break;
        }
    }
}
