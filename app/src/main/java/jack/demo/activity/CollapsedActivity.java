package jack.demo.activity;

import android.os.Bundle;

import jack.demo.JackBaseActivity;
import jack.demo.R;
import jack.demo.widget.CollapsedTextView;

/**
 * Created by weipengjie on 16/7/15.
 */
public class CollapsedActivity extends JackBaseActivity {
    private CollapsedTextView ctvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsed);
        ctvView = (CollapsedTextView) findViewById(R.id.ctv_view);
        ctvView.setShowText(getString(R.string.full_text));

    }
}
