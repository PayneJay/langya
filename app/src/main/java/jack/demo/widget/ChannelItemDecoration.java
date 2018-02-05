package jack.demo.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * ================================================
 * description:
 * ================================================
 * package_name: jack.demo.widget
 * author: PayneJay
 * date: 2018/2/2.
 */

public class ChannelItemDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //设定底部边距为1px
        outRect.set(0, 0, 0, 1);
    }
}
