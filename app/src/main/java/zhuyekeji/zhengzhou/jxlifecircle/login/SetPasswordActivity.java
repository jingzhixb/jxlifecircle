package zhuyekeji.zhengzhou.jxlifecircle.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.SPUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

public class SetPasswordActivity extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ed_mobile)
    EditText edMobile;
    @BindView(R.id.ed_againpassword)
    EditText edAgainpassword;
    @BindView(R.id.im_xieyi)
    ImageView imXieyi;
    @BindView(R.id.tv_xieyi)
    TextView tvXieyi;
    @BindView(R.id.ll_xieyi)
    LinearLayout llXieyi;
    private boolean mXieyi = false;
    private String mType;
    private String password;

    @Override
    public int getViewId()
    {
        return R.layout.activity_set_password;
    }

    @Override
    protected void processLogic()
    {
        tvTitle.setText("设置密码");
    }

    @Override
    protected void setListener()
    {
        mType = getIntent().getStringExtra("type");
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

    @OnClick({R.id.rl_back, R.id.im_xieyi, R.id.tv_xieyi, R.id.wancheng})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.im_xieyi:
                if (!mXieyi)
                {
                    imXieyi.setBackground(getResources().getDrawable(R.mipmap.gouxuan_on));
                    mXieyi = true;
                } else
                {
                    imXieyi.setBackground(getResources().getDrawable(R.mipmap.gouxuan_off));
                    mXieyi = false;
                }
                break;
            case R.id.tv_xieyi:
                break;
            case R.id.wancheng:
                String number = edMobile.getText().toString().trim();//第一次密码
                //第二次密码
                password = edAgainpassword.getText().toString().trim();
                if (number != null && number.equals(password) && number.length() > 0)
                {
                    String phone = getIntent().getStringExtra("phone");
                    if (mType.equals("1"))
                    {
                        JxApiCallBack.user_register(phone, password, 1, SetPasswordActivity.this, callBack);
//                         Intent intent=new Intent(SetPasswordActivity.this,UserWanShanActivity.class);//用戶
//                         intent.putExtra("password",password);
//                         intent.putExtra("phone",phone);
//                         startActivity(intent);
                    } else if (mType.equals("2"))
                    {
                        Intent intent = new Intent(SetPasswordActivity.this, ShangJiaWanShanActivity1.class);//商家
                        intent.putExtra("password", password);
                        intent.putExtra("phone", phone);
                        startActivity(intent);
                    } else if (mType.equals("3"))
                    {
                        Intent intent = new Intent(SetPasswordActivity.this, ShangJiaWanShanActivity1.class);//派送
                        intent.putExtra("password", password);
                        intent.putExtra("phone", phone);
                        intent.putExtra("type", mType);
                        startActivity(intent);
                    } else if (mType.equals("4"))
                    {
                        Intent intent = new Intent(SetPasswordActivity.this, ShangJiaWanShanActivity1.class);//車主
                        intent.putExtra("phone", phone);
                        intent.putExtra("type", mType);
                        startActivity(intent);
                    }
                } else
                {
                    ToastUtils.showShort("请输入密码");
                }
                //  JxApiCallBack.user_register();

                break;
        }
    }

    CallBack callBack = new CallBack()
    {
        @Override
        public void onSuccess(int what, Response<String> result)
        {
            if (JsonUtile.getCode(result.body()).equals("200"))
            {
                try
                {
                    JSONObject object = new JSONObject(JsonUtile.getbody(result.body()));
                    String token = object.getString("token");
                    SPUtils.getInstance().put("token", token);
                    SPUtils.getInstance().put("password",password);
                    Intent intent = new Intent(SetPasswordActivity.this, UserWanShanActivity.class);//用戶
                    startActivity(intent);
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            } else
            {
                ToastUtils.showShort(JsonUtile.getresulter(result.body()));
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
}
