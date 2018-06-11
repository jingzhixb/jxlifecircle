package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.bean.JiLuBean;

/**
 * Created by Administrator on 2018/6/8.
 */

public class JiLuAdapter extends BaseQuickAdapter<JiLuBean>
{
    public JiLuAdapter(Context context, int layoutResId, List<JiLuBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, JiLuBean jiLuBean)
    {

    }
}
