package jack.demo.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import jack.demo.R;
import jack.demo.widget.FlowLayout;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.Toast.LENGTH_SHORT;

/**
 * Destriptions:
 * Created by weipengjie on 16/8/2.
 */
@SuppressWarnings("ResourceType")
public class FlowLayoutActivity extends JackBaseActivity {

    //标签名称
    private String mTvNames[] = {"动漫", "钉宫理惠", "灼眼的夏娜", "绯弹的亚里亚", "零之使魔", "夕阳染红的街道"};
    //流式布局
    @BindView(R.id.flow_layout)
    FlowLayout mFlowLayout;
    //接收新添加TextView的名称
    @BindView(R.id.edit)
    EditText mEdit;

    @Override
    protected void init() {
        //遍历标签名称数组
        for (String s : mTvNames) {
            addTextView(s);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_flow;
    }

    @OnClick(R.id.btn)
    void onBtnClicked() {
        String s = mEdit.getText().toString();
        //清空输入框
        mEdit.setText("");
        addTextView(s);
    }

    public void addTextView(final String tvName) {
        //加载TextView并设置名称，并设置名称
        TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.text_view, mFlowLayout, false);
        MarginLayoutParams params = new MarginLayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        params.setMargins(0, 0, 20, 20);
        tv.setLayoutParams(params);
        tv.setPadding(10, 5, 10, 5);
        tv.setBackgroundResource(R.drawable.button_style);
        tv.setText(tvName);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FlowLayoutActivity.this, tvName, LENGTH_SHORT).show();
            }
        });
        //把TextView加入流式布局
        mFlowLayout.addView(tv);
    }
}
