package jack.demo.activity;

import android.app.Activity;
import android.os.Bundle;

import jack.demo.R;
import jack.demo.widget.MoreTextView;

/**
 * Created by weipengjie on 16/7/15.
 */
public class MoreTextViewActivity extends Activity {
    private MoreTextView mtvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        mtvText = (MoreTextView) findViewById(R.id.mtv_text);
        mtvText.setText(getString(R.string.full_text));
    }
}
