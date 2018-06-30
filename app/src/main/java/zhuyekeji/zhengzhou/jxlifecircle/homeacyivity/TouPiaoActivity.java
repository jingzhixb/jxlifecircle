package zhuyekeji.zhengzhou.jxlifecircle.homeacyivity;

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
import zhuyekeji.zhengzhou.jxlifecircle.frament.home.TouPaioAllFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.TouPiaoNewFrament;

public class TouPiaoActivity extends BaseActivity
{
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.slidingtl)
    SlidingTabLayout slidingtl;
    @BindView(R.id.viewpage)
    ViewPager viewpage;
    private String[] titles = new String[]{"最新", "总排名"};
    private ArrayList<Fragment> mFraments = new ArrayList<>();

    @Override
    public int getViewId()
    {
        return R.layout.activity_tou_piao;
    }

    @Override
    protected void processLogic()
    {
        mFraments.add(new TouPiaoNewFrament());
        mFraments.add(new TouPaioAllFrament());
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
