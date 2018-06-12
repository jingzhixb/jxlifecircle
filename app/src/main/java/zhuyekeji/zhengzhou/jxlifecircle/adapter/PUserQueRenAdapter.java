package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.bean.JiFenOrderBean;

/**
 * Created by Administrator on 2018/6/12.
 */

public class PUserQueRenAdapter extends BaseQuickAdapter<JiFenOrderBean>
{
    public PUserQueRenAdapter(Context context, int layoutResId, List<JiFenOrderBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, JiFenOrderBean jiFenOrderBean)
    {

    }
}
