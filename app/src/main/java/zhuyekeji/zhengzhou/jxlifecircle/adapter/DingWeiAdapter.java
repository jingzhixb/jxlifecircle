package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.bean.PoiBean;

/**
 * Created by Administrator on 2018/6/29.
 */

public class DingWeiAdapter extends BaseQuickAdapter<PoiBean>
{
    public DingWeiAdapter(Context context, int layoutResId, List<PoiBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, PoiBean s)
    {
        baseViewHolder.setText(R.id.tv_address,s.getAddress());
    }
}
