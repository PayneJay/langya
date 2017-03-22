package jack.demo.activity;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import butterknife.BindView;
import jack.demo.JackBaseActivity;
import jack.demo.R;

/**
 * ================================================
 * description:这是一个扑克牌翻转效果的实现类
 * ================================================
 * package_name: jack.demo.activity
 * author: weipengjie
 * date: 2016/12/11.
 */

public class PokerOverturnActivity extends JackBaseActivity {

    @BindView(R.id.iv_poker)
    ImageView ivPoker;
    private boolean bool;

    @Override
    protected void init() {
        ivPoker.setOnClickListener(new ImgViewListener());
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_poker_overturn;
    }

    class ImgViewListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

            //通过AnimationUtils得到动画配置文件(/res/anim/back_scale.xml)
            Animation animation = AnimationUtils.loadAnimation(PokerOverturnActivity.this, R.anim.back_scale);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (bool) {
                        ivPoker.setImageResource(R.drawable.image_front);
                        bool = false;
                    } else {
                        ivPoker.setImageResource(R.drawable.image_reverse);
                        bool = true;
                    }
                    //通过AnimationUtils得到动画配置文件(/res/anim/front_scale.xml),然后在把动画交给ImageView
                    ivPoker.startAnimation(AnimationUtils.loadAnimation(PokerOverturnActivity.this, R.anim.front_scale));
                }
            });
            animation.setDuration(500);
            ivPoker.startAnimation(animation);
        }
    }
}

