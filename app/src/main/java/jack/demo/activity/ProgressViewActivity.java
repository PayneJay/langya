package jack.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import jack.demo.R;
import jack.demo.utils.ProgressHandler;
import jack.demo.widget.ProgressBarView;

/**
 * Destriptions:自定义 view 实现下载进度
 * Created by weipengjie on 16/7/19.
 */
public class ProgressViewActivity extends Activity {

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
        progressBarView.setStyle(ProgressBarView.FILL);

        progressBarView1.setPercent(true);
        progressBarView1.setStyle(ProgressBarView.STROKE);
        progressBarView1.setTextColor(getResources().getColor(R.color.colorPrimary));


        progressBarView2.setPercent(false);
        progressBarView2.setStyle(ProgressBarView.STROKE);

        progressHandler.setProgress(new ProgressHandler.Progress() {
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
                Toast.makeText(ProgressViewActivity.this, getString(R.string.download_success), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btn)
    public void onClick() {
        progressHandler.sendEmptyMessageDelayed(ProgressHandler.UPDATE, ProgressHandler.TIME);
        imgDownLoad.setImageResource(R.drawable.icon_down);
    }


}
