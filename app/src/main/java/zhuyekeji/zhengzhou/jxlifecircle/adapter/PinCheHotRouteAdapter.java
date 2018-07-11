package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.bean.HotRouteBean;

/**
 * Created by Administrator on 2018/6/28.
 */

public class PinCheHotRouteAdapter extends BaseQuickAdapter<HotRouteBean>
{
    public PinCheHotRouteAdapter(Context context, int layoutResId, List<HotRouteBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HotRouteBean s)
    {
       baseViewHolder.setText(R.id.start,s.getSetout());
        baseViewHolder.setText(R.id.end,s.getDestination());
    }
}
