package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.bean.YouHuiBean;

/**
 * Created by Administrator on 2018/6/11.
 */

public class YouHuiAdapter extends BaseQuickAdapter<YouHuiBean>
{
    public YouHuiAdapter(Context context, int layoutResId, List<YouHuiBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, YouHuiBean youHuiBean)
    {

    }
}
