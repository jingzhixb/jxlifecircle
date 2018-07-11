package zhuyekeji.zhengzhou.jxlifecircle.frament.user;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
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
import zhuyekeji.zhengzhou.jxlifecircle.adapter.ShopAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseFragment;
import zhuyekeji.zhengzhou.jxlifecircle.bean.MyConllect;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

/**
 * Created by Administrator on 2018/6/8.
 */

public class ShopFrament extends BaseFragment
{
    @BindView(R.id.rv_shop)
    SwipeMenuRecyclerView rvShop;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    Unbinder unbinder;
    private View view;
    private List<MyConllect> beans;
    private ShopAdapter adapter;
    private int position;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container)
    {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.shop_frament, null);
        return view;
    }

    // 创建菜单：
    SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
            SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity()); // 各种文字和图标属性设置。
            deleteItem.setText("删除");
            deleteItem.setBackground(R.color.red);
            deleteItem.setWidth(250);
            deleteItem.setHeight(300);
            rightMenu.addMenuItem(deleteItem); // 在Item左侧添加一个菜单。

//            SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity()); // 各种文字和图标属性设置。
//            leftMenu.addMenuItem(deleteItem); // 在Item右侧添加一个菜单。

            // 注意：哪边不想要菜单，那么不要添加即可。
        }
    };
    SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {



        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
            menuBridge.closeMenu();
            position = menuBridge.getPosition();
            ToastUtils.showShort("点击了侧滑菜单");
            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
            JxApiCallBack.delete_conllect(getToken(),adapter.getItem(position).getId(),2,getActivity(),callBack);
        }
    };
    @Override
    protected void initListener()
    {
        rvShop.setSwipeMenuCreator(mSwipeMenuCreator);
        rvShop.setSwipeMenuItemClickListener(mMenuItemClickListener);
        rvShop.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvShop.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void initData()
    {
        JxApiCallBack.myshoucang(getToken(),"1",1,getActivity(),callBack);
    }
    CallBack callBack=new CallBack()
    {
        @Override
        public void onSuccess(int what, Response<String> result)
        {
          switch (what)
          {
              case 1:
                  if (JsonUtile.getCode(result.body()).equals("200"))
                  {
                      List list= Arrays.asList(new Gson().fromJson(JsonUtile.getbody(result.body()),MyConllect[].class));
                      beans=new ArrayList<>(list);
                      adapter = new ShopAdapter(getActivity(), R.layout.shop_item,beans);
                      rvShop.setAdapter(adapter);
                      adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener()
                      {
                          @Override
                          public void onItemClick(View view, int i)
                          {
                              ToastUtils.showShort("点击了"+i);

                          }
                      });
                  }else {
                      ToastUtils.showShort(JsonUtile.getresulter(result.body()));
                  }
                  break;
              case 2:
                  if (JsonUtile.getCode(result.body()).equals("200"))
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

        }

        @Override
        public void onFinish(int what)
        {

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
