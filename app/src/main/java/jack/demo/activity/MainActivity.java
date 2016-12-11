package jack.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import jack.demo.JackBaseActivity;
import jack.demo.R;

import static android.content.pm.PackageManager.GET_ACTIVITIES;

public class MainActivity extends JackBaseActivity {
    @Bind(R.id.list)
    ListView list;
    private ArrayList<ActivityInfo> mActivities = null;

    @Override
    protected void init() {
        ivTopBack.setVisibility(View.INVISIBLE);
        registerXGPush();
        updateActivityInfos();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClassName(MainActivity.this, mActivities.get(position).name);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    /**
     * 注册app到信鸽推送
     */
    private void registerXGPush() {
        // 开启logcat输出，方便debug，发布时请关闭
        XGPushConfig.enableDebug(this, true);
        Context context = getApplicationContext();
        XGPushManager.registerPush(context, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
                Log.d("TPush", "注册成功，设备token为：" + data);
            }

            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }
        });
    }

    /**
     * 更新activity的信息，并更新到每个item
     */
    private void updateActivityInfos() {
        try {
            PackageInfo pi = getPackageManager().getPackageInfo("jack.demo",
                    GET_ACTIVITIES);
            // 获取所有Activity信息
            mActivities = new ArrayList<>(
                    Arrays.asList(pi.activities));
            //获取本类的名字
            String ourName = getClass().getName();
            //获取Activity的名字
            ArrayList<String> names = new ArrayList<>();

            for (int i = mActivities.size() - 1; i > -1; i--) {
                if (mActivities.get(i).name.equals(ourName)) {
                    mActivities.remove(i);
                } else {
                    if (mActivities.get(i).name != null) {
                        names.add(0, mActivities.get(i).name);
                    } else {
                        mActivities.remove(i);
                    }
                }
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
            adapter.addAll(names);

            list.setAdapter(adapter);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
