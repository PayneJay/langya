package jack.demo.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import jack.demo.R;
import jack.demo.utils.SPUtils;
import jack.demo.utils.ToastUtils;

import static jack.demo.constant.JackConstant.LoginInfo.PASSWORD;
import static jack.demo.constant.JackConstant.LoginInfo.USER_NAME;

/**
 * Destriptions:TextInputLayout的一个登录页面
 * Created by weipengjie on 2016/11/14.
 */

public class LoginActivity extends JackBaseActivity {
    private static final String IS_LOGIN = "is_login";
    private AutoCompleteTextView mEmailView;
    private TextInputEditText mPasswordView;
    private ProgressDialog mProgressDialog;

    @Override
    protected void init() {
        ivTopBack.setVisibility(View.INVISIBLE);

        boolean isLogin = (boolean) SPUtils.get(this, IS_LOGIN, false);
        if (isLogin) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (TextInputEditText) findViewById(R.id.password);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    //尝试登录
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button emailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        emailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //尝试登录
                attemptLogin();
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login_layout;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByDoubleClick();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 说明:获取用户输入的内容并调用相应的验证方法
     */
    private void attemptLogin() {
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        if (isEmailValid(email) && isPasswordValid(password) && isMatch(email, password)) {
            login();
        }
    }

    /**
     * @param email    邮箱
     * @param password 密码
     * @return 用户名密码是否匹配
     */
    private boolean isMatch(String email, String password) {
        if (!USER_NAME.equals(email) || !PASSWORD.equals(password)) {
            ToastUtils.showShort(this, "账号或密码错误！");
            return false;
        }

        SPUtils.put(this, IS_LOGIN, true);
        return true;
    }


    /**
     * 说明:验证邮箱格式
     *
     * @param email 邮箱地址
     * @return 邮箱地址格式正确或错误
     */
    private boolean isEmailValid(String email) {
        if (email.length() == 0) {
            showToast("请输入邮箱!");
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("邮箱格式不正确!");
            return false;
        }
        return true;

    }

    /**
     * 说明:验证密码格式
     *
     * @param password icon_password
     * @return 密码格式正确或错误
     */
    private boolean isPasswordValid(String password) {
        if (password.length() == 0) {
            showToast("请输入密码!");
            return false;
        } else if (password.length() < 6) {
            showToast("请输入至少6位密码!");
            return false;
        }
        return true;
    }

    /**
     * 说明:显示Toast的方法
     *
     * @param message 要显示的消息
     */
    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 说明:登录
     * <p>
     * 这里为了展示对话框,将对话框固定显示了3秒
     */
    private void login() {
        showDialog();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                mProgressDialog.dismiss();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        timer.schedule(timerTask, 3000);
    }

    /**
     * 说明:show一个Dialog
     */
    private void showDialog() {
        mProgressDialog = new ProgressDialog(this, R.style.AppTheme_Dark_Dialog);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("正在验证...");
        mProgressDialog.show();
    }
}
