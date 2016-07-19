package jack.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
