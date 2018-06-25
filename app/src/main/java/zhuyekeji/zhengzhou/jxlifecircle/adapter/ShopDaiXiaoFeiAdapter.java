package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.bean.JiFenOrderBean;

/**
 * Created by Administrator on 2018/6/25.
 */

public class ShopDaiXiaoFeiAdapter extends BaseQuickAdapter<JiFenOrderBean>
{
    public ShopDaiXiaoFeiAdapter(Context context, int layoutResId, List<JiFenOrderBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, JiFenOrderBean jiFenOrderBean)
    {
        baseViewHolder.setText(R.id.tv_time,"2018-44");
    }

}
