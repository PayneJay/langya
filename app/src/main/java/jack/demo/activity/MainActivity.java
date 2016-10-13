package jack.demo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
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

import butterknife.ButterKnife;
import butterknife.InjectView;
import jack.demo.R;

public class MainActivity extends Activity {
    @InjectView(R.id.list)
    ListView list;
    private ArrayList<ActivityInfo> mActivities = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
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

    /**
     * 注册app到信鸽推送
     */
    private void registerXGPush() {
        // 开启logcat输出，方便debug，发布时请关闭
         XGPushConfig.enableDebug(this, true);
        // 如果需要知道注册是否成功，请使用registerPush(getApplicationContext(), XGIOperateCallback)带callback版本
        // 如果需要绑定账号，请使用registerPush(getApplicationContext(),account)版本
        // 具体可参考详细的开发指南
        // 传递的参数为ApplicationContext
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

        // 2.36（不包括）之前的版本需要调用以下2行代码
//        Intent service = new Intent(context, XGPushService.class);
//        context.startService(service);

        // 其它常用的API：
        // 绑定账号（别名）注册：registerPush(context,account)或registerPush(context,account, XGIOperateCallback)，其中account为APP账号，可以为任意字符串（qq、openid或任意第三方），业务方一定要注意终端与后台保持一致。
        // 取消绑定账号（别名）：registerPush(context,"*")，即account="*"为取消绑定，解绑后，该针对该账号的推送将失效
        // 反注册（不再接收消息）：unregisterPush(context)
        // 设置标签：setTag(context, tagName)
        // 删除标签：deleteTag(context, tagName)
    }

    /**
     * 更新activity的信息，并更新到每个item
     */
    private void updateActivityInfos() {
        try {
            PackageInfo pi = getPackageManager().getPackageInfo("jack.demo",
                    PackageManager.GET_ACTIVITIES);
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
                        names.add(0, mActivities.get(i).name.toString());
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
