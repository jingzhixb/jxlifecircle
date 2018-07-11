package zhuyekeji.zhengzhou.jxlifecircle.frament.user;

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
import zhuyekeji.zhengzhou.jxlifecircle.activity.OrderDelitActivity;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.ShopJiuDianAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseFragment;
import zhuyekeji.zhengzhou.jxlifecircle.bean.OrderJIudianBean;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

/**
 * Created by Administrator on 2018/6/9.
 */

public class ShopTuiKuan2Frament extends BaseFragment
{
    @BindView(R.id.rv_shop)
    RecyclerView rvShop;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    Unbinder unbinder;
    //商家酒店
    private View view;
    private ShopJiuDianAdapter adapter;

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
        JxApiCallBack.shopjiudian_order(getToken(), "4", "", 1, getActivity(), callBack);
    }

    CallBack callBack = new CallBack()
    {
        @Override
        public void onSuccess(int what, Response<String> result)
        {
            switch (what)
            {
                case 1:
                    if (JsonUtile.getCode(result.body()).equals("200"))
                    {
                        List list = Arrays.asList(new Gson().fromJson(JsonUtile.getbody(result.body()), OrderJIudianBean[].class));
                        beans = new ArrayList<>(list);
                        adapter = new ShopJiuDianAdapter(getActivity(), R.layout.item_jiudian_order, beans, 4);
                        rvShop.setLayoutManager(new LinearLayoutManager(getActivity()));
                        rvShop.setAdapter(adapter);
                        adapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener()
                        {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i)
                            {
                                switch (view.getId())
                                {
                                    case R.id.bt://退款
                                        Intent intent=new Intent(getActivity(), OrderDelitActivity.class);
                                        intent.putExtra("type","2");
                                        startActivity(intent);
                                        break;
                                }
                            }
                        });
                    } else
                    {
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
