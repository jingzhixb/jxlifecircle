package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.bean.ZhiCHuBean;

/**
 * Created by Administrator on 2018/6/8.
 */

public class ZhiChuAdapter extends BaseQuickAdapter<ZhiCHuBean>
{
    public ZhiChuAdapter(Context context, int layoutResId, List<ZhiCHuBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ZhiCHuBean zhiCHuBean)
    {

    }
}
