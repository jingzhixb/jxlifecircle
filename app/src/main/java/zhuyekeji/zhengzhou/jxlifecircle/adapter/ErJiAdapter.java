package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.bean.ErJiBean;
import zhuyekeji.zhengzhou.jxlifecircle.bean.JiFenOrderBean;

/**
 * Created by Administrator on 2018/7/2.
 */

public class ErJiAdapter extends BaseQuickAdapter<ErJiBean>
{
    public ErJiAdapter(Context context, int layoutResId, List<ErJiBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ErJiBean jiFenOrderBean)
    {

    }
}
