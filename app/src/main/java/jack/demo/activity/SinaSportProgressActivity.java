package jack.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import jack.demo.R;
import jack.demo.widget.SinaSportLayout;

/**
 * Destriptions:
 * Created by weipengjie on 16/8/2.
 */
public class SinaSportProgressActivity extends Activity{
    private SinaSportLayout mSinaSportLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sina_sport_zan_layout);

        mSinaSportLayout = (SinaSportLayout) findViewById(R.id.layout);
        mSinaSportLayout.setOnSinaSportListener(new SinaSportLayout.OnSinaSportListener() {
            @Override
            public void onLeftClick(TextView tvLeft) {
                mSinaSportLayout.incrementLeftProgressValue(20);
                tvLeft.setText(mSinaSportLayout.getLeftProgressValue() + "");
            }

            @Override
            public void onRightClick(TextView tvRight) {
                mSinaSportLayout.incrementRightProgressValue(20);
                tvRight.setText(mSinaSportLayout.getRightProgressValue() + "");
            }
        });
    }
}
