package zhuyekeji.zhengzhou.jxlifecircle.frament.order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.PUserTuiKuanAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseFragment;

/**
 * Created by Administrator on 2018/6/9.
 */

public class PUserTuiKuanFrament extends BaseFragment
{
    private View view;
    private PUserTuiKuanAdapter adapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container)
    {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.shop_frament, null);
        return view;
    }

    @Override
    protected void initListener()
    {

    }

    @Override
    protected void initData()
    {
        adapter = new PUserTuiKuanAdapter(getActivity(), R.layout.pusertuikuan_item, null);
    }
}
