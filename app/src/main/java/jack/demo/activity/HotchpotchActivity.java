package jack.demo.activity;

import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import jack.demo.JackBaseActivity;
import jack.demo.R;

/**
 * Destriptions:这是一个Google推出的TextInputLayout 组件的测试类
 * 详见：http://www.tuicool.com/articles/rQVVJfj
 * Created by weipengjie on 2016/11/14.
 */

public class HotchpotchActivity extends JackBaseActivity {
    private TextInputEditText mNameEditx;
    private TextInputEditText mPhoneEditx;
    private TextInputEditText mEmailEditx;
    private TextInputEditText mPasswordEditx;
    private TextInputEditText mFeedbackEditx;

    private String mContentName;
    private String mContentPhone;
    private String mContentEmail;
    private String mContentPassword;
    private String mContentFeedback;

    @Override
    protected void init() {
        mNameEditx = (TextInputEditText) findViewById(R.id.edit_name);
        mPhoneEditx = (TextInputEditText) findViewById(R.id.edit_phone);
        mEmailEditx = (TextInputEditText) findViewById(R.id.edit_email);
        mPasswordEditx = (TextInputEditText) findViewById(R.id.edit_password);
        mFeedbackEditx = (TextInputEditText) findViewById(R.id.edit_feedback);

        Button submitBtn = (Button) findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlAction();
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_textinput_layout;
    }

    private void getContent() {
        mContentName = mNameEditx.getText().toString();
        mContentPhone = mPhoneEditx.getText().toString();
        mContentEmail = mEmailEditx.getText().toString();
        mContentPassword = mPasswordEditx.getText().toString();
        mContentFeedback = mFeedbackEditx.getText().toString();
    }

    private void controlAction() {
        getContent();

        if (mContentName.length() == 0) {
            showToast("姓名不能为空!");
        } else if (mContentPhone.length() != 11) {
            showToast("请正确填写11位手机号码!");
        } else if (mContentEmail.length() == 0 || !android.util.Patterns.EMAIL_ADDRESS.matcher(mContentEmail).matches()) {
            showToast("请正确填写邮箱地址!");
        } else if (mContentPassword.length() == 0) {
            showToast("密码不能为空");
        } else if (mContentFeedback.length() == 0) {
            showToast("反馈意见不能为空!");
        } else if (mContentFeedback.length() > 10) {
            showToast("反馈意见长度字数不能大于10!");
        } else {
            doSubmission();
        }
    }

    private void doSubmission() {
        showToast("恭喜您,数据正确!");
    }


    private void showToast(String message) {
        Toast.makeText(HotchpotchActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
