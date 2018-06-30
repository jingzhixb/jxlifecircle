package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;

public class NewShopActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_shopname)
    EditText tvShopname;
    @BindView(R.id.ed_miaoshu)
    TextView edMiaoshu;
    @BindView(R.id.tv_iaoqian)
    TextView tvIaoqian;
    @BindView(R.id.rl_biaoqian)
    RelativeLayout rlBiaoqian;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.fenlei)
    RelativeLayout fenlei;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.sp_start_youxiao)
    Spinner spStartYouxiao;
    @BindView(R.id.sp_end_youxiao)
    Spinner spEndYouxiao;
    @BindView(R.id.im_tongyong)
    ImageView imTongyong;
    @BindView(R.id.sp_start_time)
    Spinner spStartTime;
    @BindView(R.id.sp_end_time)
    Spinner spEndTime;
    @BindView(R.id.jia2)
    ImageView jia2;
    @BindView(R.id.rl_guize)
    RelativeLayout rlGuize;
    @BindView(R.id.tv_baocun)
    TextView tvBaocun;

    @Override
    public int getViewId()
    {
        return R.layout.activity_new_shop;
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

    @OnClick({R.id.rl_back, R.id.rl_biaoqian, R.id.fenlei, R.id.rl_guize, R.id.tv_baocun, R.id.rl_miaoshu})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_biaoqian://标签
                Intent intent = new Intent(NewShopActivity.this, BiaoQianActivity.class);
                startActivity(intent);
                break;
            case R.id.fenlei://分类
                Intent intent2 = new Intent(NewShopActivity.this, TypeActivity.class);
                startActivity(intent2);
                break;
            case R.id.rl_guize://使用规则
                break;
            case R.id.tv_baocun://保存
                break;
            case R.id.rl_miaoshu://商品描述
                Intent intent1 = new Intent(NewShopActivity.this, MiaoShuActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
