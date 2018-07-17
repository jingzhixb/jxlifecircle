package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.content.Intent;
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
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

public class TiXianActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.rl_zhanghao)
    RelativeLayout rlZhanghao;
    @BindView(R.id.ed_money)
    EditText edMoney;
    @BindView(R.id.tv_alltixian)
    TextView tvAlltixian;
    @BindView(R.id.tv_keti)
    TextView tvKeti;

    @Override
    public int getViewId()
    {
        return R.layout.activity_ti_xian;
    }

    @Override
    protected void processLogic()
    {
        tvTitle.setText("提现");
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

    @OnClick({R.id.rl_back, R.id.rl_zhanghao, R.id.tv_alltixian})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_zhanghao:
                Intent intent=new Intent(TiXianActivity.this,BankListActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_alltixian://全部提现
                JxApiCallBack.ti(getToken(),"","","",1,TiXianActivity.this,callBack);
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
}
