package zhuyekeji.zhengzhou.jxlifecircle;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.JiLuAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;

public class TiXIanJiLuActivity extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_zhichu)
    RecyclerView rvZhichu;

    @Override
    public int getViewId()
    {
        return R.layout.activity_ti_xian_ji_lu;
    }

    @Override
    protected void processLogic()
    {
        tvTitle.setText("支出记录");
        rlBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }

    @Override
    protected void setListener()
    {
        JiLuAdapter adapter=new JiLuAdapter(this,R.layout.jilu_item,null);
        rvZhichu.setLayoutManager(new LinearLayoutManager(this));
        rvZhichu.setAdapter(adapter);
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
