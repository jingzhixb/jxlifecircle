package zhuyekeji.zhengzhou.jxlifecircle.frament;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.TouPiaoAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseFragment;
import zhuyekeji.zhengzhou.jxlifecircle.bean.JiFenOrderBean;
import zhuyekeji.zhengzhou.jxlifecircle.homeacyivity.TouPiaoDeliteActivity;

/**
 * Created by Administrator on 2018/6/8.
 */

public class TouPiaoNewFrament extends BaseFragment
{
    @BindView(R.id.rv_shop)
    RecyclerView rvShop;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    Unbinder unbinder;
    private View view;
    private List<JiFenOrderBean> beans = new ArrayList<>();

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container)
    {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.utils_item, null);
        return view;
    }

    @Override
    protected void initListener()
    {
        for (int i = 0; i < 10; i++)
        {
            beans.add(new JiFenOrderBean());
        }
        TouPiaoAdapter adapter = new TouPiaoAdapter(getActivity(), R.layout.toupiao_item, beans);
            rvShop.setLayoutManager(new GridLayoutManager(getActivity(),2));
            rvShop.setAdapter(adapter);
            adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener()
            {
                @Override
                public void onItemClick(View view, int i)
                {
                    Intent intent=new Intent(getActivity(),TouPiaoDeliteActivity.class);
                    startActivity(intent);
                }
            });
    }

    @Override
    protected void initData()
    {

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
}
