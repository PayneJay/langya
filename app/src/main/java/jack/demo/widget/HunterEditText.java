package jack.demo.widget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import jack.demo.R;

/**
 * ================================================
 * description:
 * ================================================
 * package_name: jack.demo.widget
 * author: PayneJay
 * date: 2017/3/25.
 */

@SuppressLint("AppCompatCustomView")
public class HunterEditText extends EditText {

    /**
     * 该字段是否必填
     */
    private boolean isRequired;

    /**
     * 错误信息
     */
    private String errorMsg = "不能为空";

    /**
     * 所填数值
     */
    private String value;

    private String name;

    public HunterEditText(Context context) {
        super(context);
        init(context, null);
    }

    public HunterEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public HunterEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HunterEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            @SuppressLint("Recycle") TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HunterEditText);
            isRequired = ta.getBoolean(R.styleable.HunterEditText_isRequired, false);
        }

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString().trim();
                boolean isEmpty = checkIsEmpty(str);

                if (isEmpty) {
                    setErrorMsg(name + errorMsg);
                } else {
                    setValue(str);
                }
            }
        });
    }

    /**
     * 判断输入是否为空
     *
     * @param str 输入字符串
     * @return 是否为空
     */
    private boolean checkIsEmpty(String str) {
        return !isRequired || TextUtils.isEmpty(str);
    }

}
