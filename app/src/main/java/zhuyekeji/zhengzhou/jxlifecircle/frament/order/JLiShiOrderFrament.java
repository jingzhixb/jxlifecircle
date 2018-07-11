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
import zhuyekeji.zhengzhou.jxlifecircle.adapter.UserDaiXiafeiAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseFragment;
import zhuyekeji.zhengzhou.jxlifecircle.bean.UserOrderYOuHuiBean;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

/**
 * Created by Administrator on 2018/6/9.
 */

public class JLiShiOrderFrament extends BaseFragment
{
    @BindView(R.id.rv_shop)
    RecyclerView rvShop;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    Unbinder unbinder;
    //用户优惠购历史
    private View view;
    private List<UserOrderYOuHuiBean> beans;
    private UserDaiXiafeiAdapter adapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container)
    {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.utils_item, null);
        return view;
    }

    @Override
    protected void initListener()
    {
        rvShop.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData()
    {
        JxApiCallBack.order_youhui(getToken(), 3, 1, getActivity(), callBack);
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
    CallBack callBack = new CallBack()
    {
        @Override
        public void onSuccess(int what, Response<String> result)
        {
            if (JsonUtile.getCode(result.body()).equals("200"))
            {
                List list = Arrays.asList(new Gson().fromJson(JsonUtile.getbody(result.body()), UserOrderYOuHuiBean[].class));
                beans = new ArrayList<>(list);
                adapter = new UserDaiXiafeiAdapter(getActivity(), R.layout.shop_daixiaofei_item, beans, 3);
                rvShop.setAdapter(adapter);
            } else
            {
                ToastUtils.showShort(JsonUtile.getresulter(result.body()));
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
