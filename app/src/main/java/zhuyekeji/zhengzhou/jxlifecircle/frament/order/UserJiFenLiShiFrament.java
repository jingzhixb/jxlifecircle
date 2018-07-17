package zhuyekeji.zhengzhou.jxlifecircle.frament.order;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import zhuyekeji.zhengzhou.jxlifecircle.adapter.JiFenOrderAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseFragment;
import zhuyekeji.zhengzhou.jxlifecircle.bean.UserJiFenShopBean;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;

/**
 * Created by Administrator on 2018/6/9.
 */

public class UserJiFenLiShiFrament extends BaseFragment
{
    //用户积分历史订单
    @BindView(R.id.rv_shop)
    RecyclerView rvShop;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    Unbinder unbinder;
    //用户积分 待确认订单
    private JiFenOrderAdapter adapter;
    private View view;
    private List<UserJiFenShopBean> beans = new ArrayList<>();

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
        JxApiCallBack.jifenshop(getToken(), 3, 1, getActivity(), callBack);
        adapter = new JiFenOrderAdapter(getActivity(), R.layout.jifenorder_item, beans);
        rvShop.setAdapter(adapter);
        rvShop.setLayoutManager(new LinearLayoutManager(getActivity()));
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
                        List list = Arrays.asList(new Gson().fromJson(JsonUtile.getbody(result.body()), UserJiFenShopBean[].class));
                        beans.addAll(list);
                        adapter.notifyDataSetChanged();
                    } else
                    {

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
