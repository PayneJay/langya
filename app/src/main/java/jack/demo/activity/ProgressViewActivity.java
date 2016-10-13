package jack.demo.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import jack.demo.JackBaseActivity;
import jack.demo.R;
import jack.demo.utils.ProgressHandler;
import jack.demo.utils.ProgressHandler.Progress;
import jack.demo.widget.ProgressBarView;

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

    @InjectView(R.id.btn)
    Button btnDownload;
    @InjectView(R.id.view)
    ProgressBarView progressBarView;
    @InjectView(R.id.view1)
    ProgressBarView progressBarView1;
    @InjectView(R.id.view2)
    ProgressBarView progressBarView2;
    @InjectView(R.id.img_download_status)
    ImageView imgDownLoad;

    private ProgressHandler progressHandler = new ProgressHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        ButterKnife.inject(this);

        assert btnDownload != null;
        assert progressBarView != null;
        assert progressBarView1 != null;
        assert progressBarView2 != null;

        progressBarView.setPercent(false);
        progressBarView.setStyle(FILL);

        progressBarView1.setPercent(true);
        progressBarView1.setStyle(STROKE);
        progressBarView1.setTextColor(getResources().getColor(R.color.colorPrimary));


        progressBarView2.setPercent(false);
        progressBarView2.setStyle(STROKE);

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

    @OnClick(R.id.btn)
    public void onClick() {
        progressHandler.sendEmptyMessageDelayed(UPDATE, TIME);
        imgDownLoad.setImageResource(R.drawable.icon_down);
    }


}
