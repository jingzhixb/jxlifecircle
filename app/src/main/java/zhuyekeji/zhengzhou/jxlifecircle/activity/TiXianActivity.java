package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;

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
}
