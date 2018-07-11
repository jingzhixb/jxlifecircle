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
import zhuyekeji.zhengzhou.jxlifecircle.adapter.ShopDaiXiaoFeiAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseFragment;
import zhuyekeji.zhengzhou.jxlifecircle.bean.UserOrderYOuHuiBean;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

/**
 * Created by Administrator on 2018/6/9.
 */

public class ShopTuiKuanFrament extends BaseFragment
{

    //商家订单申请退款
    @BindView(R.id.rv_shop)
    RecyclerView rvShop;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    Unbinder unbinder;
    //商家
    private View view;
    private ShopDaiXiaoFeiAdapter adapter;
    private List<UserOrderYOuHuiBean> beans;

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
        JxApiCallBack.shoporder_you(getToken(), "3", "", 1, getActivity(), callBack);
    }

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
                        List list= Arrays.asList(new Gson().fromJson(JsonUtile.getbody(result.body()),UserOrderYOuHuiBean[].class));
                        beans=new ArrayList<>(list);
                        rvShop.setLayoutManager(new LinearLayoutManager(getActivity()));
                        adapter=new ShopDaiXiaoFeiAdapter(getActivity(),R.layout.shop_daixiaofei_item,beans,1);
                        rvShop.setAdapter(adapter);
                        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener()
                        {
                            @Override
                            public void onItemClick(View view, int i)
                            {
                                Intent intent=new Intent(getActivity(),OrderDelitActivity.class);
                                intent.putExtra("type","1");
                                startActivity(intent);
                            }
                        });
                        adapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener()
                        {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i)
                            {
                                switch (view.getId())
                                {
                                    case R.id.bt:
                                        Intent intent=new Intent(getActivity(),OrderDelitActivity.class);
                                        intent.putExtra("type","2");
                                        startActivity(intent);
                                        break;
                                }
                            }
                        });
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
}
