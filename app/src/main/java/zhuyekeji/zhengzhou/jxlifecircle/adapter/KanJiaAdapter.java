package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.bean.JiFenOrderBean;
import zhuyekeji.zhengzhou.jxlifecircle.bean.KanJiaBean;

/**
 * Created by Administrator on 2018/6/26.
 */

public class KanJiaAdapter extends BaseQuickAdapter<KanJiaBean>
{
    public KanJiaAdapter(Context context, int layoutResId, List<KanJiaBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, KanJiaBean jiFenOrderBean)
    {

    }
}
