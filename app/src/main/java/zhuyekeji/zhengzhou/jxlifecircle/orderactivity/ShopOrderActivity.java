package zhuyekeji.zhengzhou.jxlifecircle.orderactivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.frament.order.DaiPingJIaFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.order.JLiShiOrderFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.order.UserDaiXiaoFeiFrament;

public class ShopOrderActivity extends BaseActivity
{
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tl_connlent)
    SlidingTabLayout tlConnlent;
    @BindView(R.id.viewpage)
    ViewPager viewpage;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"代消费", "待评价", "待收货"};
    private ArrayList<Fragment> mFrament = new ArrayList<>();

    @Override
    public int getViewId()
    {
        return R.layout.activity_shop_order;
    }

    @Override
    protected void processLogic()
    {

        tvTitle.setText("优惠购订单");
        rlBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        // mFrament.add(new AllOrderFrament());
        mFrament.add(new UserDaiXiaoFeiFrament());
        mFrament.add(new DaiPingJIaFrament());
        mFrament.add(new JLiShiOrderFrament());

        tlConnlent.setViewPager(viewpage,mTitles,this,mFrament);
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
