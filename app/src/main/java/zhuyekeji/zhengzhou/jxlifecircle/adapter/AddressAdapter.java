package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.bean.AddressBean;

/**
 * Created by Administrator on 2018/6/8.
 */

public class AddressAdapter extends BaseQuickAdapter<AddressBean>
{
    public AddressAdapter(Context context, int layoutResId, List<AddressBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, AddressBean addressBean)
    {

    }
}
