package jack.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import jack.demo.R;
import jack.demo.widget.FlowLayout;

/**
 * Destriptions:
 * Created by weipengjie on 16/8/2.
 */
public class FlowLayoutActivity extends Activity {

    //标签名称
    private String mTvNames[] = {"动漫", "钉宫理惠", "灼眼的夏娜", "绯弹的亚里亚", "零之使魔", "夕阳染红的街道"};
    //流式布局
    private FlowLayout mFlowLayout;
    //用于动态添加TextView到流式布局的按钮
    private Button mBtn;
    //接收新添加TextView的名称
    private EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);

        mFlowLayout = (FlowLayout) findViewById(R.id.flow_layout);
        init();

        mEdit = (EditText) findViewById(R.id.edit);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mEdit.getText().toString();
                //清空输入框
                mEdit.setText("");
                addTextView(s);
            }
        });
    }

    public void init() {
        //遍历标签名称数组
        for (String s : mTvNames) {
            addTextView(s);
        }
    }

    public void addTextView(final String tvName) {
        //加载TextView并设置名称，并设置名称
        TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.text_view, mFlowLayout, false);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 20, 20);
        tv.setLayoutParams(params);
        tv.setBackgroundResource(R.drawable.button_style);
        tv.setText(tvName);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FlowLayoutActivity.this, tvName, Toast.LENGTH_SHORT).show();
            }
        });
        //把TextView加入流式布局
        mFlowLayout.addView(tv);
    }
}
