package zhuyekeji.zhengzhou.jxlifecircle.frament.user;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.FaBuAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.PinCheAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseFragment;
import zhuyekeji.zhengzhou.jxlifecircle.bean.FaBuBean;
import zhuyekeji.zhengzhou.jxlifecircle.bean.PinCheBean;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

/**
 * Created by Administrator on 2018/7/16.
 */

public class FaBuPinCheFrament extends BaseFragment
{
    @BindView(R.id.rv_shop)
    SwipeMenuRecyclerView rvShop;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    Unbinder unbinder;
    private PinCheAdapter adapter;
    private View view;
    private int position;
    private List<PinCheBean> beans = new ArrayList<>();
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container)
    {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.shop_frament, null);
        return view;
    }

    // 创建菜单：
    SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator()
    {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType)
        {
            SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity()); // 各种文字和图标属性设置。
            deleteItem.setText("删除");
            deleteItem.setBackground(R.color.red);
            deleteItem.setWidth(250);
            deleteItem.setHeight(-1);
            //  deleteItem.setWidth(-1);
            rightMenu.addMenuItem(deleteItem); // 在Item左侧添加一个菜单。


//            SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity()); // 各种文字和图标属性设置。
//            leftMenu.addMenuItem(deleteItem); // 在Item右侧添加一个菜单。

            // 注意：哪边不想要菜单，那么不要添加即可。
        }
    };
    SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener()
    {


        @Override
        public void onItemClick(SwipeMenuBridge menuBridge)
        {
            // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
            menuBridge.closeMenu();
            position = menuBridge.getPosition();
            ToastUtils.showShort("点击了侧滑菜单");
            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
            JxApiCallBack.delfabu(getToken(),"4",adapter.getItem(position).getId(),2,getActivity(),callBack);
        }
    };


    @Override
    protected void initListener()
    {

        rvShop.setSwipeMenuCreator(mSwipeMenuCreator);
        rvShop.setSwipeMenuItemClickListener(mMenuItemClickListener);
        rvShop.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvShop.setItemAnimator(new DefaultItemAnimator());
        refreshlayout.setOnRefreshListener(new OnRefreshListener()
        {
            @Override
            public void onRefresh(RefreshLayout refreshlayout)
            {
                JxApiCallBack.myfabu(getToken(), "4", 1, getActivity(), callBack);
                refreshlayout.finishRefresh(5000,false);
            }
        });

    }


    @Override
    protected void initData()
    {
        JxApiCallBack.myfabu(getToken(), "4", 1, getActivity(), callBack);
    }

    CallBack callBack = new CallBack()
    {
        @Override
        public void onSuccess(int what, Response<String> result)
        {
           switch (what)
           {
               case 1:
                   if (JsonUtile.isTrue(result.body()))
                   {
                       refreshlayout.finishRefresh();
                       List list= Arrays.asList(new Gson().fromJson(JsonUtile.getbody(result.body()),PinCheBean[].class));
                       beans.addAll(list);
                       for (int i=0;i<beans.size();i++)
                       {
                           if (beans.get(i).getType().equals("人找车"))
                           {
                               beans.get(i).setType("1");
                           }else if (beans.get(i).getType().equals("车找人"))
                           {
                               beans.get(i).setType("2");
                           }else if (beans.get(i).getType().equals("天天拼车"))
                           {
                               beans.get(i).setType("3");
                           }
                       }
                       adapter=new PinCheAdapter(getActivity(),R.layout.pinche_item,beans);
                       adapter.notifyDataSetChanged();
                       rvShop.setAdapter(adapter);
                   } else
                   {
                       ToastUtils.showShort(JsonUtile.getresulter(result.body()));
                   }
                   break;
               case 2:
                   if (JsonUtile.isTrue(result.body()))
                   {
                       ToastUtils.showShort(JsonUtile.getresulter(result.body()));
                       adapter.remove(position);
                   }else {
                       ToastUtils.showShort(JsonUtile.getresulter(result.body()));
                   }
                   break;
           }
        }

        @Override
        public void onFail(int what, Response<String> result)
        {
            refreshlayout.finishRefresh();
        }

        @Override
        public void onFinish(int what)
        {
            refreshlayout.finishRefresh();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // TODO: inflate a fragment view
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
