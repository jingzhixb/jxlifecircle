package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.bean.ShopTypeBean;

/**
 * Created by Administrator on 2018/7/2.
 */

public class YIJiAdapter extends BaseQuickAdapter<ShopTypeBean>
{
    public YIJiAdapter(Context context, int layoutResId, List<ShopTypeBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ShopTypeBean s)
    {

    }
}
