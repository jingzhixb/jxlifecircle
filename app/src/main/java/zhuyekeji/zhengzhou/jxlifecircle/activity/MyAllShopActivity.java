package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;

public class MyAllShopActivity extends BaseActivity
{
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.yiji)
    RecyclerView yiji;
    @BindView(R.id.erji)
    RecyclerView erji;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.rl_newshop)
    RelativeLayout rlNewshop;

    @Override
    public int getViewId()
    {
        return R.layout.activity_my_all_shop;
    }

    @Override
    protected void processLogic()
    {
        tvTitle.setText("全部商品");
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

    @OnClick({R.id.rl_back, R.id.tv_type, R.id.rl_newshop})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_type:
                break;
            case R.id.rl_newshop:
                Intent intent=new Intent(MyAllShopActivity.this,NewShopActivity.class);//新增商品
                startActivity(intent);
                break;
        }
    }
}
