package jack.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import jack.demo.R;
import jack.demo.utils.LogUtils;

/**
 * ================================================
 * description:
 * ================================================
 * package_name: jack.demo.activity
 * author: PayneJay
 * date: 2017/3/15.
 */

public class ObservableListViewTestActivity extends AppCompatActivity implements ObservableScrollViewCallbacks {
    @Bind(R.id.observable_listview)
    ObservableListView observableListview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable_listview_test);
        ButterKnife.bind(this);
        observableListview.setScrollViewCallbacks(this);


        // Add these codes after ListView initialization
        ArrayList<String> items = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            items.add("Item " + i);
        }
        observableListview.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, items));
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        LogUtils.d("scrollY" + scrollY + "firstScroll" + firstScroll + "dragging" + dragging);
    }

    @Override
    public void onDownMotionEvent() {
        LogUtils.d("onDownMotionEvent");
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        ActionBar ab = getSupportActionBar();
        if (scrollState == ScrollState.UP) {
            if (ab.isShowing()) {
                ab.hide();
            }
        } else if (scrollState == ScrollState.DOWN) {
            if (!ab.isShowing()) {
                ab.show();
            }
        }
    }
}
