package zhuyekeji.zhengzhou.jxlifecircle.orderactivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.frament.order.JDaiFaHuoFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.order.JDaiQueRenFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.order.JLiShiOrderFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.order.SDaiPingJiaFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.order.SDaiXiaoFeiFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.order.STuiKuanFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.order.UserJiFenLiShiFrament;

public class OrderJiFenActivity extends BaseActivity
{

//	订单-优惠购订单（买家）
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tl_connlent)
    CommonTabLayout tlConnlent;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"待发货", "待确认", "历史订单"};
    private ArrayList<Fragment> mFrament = new ArrayList<>();
    @Override
    public int getViewId()
    {
        return R.layout.activity_order_ji_fen;
    }

    @Override
    protected void processLogic()
    {
        tvTitle.setText("兑换订单");
        rlBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        for (int i=0;i<mTitles.length;i++)
        {
            final int t = i;
            mTabEntities.add(new CustomTabEntity()
            {
                @Override
                public String getTabTitle()
                {
                    return mTitles[t];
                }

                @Override
                public int getTabSelectedIcon()
                {
                    return 0;
                }

                @Override
                public int getTabUnselectedIcon()
                {
                    return 0;
                }
            });
        }
        mFrament.add(new JDaiFaHuoFrament());
        mFrament.add(new JDaiQueRenFrament());
        mFrament.add(new UserJiFenLiShiFrament());
        tlConnlent.setTabData(mTabEntities,this,R.id.frame_layout, mFrament);

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
