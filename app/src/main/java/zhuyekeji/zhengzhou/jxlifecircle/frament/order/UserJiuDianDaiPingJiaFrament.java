package zhuyekeji.zhengzhou.jxlifecircle.frament.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.PUserDaiJieAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.PUserQueRenAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseFragment;
import zhuyekeji.zhengzhou.jxlifecircle.bean.OrderJIudianBean;
import zhuyekeji.zhengzhou.jxlifecircle.orderactivity.UserPingJiaActivity;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;

/**
 * Created by Administrator on 2018/6/9.
 */

public class UserJiuDianDaiPingJiaFrament extends BaseFragment
{
    @BindView(R.id.rv_shop)
    RecyclerView rvShop;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    Unbinder unbinder;
    private View view;
    private PUserDaiJieAdapter adapter;
    private List<OrderJIudianBean> beans;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container)
    {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.utils_item, null);
        return view;
    }

    @Override
    protected void initListener()
    {

    }

    @Override
    protected void initData()
    {
        JxApiCallBack.order_jiudian(getToken(), 3, 1, getActivity(), callBack);
    }

    CallBack callBack = new CallBack()
    {
        @Override
        public void onSuccess(final int what, Response<String> result)
        {
            if (JsonUtile.getCode(result.body()).equals("200"))
            {
                List list = Arrays.asList(new Gson().fromJson(JsonUtile.getbody(result.body()), OrderJIudianBean[].class));
                beans = new ArrayList<>(list);
                adapter = new PUserDaiJieAdapter(getActivity(), R.layout.item_jiudian_order, beans,3);
                rvShop.setLayoutManager(new LinearLayoutManager(getActivity()));
                rvShop.setAdapter(adapter);
                adapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener()
                {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i)
                    {
                        switch (what)
                        {
                            case R.id.bt:
                                Intent intent=new Intent(getActivity(), UserPingJiaActivity.class);
                                intent.putExtra("orderid",adapter.getItem(i).getId());
                                startActivity(intent);
                                break;
                        }
                    }
                });
            } else
            {

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
