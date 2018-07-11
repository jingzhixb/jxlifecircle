package zhuyekeji.zhengzhou.jxlifecircle.activity;

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

import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.RegexUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.SPUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

public class XiuGaiMMActivity extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ed_password_old)
    EditText edPasswordOld;
    @BindView(R.id.ed_mobile)
    EditText edMobile;
    @BindView(R.id.ed_yanzheng)
    EditText edYanzheng;
    @BindView(R.id.bt_yanzheng)
    Button btYanzheng;
    @BindView(R.id.next)
    TextView next;
    private MyCountDownTimer myCountDownTimer;
    private String code;
    @Override
    public int getViewId()
    {
        return R.layout.activity_xiu_gai_mm;
    }

    @Override
    protected void processLogic()
    {
tvTitle.setText("修改密码");
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
            case R.id.bt_yanzheng:
                if (RegexUtils.isMobileExact(edMobile.getText().toString().trim()))
                {
                    myCountDownTimer = new MyCountDownTimer(60000, 1000);
                    myCountDownTimer.start();
                    JxApiCallBack.getverify(getToken(),edMobile.getText().toString().trim(),1,XiuGaiMMActivity.this,callBack);
                } else
                {
                    ToastUtils.showShort("请输入正确的电话号码");
                }
                break;
            case R.id.next:
                String password=edPasswordOld.getText().toString().trim();
                String pass= SPUtils.getInstance().getString("password");
                if (pass!=null&&password!=null&&pass.equals(password))
                {

                }else {
                    ToastUtils.showShort("密码不正确");
                    return;
                }
                String yan=edYanzheng.getText().toString().trim();
                if (yan==null||!yan.equals(code))
                {
                    ToastUtils.showShort("验证码错误");
                    return;
                }
                Intent intent=new Intent(XiuGaiMMActivity.this,XiuGaiMMActivity2.class);
                startActivity(intent);
                break;
        }

    }
    CallBack callBack=new CallBack()
    {
        @Override
        public void onSuccess(int what, Response<String> result)
        {
            switch (what)
            {
                case 1:
                    if (JsonUtile.getCode(result.body()).equals("200"))
                    {
                        try
                        {
                            JSONObject object=new JSONObject(result.body());
                            code = object.getString("code");
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }else {
                        ToastUtils.showShort(JsonUtile.getresulter(result.body()));
                    }
                    break;
            }
        }

        @Override
        public void onFail(int what, Response<String> result)
        {

        }

        @Override
        public void onFinish(int what)
        {

        }
    };
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
