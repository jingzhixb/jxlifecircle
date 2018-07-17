package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.bean.JiFenOrderBean;
import zhuyekeji.zhengzhou.jxlifecircle.bean.UserJiFenShopBean;

/**
 * Created by Administrator on 2018/6/12.
 */

public class JiFenOrderAdapter extends BaseQuickAdapter<UserJiFenShopBean>
{
    public JiFenOrderAdapter(Context context, int layoutResId, List<UserJiFenShopBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, UserJiFenShopBean s)
    {
        baseViewHolder.setText(R.id.tv_title, s.getName());
        baseViewHolder.setText(R.id.jifen, s.getJifen());

    }
}
