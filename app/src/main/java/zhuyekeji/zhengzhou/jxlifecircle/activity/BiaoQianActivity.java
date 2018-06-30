package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.BiaoQianAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;

public class BiaoQianActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_select_on)
    RecyclerView rvSelectOn;
    @BindView(R.id.rv_alll)
    RecyclerView rvAlll;
    @BindView(R.id.baocun)
    TextView baocun;

    private BiaoQianAdapter adapter,adapterAll;
    @Override
    public int getViewId()
    {
        return R.layout.activity_biao_qian;
    }

    @Override
    protected void processLogic()
    {
tvTitle.setText("商品标签");
    }

    @Override
    protected void setListener()
    {
        List<String> list=new ArrayList<>();
        for (int i=0;i<9;i++)
        {
            list.add("标签"+i);
        }
          rvAlll.setLayoutManager(new GridLayoutManager(BiaoQianActivity.this,4));
        rvSelectOn.setLayoutManager(new GridLayoutManager(BiaoQianActivity.this,4));
        adapter=new BiaoQianAdapter(this,R.layout.biaoqian_item,list,1);
        adapterAll=new BiaoQianAdapter(this,R.layout.biaoqian_item,list,2);
        rvSelectOn.setAdapter(adapter);
        rvAlll.setAdapter(adapterAll);
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

    @OnClick({R.id.rl_back, R.id.baocun})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                break;
            case R.id.baocun:
                break;
        }
    }
}
