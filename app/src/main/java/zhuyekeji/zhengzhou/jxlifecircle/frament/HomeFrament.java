package zhuyekeji.zhengzhou.jxlifecircle.frament;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.github.clans.fab.FloatingActionButton;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.bgabanner.BGABanner;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.MyGridViewAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.api.FragmentBackHandler;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseFragment;
import zhuyekeji.zhengzhou.jxlifecircle.bean.HomeIconInfo;
import zhuyekeji.zhengzhou.jxlifecircle.homeacyivity.CitySelectActivity;
import zhuyekeji.zhengzhou.jxlifecircle.homeacyivity.KanJiaListActivity;
import zhuyekeji.zhengzhou.jxlifecircle.homeacyivity.PinCheActivity;
import zhuyekeji.zhengzhou.jxlifecircle.homeacyivity.TouPiaoActivity;
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
    @BindView(R.id.home_viewpage)
    ViewPager homeViewpage;

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.fl_button)
    FloatingActionButton flButton;

    private View view;
    Unbinder unbinder;
    private boolean isDoubleClick = false;
    private int[] img = new int[]{R.mipmap.youhuigou, R.mipmap.pinche, R.mipmap.xiangqin, R.mipmap.zhibo,
            R.mipmap.ershouche, R.mipmap.ershoufang
            , R.mipmap.bianmin, R.mipmap.kanjia};
    private String[] titles = new String[]{"优惠购", "拼车", "相亲", "直播", "二手车", "二手房", "便民信息", "砍价免费拿"};

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
                Intent intent = new Intent(getActivity(), CitySelectActivity.class);
                startActivity(intent);
            }
        });
        flButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), TouPiaoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData()
    {
        View page1 = getActivity().getLayoutInflater().inflate(R.layout.home_grid, null);
        View page2 = getActivity().getLayoutInflater().inflate(R.layout.home_grid, null);
        final GridView gridView1 = page1.findViewById(R.id.grid);
        GridView gridView2 = page2.findViewById(R.id.grid);

        List<HomeIconInfo> mHomeData = new ArrayList<>();
        for (int i = 0; i < titles.length; i++)
        {
            mHomeData.add(new HomeIconInfo(titles[i], img[i]));
        }
        List<View> mViews = new ArrayList<>();
        gridView1.setAdapter(new MyGridViewAdapter(mHomeData, getActivity()));
        gridView2.setAdapter(new MyGridViewAdapter(mHomeData, getActivity()));
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                switch (i)
                {
                    case 7://砍价
                        Intent intent = new Intent(getActivity(), KanJiaListActivity.class);
                        startActivity(intent);
                        break;
                    case 1://拼车
                        Intent intent1 = new Intent(getActivity(), PinCheActivity.class);
                        startActivity(intent1);
                        break;
                }
            }
        });
        mViews.add(page1);
        mViews.add(page2);
        homeViewpage.setAdapter(new MyPagerAdapter(mViews));
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


    public class MyPagerAdapter extends PagerAdapter
    {

        //存放外界传来的集合数据
        private List<View> mViews = new ArrayList<>();

        //构造方法,进行容器数据的初始化,必须把外界的数据传进来,让ViewPager进行加载显示
        //提示:有些参数没有数据,但是代码中用到了,第一个想到的构造方法,传数据
        public MyPagerAdapter(List<View> views)
        {
            mViews = views;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            //从容器里拿数据
            View view = mViews.get(position);
            //把控件对象放入ViewPager的容器里,进行显示
            container.addView(view);
            //把控件显示出来,方便销毁
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            //把不用的View对象销毁,防止内存泄漏
            container.removeView(mViews.get(position));
        }

        @Override
        public int getCount()
        {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object)
        {
            return view == object;
        }
    }
}
