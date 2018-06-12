package zhuyekeji.zhengzhou.jxlifecircle.frament.order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.PUserDaiJieAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseFragment;

/**
 * Created by Administrator on 2018/6/9.
 */

public class PUserDaiJIeDanFrament extends BaseFragment
{
    private PUserDaiJieAdapter adapter;
    private View view;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container)
    {
        view=LayoutInflater.from(getActivity()).inflate(R.layout.shop_frament,null);
        return view;
    }

    @Override
    protected void initListener()
    {
adapter=new PUserDaiJieAdapter(getActivity(),R.layout.puserdaijie_item,null);
    }

    @Override
    protected void initData()
    {

    }
}
