package zhuyekeji.zhengzhou.jxlifecircle.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.RegexUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

public class RegisterActivity extends BaseActivity
{
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ed_mobile)
    EditText edMobile;
    @BindView(R.id.ed_yanzheng)
    EditText edYanzheng;
    @BindView(R.id.bt_yanzheng)
    Button btYanzheng;
    @BindView(R.id.next)
    TextView next;
    private String type;
    private MyCountDownTimer myCountDownTimer;

    @Override
    public int getViewId()
    {
        return R.layout.activity_register;
    }

    @Override
    protected void processLogic()
    {
        type = getIntent().getStringExtra("type");//5忘记密码,1用户注册,2商家注册，3派送注册，4车主注册
        if (type == null)
        {
            return;
        } else
        {
            if (type.equals("1"))
            {
               tvTitle.setText("用户注册");
            } else if (type.equals("2"))
            {
                tvTitle.setText("商家注册");
            } else if (type.equals("3"))
            {
                tvTitle.setText("派送注册");
            } else if (type.equals("4"))
            {
                tvTitle.setText("车主注册");
            }
        }
    }

    @Override
    protected void setListener()
    {

    }

    @Override
    protected Context getActivityContext()
    {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_back, R.id.bt_yanzheng, R.id.next})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.bt_yanzheng://获取验证码
                if (RegexUtils.isMobileExact(edMobile.getText().toString().trim()))
                {
                    myCountDownTimer = new MyCountDownTimer(60000, 1000);
                    myCountDownTimer.start();
                } else
                {
                    ToastUtils.showShort("请输入正确的电话号码");
                }
                break;
            case R.id.next://下一步
                Intent intent=new Intent(RegisterActivity.this,SetPasswordActivity.class);
                intent.putExtra("type",type);
                startActivity(intent);
                break;
        }
    }


    //复写倒计时
    private class MyCountDownTimer extends CountDownTimer
    {

        public MyCountDownTimer(long millisInFuture, long countDownInterval)
        {
            super(millisInFuture, countDownInterval);
        }

        //计时过程
        @Override
        public void onTick(long l)
        {
            //防止计时过程中重复点击
            edMobile.removeTextChangedListener(mTextWatcher);
            btYanzheng.setBackgroundResource(R.color.title_666);
            btYanzheng.setClickable(false);
            btYanzheng.setText(l / 1000 + "s");
        }

        //计时完毕的方法
        @Override
        public void onFinish()
        {
            //重新给Button设置文字
            btYanzheng.setText("重新获取");
            //设置可点击
            edMobile.addTextChangedListener(mTextWatcher);
            btYanzheng.setClickable(true);
            btYanzheng.setBackgroundResource(R.color.jxcolor);
        }
    }

    //监听输入的手机号
    TextWatcher mTextWatcher = new TextWatcher()
    {
        private CharSequence temp;

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            temp = s;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
        }

        @Override
        public void afterTextChanged(Editable s)
        {
            if (temp.length() == 11)
            {//改变验证码背景颜色
                btYanzheng.setBackgroundResource(R.color.jxcolor);
                btYanzheng.setEnabled(true);
                btYanzheng.setClickable(true);
            } else
            {
                btYanzheng.setBackgroundResource(R.color.title_666);
                btYanzheng.setClickable(false);
            }
        }
    };
}