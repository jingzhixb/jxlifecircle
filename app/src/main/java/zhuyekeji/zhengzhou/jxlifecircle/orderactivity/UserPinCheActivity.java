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
import zhuyekeji.zhengzhou.jxlifecircle.frament.order.UserJIuDianDaiRuZhuFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.order.UserJIuLiShiFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.order.UserJiuDianRuZhuZhongFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.order.PUserTuiKuanFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.order.UserJiuDianDaiPingJiaFrament;

public class UserPinCheActivity extends BaseActivity
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
    private String[] mTitles = {"待入住", "入住中", "待评价", "申请退款","历史订单"};
    private ArrayList<Fragment> mFrament = new ArrayList<>();

    @Override
    public int getViewId()
    {
        return R.layout.activity_user_pin_che;
    }

    @Override
    protected void processLogic()
    {
        tvTitle.setText("酒店订单");
        rlBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
        mFrament.add(new UserJIuDianDaiRuZhuFrament());
        mFrament.add(new UserJiuDianRuZhuZhongFrament());
        mFrament.add(new UserJiuDianDaiPingJiaFrament());
        mFrament.add(new PUserTuiKuanFrament());
        mFrament.add(new UserJIuLiShiFrament());
        tlConnlent.setViewPager(viewpage,mTitles, this, mFrament);
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
