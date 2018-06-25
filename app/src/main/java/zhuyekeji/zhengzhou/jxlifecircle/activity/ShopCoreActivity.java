package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;

public class ShopCoreActivity extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.im_title)
    ImageView imTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;
    @BindView(R.id.rl_dingdan)
    RelativeLayout rlDingdan;
    @BindView(R.id.rl_guanli)
    RelativeLayout rlGuanli;
    @BindView(R.id.rl_youhui)
    RelativeLayout rlYouhui;

    @Override
    public int getViewId()
    {
        return R.layout.activity_shop_core;
    }

    @Override
    protected void processLogic()
    {
tvTitle.setText("商家中心");
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

    @OnClick({R.id.rl_back, R.id.ll_info, R.id.rl_dingdan, R.id.rl_guanli, R.id.rl_youhui})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_info:
                break;
            case R.id.rl_dingdan://商家订单
                Intent intent=new Intent(ShopCoreActivity.this,ShopOrederActivity.class);
            startActivity(intent);
                break;
            case R.id.rl_guanli://商品管理
                Intent intent1=new Intent(ShopCoreActivity.this,MyAllShopActivity.class);
            startActivity(intent1);
                break;
            case R.id.rl_youhui://优惠卷
                break;
        }
    }
}
