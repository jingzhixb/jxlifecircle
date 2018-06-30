package zhuyekeji.zhengzhou.jxlifecircle.frament.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseFragment;

/**
 * Created by Administrator on 2018/6/8.
 */

public class TouPaioAllFrament extends BaseFragment
{
    private View view;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container)
    {
        view=LayoutInflater.from(getActivity()).inflate(R.layout.utils_item,null);
        return view;
    }

    @Override
    protected void initListener()
    {

    }

    @Override
    protected void initData()
    {

    }
}
