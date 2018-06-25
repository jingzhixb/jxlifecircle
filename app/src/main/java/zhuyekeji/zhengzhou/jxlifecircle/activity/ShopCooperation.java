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

public class ShopCooperation extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_meishi)
    RelativeLayout rlMeishi;
    @BindView(R.id.rl_jiudian)
    RelativeLayout rlJiudian;
    @BindView(R.id.rl_dujia)
    RelativeLayout rlDujia;
    @BindView(R.id.rl_zonghe)
    RelativeLayout rlZonghe;

    @Override
    public int getViewId()
    {
        return R.layout.activity_shop_cooperation;
    }

    @Override
    protected void processLogic()
    {
tvTitle.setText("商家合作");
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

    @OnClick({R.id.rl_back, R.id.rl_meishi, R.id.rl_jiudian, R.id.rl_dujia, R.id.rl_zonghe})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_meishi:
                Intent intent=new Intent(ShopCooperation.this,CooperationMeiShiActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_jiudian:
                Intent intent1=new Intent(ShopCooperation.this,CooperationMeiShiActivity.class);
                startActivity(intent1);
                break;
            case R.id.rl_dujia:
                Intent intent2=new Intent(ShopCooperation.this,CooperationMeiShiActivity.class);
                startActivity(intent2);
                break;
            case R.id.rl_zonghe:
                Intent intent3=new Intent(ShopCooperation.this,CooperationMeiShiActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
