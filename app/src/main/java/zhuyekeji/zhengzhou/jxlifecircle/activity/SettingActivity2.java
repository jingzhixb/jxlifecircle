package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;

public class SettingActivity2 extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_xiugaipassword)
    RelativeLayout rlXiugaipassword;
    @BindView(R.id.tv_cache)
    TextView tvCache;
    @BindView(R.id.rl_calean)
    RelativeLayout rlCalean;
    @BindView(R.id.rl_guanyu)
    RelativeLayout rlGuanyu;
    @BindView(R.id.rl_genduo)
    RelativeLayout rlGenduo;

    @Override
    public int getViewId()
    {
        return R.layout.activity_setting2;
    }

    @Override
    protected void processLogic()
    {
tvTitle.setText("设置");
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

    @OnClick({R.id.rl_back, R.id.rl_xiugaipassword, R.id.rl_calean, R.id.rl_guanyu, R.id.rl_genduo})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_xiugaipassword:
                Intent intent2=new Intent(SettingActivity2.this,XiuGaiMMActivity.class);
                startActivity(intent2);
                break;
            case R.id.rl_calean:
                break;
            case R.id.rl_guanyu:
                Intent intent=new Intent(SettingActivity2.this,SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_genduo:
                break;
        }
    }
}
