package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/7/14.
 */

public class BankAdapter extends BaseQuickAdapter<BankBean>
{
    public BankAdapter(Context context, int layoutResId, List<BankBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, BankBean bankBean)
    {

    }
}
