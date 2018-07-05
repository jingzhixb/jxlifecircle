package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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
import zhuyekeji.zhengzhou.jxlifecircle.adapter.TaoCanAfapter;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;

public class AddTaoCanActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.rv_taocan)
    RecyclerView rvTaocan;
    @BindView(R.id.tv_baocun)
    TextView tvBaocun;
    private List<String> beans = new ArrayList<>();
    private TaoCanAfapter afapter;

    @Override
    public int getViewId()
    {
        return R.layout.activity_add_tao_can;
    }

    @Override
    protected void processLogic()
    {
        tvTitle.setText("套餐内容");
        beans.add("");
        rvTaocan.setLayoutManager(new LinearLayoutManager(this));
        afapter = new TaoCanAfapter(this, R.layout.addtaocan_item, beans);
        rvTaocan.setAdapter(afapter);
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

    @OnClick({R.id.rl_back, R.id.tv_baocun})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_baocun:
                break;
        }
    }
}
