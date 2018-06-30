package zhuyekeji.zhengzhou.jxlifecircle.homeacyivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;

public class KanJiaShareActivity extends BaseActivity
{
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_kanjia)
    TextView tvKanjia;
    @BindView(R.id.tv_next_time)
    TextView tvNextTime;
    @BindView(R.id.tv_username)
    TextView tvUsername;

    @Override
    public int getViewId()
    {
        return R.layout.activity_kan_jia_share;
    }

    @Override
    protected void processLogic()
    {

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
