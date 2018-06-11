package zhuyekeji.zhengzhou.jxlifecircle.frament;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.bgabanner.BGABanner;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.api.FragmentBackHandler;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseFragment;
import zhuyekeji.zhengzhou.jxlifecircle.homeacyivity.CitySelectActivity;
import zhuyekeji.zhengzhou.jxlifecircle.utils.other.UIThread;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ActivityUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

/**
 * Created by Administrator on 2018/6/6.
 */

public class HomeFrament extends BaseFragment implements FragmentBackHandler
{
    @BindView(R.id.rl_city)
    RelativeLayout rlCity;
    @BindView(R.id.banner_pager)
    BGABanner bannerPager;
    @BindView(R.id.youhuigou)
    LinearLayout youhuigou;
    @BindView(R.id.pinche)
    LinearLayout pinche;
    @BindView(R.id.xiangqin)
    LinearLayout xiangqin;
    @BindView(R.id.zhibo)
    LinearLayout zhibo;
    @BindView(R.id.ershouche)
    LinearLayout ershouche;
    @BindView(R.id.ershoufang)
    LinearLayout ershoufang;
    @BindView(R.id.bianmin)
    LinearLayout bianmin;
    @BindView(R.id.kanjia)
    LinearLayout kanjia;
    @BindView(R.id.marquee_view)
    ViewFlipper marqueeView;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.nestscrollview)
    NestedScrollView nestscrollview;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    private View view;
    Unbinder unbinder;
    private boolean isDoubleClick = false;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container)
    {
        view = inflater.inflate(R.layout.home_frament, container, false);
        return view;
    }

    @Override
    protected void initListener()
    {
rlCity.setOnClickListener(new View.OnClickListener()
{
    @Override
    public void onClick(View view)
    {
        Intent intent=new Intent(getActivity(), CitySelectActivity.class);
        startActivity(intent);
    }
});
    }

    @Override
    protected void initData()
    {

    }

    @Override
    public boolean onBackPressed()
    {
        if (isDoubleClick)
        {
            ActivityUtils.finishAllActivities();
            System.exit(0);
        } else
        {
            ToastUtils.showShort("再次点击一次退出程序");
            isDoubleClick = true;
            UIThread.getInstance().postDelay(new Runnable()
            {
                @Override
                public void run()
                {
                    isDoubleClick = false;
                }
            }, 1000);
        }
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
    }
}
