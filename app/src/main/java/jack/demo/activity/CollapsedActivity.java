package jack.demo.activity;

import butterknife.BindView;
import jack.demo.R;
import jack.demo.widget.CollapsedTextView;

/**
 * Created by weipengjie on 16/7/15.
 */
public class CollapsedActivity extends JackBaseActivity {
    @BindView(R.id.ctv_view)
    CollapsedTextView ctvView;

    @Override
    protected void init() {
        ctvView.setShowText(getString(R.string.full_text));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_collapsed;
    }
}
