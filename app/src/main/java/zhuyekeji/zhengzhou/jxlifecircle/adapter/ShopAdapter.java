package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.App;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.bean.MyConllect;

/**
 * Created by Administrator on 2018/6/8.
 */

public class ShopAdapter extends BaseQuickAdapter<MyConllect>
{
    public ShopAdapter(Context context, int layoutResId, List<MyConllect> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MyConllect s)
    {
        baseViewHolder.setText(R.id.title,s.getGoods_name());
        baseViewHolder.setText(R.id.address,s.getGoods_name());
        baseViewHolder.setText(R.id.price,s.getPrice());
        Glide.with(App.getInstance()).load(s.getPic()).into((ImageView)baseViewHolder.getView(R.id.image));
    }
}
