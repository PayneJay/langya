package jack.demo.activity;

import android.widget.TextView;

import jack.demo.JackBaseActivity;
import jack.demo.R;
import jack.demo.widget.SinaSportLayout;
import jack.demo.widget.SinaSportLayout.OnSinaSportListener;

import static java.lang.String.format;

/**
 * Destriptions:
 * Created by weipengjie on 16/8/2.
 */
public class SinaSportProgressActivity extends JackBaseActivity {
    private SinaSportLayout mSinaSportLayout;

    @Override
    protected void init() {
        mSinaSportLayout = (SinaSportLayout) findViewById(R.id.layout);
        mSinaSportLayout.setOnSinaSportListener(new OnSinaSportListener() {
            @Override
            public void onLeftClick(TextView tvLeft) {
                mSinaSportLayout.incrementLeftProgressValue(20);
                tvLeft.setText(format("%d", mSinaSportLayout.getLeftProgressValue()));
            }

            @Override
            public void onRightClick(TextView tvRight) {
                mSinaSportLayout.incrementRightProgressValue(20);
                tvRight.setText(format("%d", mSinaSportLayout.getRightProgressValue()));
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_sina_sport_zan_layout;
    }
}
