package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.bean.ShopType2Bean;

/**
 * Created by Administrator on 2018/7/10.
 */

public class ShopTypeAdapter extends BaseQuickAdapter<ShopType2Bean>
{
    public ShopTypeAdapter(Context context, int layoutResId, List<ShopType2Bean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ShopType2Bean shopType2Bean)
    {
      baseViewHolder.setText(R.id.tv_type,shopType2Bean.getType());
    }
}
