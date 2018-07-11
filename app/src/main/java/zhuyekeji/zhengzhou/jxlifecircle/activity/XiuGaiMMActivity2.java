package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.model.Response;

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

public class XiuGaiMMActivity2 extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.firstpassword)
    EditText firstpassword;
    @BindView(R.id.passworeagain)
    EditText passworeagain;
    @BindView(R.id.wancheng)
    TextView wancheng;
    private String again;

    @Override
    public int getViewId()
    {
        return R.layout.activity_xiu_gai_mm2;
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

    @OnClick({R.id.rl_back, R.id.wancheng})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.wancheng:
                String first=firstpassword.getText().toString().trim();
                again = passworeagain.getText().toString().trim();
                if (first==null|| again ==null||first.length()==0|| again.length()==0)
                {
                    ToastUtils.showShort("请输入密码");
                    return;
                }
                if (first.equals(again))
                {
                    JxApiCallBack.updatapassword(getToken(),first, again,1,XiuGaiMMActivity2.this,callBack);
                }else {
                    ToastUtils.showShort("两次密码不一致");
                    return;
                }
                break;
        }
    }
    CallBack callBack=new CallBack()
    {
        @Override
        public void onSuccess(int what, Response<String> result)
        {
          if (JsonUtile.getCode(result.body()).equals("200"))
          {
              ToastUtils.showShort(JsonUtile.getresulter(result.body()));
              SPUtils.getInstance().put("password",again);
          }else {
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
