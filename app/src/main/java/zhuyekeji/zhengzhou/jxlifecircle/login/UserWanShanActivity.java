package zhuyekeji.zhengzhou.jxlifecircle.login;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;

public class UserWanShanActivity extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.wancheng)
    TextView wancheng;
    @BindView(R.id.tv_skip)
    TextView tvSkip;

    @Override
    public int getViewId()
    {
        return R.layout.activity_user_wan_shan;
    }

    @Override
    protected void processLogic()
    {
        tvTitle.setText("完善资料");
    }

    @Override
    protected void setListener()
    {

    }

    @Override
    protected Context getActivityContext()
    {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_back, R.id.wancheng, R.id.tv_skip})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.wancheng:
                break;
            case R.id.tv_skip:
                break;
        }
    }
}
