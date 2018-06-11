package zhuyekeji.zhengzhou.jxlifecircle.orderactivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
import zhuyekeji.zhengzhou.jxlifecircle.frament.order.AllOrderFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.order.DaiFaHuoFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.order.DaiPingJIaFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.order.PUserDaiJIeDanFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.order.PUserDaiQueRenFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.order.PUserTuiKuanFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.order.PUserYiWanChengFrament;

public class UserPinCheActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tl_connlent)
    CommonTabLayout tlConnlent;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"待接单", "待确认", "已完成","退款"};
    private ArrayList<Fragment> mFrament = new ArrayList<>();

    @Override
    public int getViewId()
    {
        return R.layout.activity_user_pin_che;
    }

    @Override
    protected void processLogic()
    {
        tvTitle.setText("拼车订单");
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
        mFrament.add(new PUserDaiJIeDanFrament());
        mFrament.add(new PUserDaiQueRenFrament());
        mFrament.add(new PUserYiWanChengFrament());
        mFrament.add(new PUserTuiKuanFrament());
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
}
