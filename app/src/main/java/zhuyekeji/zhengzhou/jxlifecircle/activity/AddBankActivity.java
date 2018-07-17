package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
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
import zhuyekeji.zhengzhou.jxlifecircle.utils.BankCOde;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.RegexUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

public class AddBankActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.ed_bank_name)
    EditText edBankName;
    @BindView(R.id.ed_bank_number)
    EditText edBankNumber;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_cdnumber)
    EditText edCdnumber;
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.ed_yanzheng)
    EditText edYanzheng;
    @BindView(R.id.tv_yanzheng)
    TextView tvYanzheng;
    @BindView(R.id.tv_bangding)
    TextView tvBangding;
    private String mPhone;
    private MyCountDownTimer myCountDownTimer;
    private boolean validated;
    private static String card="https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardNo=";
    private String code;

    @Override
    public int getViewId()
    {
        return R.layout.activity_add_bank;
    }

    @Override
    protected void processLogic()
    {
     tvTitle.setText("添加银行卡");

        edBankNumber.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if (editable.toString().length()<19)
                {
                    edBankName.setText("");
                }
                int carlenth=editable.toString().length();
                if (carlenth==19||carlenth==16||carlenth==17)
                {
                    OkGo.<String>post(card+editable.toString()+"&cardBinCheck=true")
                            .tag(AddBankActivity.this)
                            .execute(new StringCallback()
                            {
                                @Override
                                public void onSuccess(Response<String> response)
                                {
                                    String body=response.body();
                                    try
                                    {
                                        JSONObject jsonObject=new JSONObject(body);
                                        String stat=jsonObject.getString("stat");
                                        validated = jsonObject.getBoolean("validated");
                                        if (stat.equals("ok"))
                                        {
                                            String bank=jsonObject.getString("bank");
                                            JSONObject object=new JSONObject(BankCOde.bankCode);
                                            String bankname=object.getString(bank);
                                            edBankName.setText(bankname);
                                        }
                                    } catch (JSONException e)
                                    {
                                        //ToastManager.show("请检查银行卡号是否正确");
                                        e.printStackTrace();
                                    }
                                }
                            });
                }
            }
        });
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

    @OnClick({R.id.rl_back, R.id.tv_yanzheng, R.id.tv_bangding})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_yanzheng://获取验证码
                if (RegexUtils.isMobileExact(edPhone.getText().toString().trim()))
                {
                    mPhone=edPhone.getText().toString().trim();
                    myCountDownTimer = new MyCountDownTimer(60000, 1000);
                    myCountDownTimer.start();
                    JxApiCallBack.sedmsm(mPhone,"1",1,AddBankActivity.this,callBack);
                } else
                {
                    ToastUtils.showShort("请输入正确的电话号码");
                }
                break;
            case R.id.tv_bangding:
                String banknamae=edBankName.getText().toString().trim();
                String banknumber=edBankNumber.getText().toString().trim();
                String name=edName.getText().toString().trim();
                String cdnumber=edCdnumber.getText().toString().trim();
                String yanzhen=edYanzheng.getText().toString().trim();
                if (banknamae==null||banknamae.length()==0)
                {
                    ToastUtils.showShort("请输入银行名称 ");
                    return;
                }
                if (banknumber==null||banknumber.length()==0||banknumber.length()<16||banknumber.length()==17||banknumber.length()>18)
                {
                    ToastUtils.showShort("银行卡号错误 ");
                    return;
                }
                if (name==null||name.length()==0)
                {
                    ToastUtils.showShort("请输入持卡人");
                    return;
                }
                if (cdnumber==null||cdnumber.length()==0)
                {
                    ToastUtils.showShort("请输入 身份证号");
                    return;
                }
                if (mPhone==null||mPhone.length()==0)
                {
                    ToastUtils.showShort("请输入电话号码");
                    return;
                }
                if (yanzhen==null||yanzhen.length()==0)
                {
                    ToastUtils.showShort("请输入验证码 ");
                    return;
                }
                if (!yanzhen.equals(code))
                {
                    ToastUtils.showShort("验证码错误");
                    return;
                }
                JxApiCallBack.addbank(getToken(),banknamae,banknumber,name,cdnumber,mPhone,yanzhen,
                        2,AddBankActivity.this,callBack);
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
                  if (JsonUtile.isTrue(result.body()))
                  {
                      ToastUtils.showShort(JsonUtile.getresulter(result.body()));
                      try
                      {
                          JSONObject object=new JSONObject(JsonUtile.getbody(result.body()));
                          code = object.getString("code");
                      } catch (JSONException e)
                      {
                          e.printStackTrace();
                      }
                  }else {
                      ToastUtils.showShort(JsonUtile.getresulter(result.body()));
                  }
                  break;
              case 2:
                  if (JsonUtile.isTrue(result.body()))
                  {
                      ToastUtils.showShort(JsonUtile.getresulter(result.body()));
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
            edPhone.removeTextChangedListener(mTextWatcher);
            tvYanzheng.setBackgroundResource(R.color.title_666);
            tvYanzheng.setClickable(false);
            tvYanzheng.setText(l / 1000 + "s");
        }

        //计时完毕的方法
        @Override
        public void onFinish()
        {
            //重新给Button设置文字
            tvYanzheng.setText("重新获取");
            //设置可点击
            edPhone.addTextChangedListener(mTextWatcher);
            tvYanzheng.setClickable(true);
            tvYanzheng.setBackgroundResource(R.color.jxcolor);
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
                tvYanzheng.setBackgroundResource(R.color.jxcolor);
                tvYanzheng.setEnabled(true);
                tvYanzheng.setClickable(true);
            } else
            {
                tvYanzheng.setBackgroundResource(R.color.title_666);
                tvYanzheng.setClickable(false);
            }
        }
    };
}
