package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.AddressAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;

public class MyAddressActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_addrsee)
    RecyclerView rvAddrsee;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    @BindView(R.id.rl_add)
    RelativeLayout rlAdd;
private AddressAdapter adapter;
    @Override
    public int getViewId()
    {
        return R.layout.activity_my_address;
    }

    @Override
    protected void processLogic()
    {
tvTitle.setText("地址管理");
    }

    @Override
    protected void setListener()
    {
adapter=new AddressAdapter(mContext,R.layout.myaddress_item,null);
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

    @OnClick({R.id.rl_back, R.id.rl_add})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_add:
                Intent intent=new Intent(MyAddressActivity.this,AddAddressActivity.class);
                startActivity(intent);
                break;
        }
    }
}
