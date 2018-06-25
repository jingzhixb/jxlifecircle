package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.frament.user.PayJiLuFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.user.ShopDaiXiaoFeiFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.user.ShopPinJiaFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.user.ShopTuiKuanFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.user.ShopYIXiaoFeiFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.user.TiXianJiluFrament;

public class ShopOrederActivity extends BaseActivity
{
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.slidingtl)
    SlidingTabLayout slidingtl;
    @BindView(R.id.viewpage)
    ViewPager viewpage;
    private String[] titles = new String[]{"待消费", "已消费", "申请退款", "评价列表"};
    private ArrayList<Fragment> mFraments = new ArrayList<>();

    @Override
    public int getViewId()
    {
        return R.layout.activity_shop_oreder;
    }

    @Override
    protected void processLogic()
    {
        tvTitle.setText("商家订单");
        mFraments.add(new ShopDaiXiaoFeiFrament());
        mFraments.add(new ShopYIXiaoFeiFrament());
        mFraments.add(new ShopTuiKuanFrament());
        mFraments.add(new ShopPinJiaFrament());
        slidingtl.setViewPager(viewpage, titles, this, mFraments);
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
