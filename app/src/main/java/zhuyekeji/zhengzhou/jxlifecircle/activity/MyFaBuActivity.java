package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.frament.user.DianPuFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.user.FaBuFangChanFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.user.FaBuFriendFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.user.FaBuPinCheFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.user.FaBuQiCheFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.user.ShopFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.user.TieZiFrament;

public class MyFaBuActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tl_connlent)
    CommonTabLayout tlConnlent;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    private String[] mTitles = {"朋友圈", "汽车圈", "房产圈","拼车"};
    private ArrayList<Fragment> mFrament = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    @Override
    public int getViewId()
    {
        return R.layout.activity_my_fa_bu;
    }

    @Override
    protected void processLogic()
    {
        for (int i=0;i<mTitles.length;i++)
        {
            final int t=i;
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
        mFrament.add(new FaBuFriendFrament());
        mFrament.add(new FaBuQiCheFrament());
        mFrament.add(new FaBuFangChanFrament());
        mFrament.add(new FaBuPinCheFrament());
        tlConnlent.setTabData(mTabEntities,this,R.id.frame_layout, mFrament);
    }

    @Override
    protected void setListener()
    {

    }

    @Override
    protected Context getActivityContext()
    {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.rl_back)
    public void onViewClicked()
    {
        finish();
    }
}
