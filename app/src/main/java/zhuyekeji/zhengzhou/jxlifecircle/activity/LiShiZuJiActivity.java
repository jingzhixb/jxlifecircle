package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.LiShiZuJuAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;

public class LiShiZuJiActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_lishi)
    RecyclerView rvLishi;
private LiShiZuJuAdapter adapter;
    @Override
    public int getViewId()
    {
        return R.layout.activity_li_shi_zu_ji;
    }

    @Override
    protected void processLogic()
    {
        tvTitle.setText("历史足迹");
        rlBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
        adapter=new LiShiZuJuAdapter(this,R.layout.lishi_item,null);
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
