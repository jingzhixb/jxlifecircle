package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;

public class CooperationMeiShiActivity extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_mingcheng)
    TextView tvMingcheng;
    @BindView(R.id.ed_mingcheng)
    EditText edMingcheng;
    @BindView(R.id.tv_zhaopai)
    TextView tvZhaopai;
    @BindView(R.id.im_zhaopai)
    ImageView imZhaopai;
    @BindView(R.id.rl_zhaopai)
    RelativeLayout rlZhaopai;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.rl_shangjia)
    RelativeLayout rlShangjia;
    @BindView(R.id.ed_faren)
    EditText edFaren;
    @BindView(R.id.ed_mobile)
    EditText edMobile;
    @BindView(R.id.image_zhizhao)
    ImageView imageZhizhao;
    @BindView(R.id.im_registerxieyi)
    ImageView imRegisterxieyi;
    @BindView(R.id.rl_zhizhao)
    RelativeLayout rlZhizhao;
    @BindView(R.id.tijiao)
    TextView tijiao;

    @Override
    public int getViewId()
    {
        return R.layout.activity_cooperation_mei_shi;
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

    @OnClick({R.id.rl_back, R.id.rl_zhaopai, R.id.rl_address, R.id.rl_shangjia, R.id.rl_zhizhao, R.id.tijiao})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_zhaopai:
                break;
            case R.id.rl_address://店铺地址
                Intent intent=new Intent(CooperationMeiShiActivity.this,AddressSelectActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_shangjia://商家分类
                Intent intent1=new Intent(CooperationMeiShiActivity.this,ShopTypeActivity.class);
            startActivity(intent1);
                break;
            case R.id.rl_zhizhao://营业执照
                break;
            case R.id.tijiao:
                break;
        }
    }
}
