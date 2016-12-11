package jack.demo.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jack.demo.JackBaseActivity;
import jack.demo.R;
import jack.demo.utils.ProgressHandler;
import jack.demo.utils.ProgressHandler.Progress;
import jack.demo.utils.ToastUtils;
import jack.demo.widget.ProgressBarView;
import jack.demo.widget.RemoteControlMenu;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;
import static jack.demo.utils.ProgressHandler.TIME;
import static jack.demo.utils.ProgressHandler.UPDATE;
import static jack.demo.widget.ProgressBarView.FILL;
import static jack.demo.widget.ProgressBarView.STROKE;

/**
 * Destriptions:自定义 view 实现下载进度
 * Created by weipengjie on 16/7/19.
 */
public class ProgressViewActivity extends JackBaseActivity {

    @Bind(R.id.btn)
    Button btnDownload;
    @Bind(R.id.view)
    ProgressBarView progressBarView;
    @Bind(R.id.view1)
    ProgressBarView progressBarView1;
    @Bind(R.id.view2)
    ProgressBarView progressBarView2;
    @Bind(R.id.img_download_status)
    ImageView imgDownLoad;
    @Bind(R.id.rcMenu)
    RemoteControlMenu rcMenu;

    private ProgressHandler progressHandler = new ProgressHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        ButterKnife.bind(this);

        assert btnDownload != null;
        assert progressBarView != null;
        assert progressBarView1 != null;
        assert progressBarView2 != null;

        initView();

        progressHandler.setProgress(new Progress() {
            @Override
            public void setSchedule(int schedule) {
                progressBarView.setCurrentProgress(schedule);
                progressBarView.setCurrentProgressColor(getResources().getColor(R.color.colorAccent));
                progressBarView1.setCurrentProgress(schedule);
                progressBarView1.setCurrentProgressColor(getResources().getColor(R.color.colorPrimary));
                progressBarView2.setCurrentProgress(schedule);
            }

            @Override
            public void onSuccess() {
                imgDownLoad.setImageResource(R.drawable.icon_ok);
                makeText(ProgressViewActivity.this, getString(R.string.download_success), LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        progressBarView.setPercent(false);
        progressBarView.setStyle(FILL);

        progressBarView1.setPercent(true);
        progressBarView1.setStyle(STROKE);
        progressBarView1.setTextColor(getResources().getColor(R.color.colorPrimary));


        progressBarView2.setPercent(false);
        progressBarView2.setStyle(STROKE);

        RemoteControlMenu.MenuListener menuListener = new RemoteControlMenu.MenuListener() {
            @Override
            public void onCenterClicked() {
                // TODO: 2016/11/16 点击中间菜单
                ToastUtils.showShort(ProgressViewActivity.this, "菜单中间");
            }

            @Override
            public void onUpClicked() {
                // TODO: 2016/11/16 点击菜单上
                ToastUtils.showShort(ProgressViewActivity.this, "菜单上");
            }

            @Override
            public void onRightClicked() {
                // TODO: 2016/11/16 点击右菜单
                ToastUtils.showShort(ProgressViewActivity.this, "菜单右");
            }

            @Override
            public void onDownClicked() {
                // TODO: 2016/11/16 点击菜单下
                ToastUtils.showShort(ProgressViewActivity.this, "菜单下");
            }

            @Override
            public void onLeftClicked() {
                // TODO: 2016/11/16 点击左侧菜单
                ToastUtils.showShort(ProgressViewActivity.this, "菜单左");
            }
        };
        rcMenu.setListener(menuListener);
    }

    @OnClick(R.id.btn)
    public void onClick() {
        progressHandler.sendEmptyMessageDelayed(UPDATE, TIME);
        imgDownLoad.setImageResource(R.drawable.icon_down);
    }

}
