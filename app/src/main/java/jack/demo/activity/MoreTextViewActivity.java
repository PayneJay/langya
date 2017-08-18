package jack.demo.activity;

import jack.demo.R;
import jack.demo.widget.MoreTextView;

/**
 * Created by weipengjie on 16/7/15.
 */
public class MoreTextViewActivity extends JackBaseActivity {
    private MoreTextView mtvText;

    @Override
    protected void init() {
        mtvText = (MoreTextView) findViewById(R.id.mtv_text);
        mtvText.setText(getString(R.string.full_text));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_more;
    }
}
