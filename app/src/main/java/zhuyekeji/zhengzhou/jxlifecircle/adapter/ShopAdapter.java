package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.R;

/**
 * Created by Administrator on 2018/6/8.
 */

public class ShopAdapter extends BaseQuickAdapter<String>
{
    public ShopAdapter(Context context, int layoutResId, List<String> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s)
    {
        baseViewHolder.setText(R.id.text,s);
    }
}
