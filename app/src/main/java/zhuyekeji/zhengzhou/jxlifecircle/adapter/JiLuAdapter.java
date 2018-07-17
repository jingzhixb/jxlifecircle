package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.bean.JiLuBean;
import zhuyekeji.zhengzhou.jxlifecircle.bean.TiXianBean;

/**
 * Created by Administrator on 2018/6/8.
 */

public class JiLuAdapter extends BaseQuickAdapter<TiXianBean>
{
    public JiLuAdapter(Context context, int layoutResId, List<TiXianBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, TiXianBean jiLuBean)
    {
       if (jiLuBean.getType().equals("1"))
       {
           baseViewHolder.setText(R.id.zhifu,"充值");
       }else if (jiLuBean.getType().equals("2"))
       {
           baseViewHolder.setText(R.id.zhifu,"提现");
       }
       baseViewHolder.setText(R.id.time,jiLuBean.getDate());
        baseViewHolder.setText(R.id.money,jiLuBean.getMoney());
    }
}
