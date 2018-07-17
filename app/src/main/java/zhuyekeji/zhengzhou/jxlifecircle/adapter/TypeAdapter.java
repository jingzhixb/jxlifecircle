package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.bean.TypeShopBean;

/**
 * Created by Administrator on 2018/7/3.
 */

public class TypeAdapter extends BaseQuickAdapter<TypeShopBean>
{
    public TypeAdapter(Context context, int layoutResId, List<TypeShopBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, TypeShopBean s)
    {
       baseViewHolder.setText(R.id.name,s.getClass_name());
    }
}
