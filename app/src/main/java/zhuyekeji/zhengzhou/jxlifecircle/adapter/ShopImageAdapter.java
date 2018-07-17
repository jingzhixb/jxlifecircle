package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.App;
import zhuyekeji.zhengzhou.jxlifecircle.R;

/**
 * Created by Administrator on 2018/7/13.
 */

public class ShopImageAdapter extends BaseQuickAdapter<String>
{
    public ShopImageAdapter(Context context, int layoutResId, List<String> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s)
    {
        baseViewHolder.setOnClickListener(R.id.del,new OnItemChildClickListener());
        Glide.with(App.getInstance()).load(s).into((ImageView)baseViewHolder.getView(R.id.im));
    }
}
