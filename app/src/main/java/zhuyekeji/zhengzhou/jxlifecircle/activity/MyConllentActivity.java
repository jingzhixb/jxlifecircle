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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.frament.user.DianPuFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.user.ShopFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.user.TieZiFrament;

public class MyConllentActivity extends BaseActivity
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
    private String[] mTitles = {"商品收藏", "店铺收藏", "帖子收藏"};
    private ArrayList<Fragment> mFrament = new ArrayList<>();

    @Override
    public int getViewId()
    {
        return R.layout.activity_my_conllent;
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
        mFrament.add(new ShopFrament());
        mFrament.add(new DianPuFrament());
        mFrament.add(new TieZiFrament());
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
