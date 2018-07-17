package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.bean.MyCouPon;

/**
 * Created by Administrator on 2018/6/11.
 */

public class YouHuiAdapter extends BaseQuickAdapter<MyCouPon>
{
    public YouHuiAdapter(Context context, int layoutResId, List<MyCouPon> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MyCouPon youHuiBean)
    {

    }
}
