package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.YouHuiJuanAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.bean.JiFenOrderBean;

public class ShopYouHuiJuanActivity extends BaseActivity
{
    //优惠卷列表
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_youhui)
    RecyclerView rvYouhui;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    private YouHuiJuanAdapter adapter;
    private List<JiFenOrderBean> beans = new ArrayList<>();

    @Override
    public int getViewId()
    {
        return R.layout.activity_shop_you_hui_juan;
    }

    @Override
    protected void processLogic()
    {
        for (int i = 0; i < 3; i++)
        {
            beans.add(new JiFenOrderBean());
        }
        tvTitle.setText("优惠卷");
        rvYouhui.setLayoutManager(new LinearLayoutManager(this));
        adapter = new YouHuiJuanAdapter(this, R.layout.youhuijuan_item, beans);
        rvYouhui.setAdapter(adapter);
        adapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener()
        {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i)
            {
                switch (view.getId())
                {
                    case R.id.tv_bianji://编辑优惠卷
                        break;
                    case R.id.xiajia://下架哟优惠卷
                        break;
                    case R.id.tv_shanchu://删除优惠卷
                        break;
                }
            }
        });
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

    @OnClick({R.id.rl_back, R.id.tv_add})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_add:
                Intent intent=new Intent(ShopYouHuiJuanActivity.this,AddYouHuiJuanActivity.class);
                startActivity(intent);
                break;
        }
    }
}
