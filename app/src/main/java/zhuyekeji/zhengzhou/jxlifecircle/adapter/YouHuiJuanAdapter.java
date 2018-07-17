package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.bean.JiFenOrderBean;
import zhuyekeji.zhengzhou.jxlifecircle.bean.YouHuiBean;

/**
 * Created by Administrator on 2018/6/26.
 */

public class YouHuiJuanAdapter extends BaseQuickAdapter<YouHuiBean>
{
    public YouHuiJuanAdapter(Context context, int layoutResId, List<YouHuiBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, YouHuiBean bean)
    {
       baseViewHolder.setText(R.id.tv_provice,bean.getCoupon_money());
       baseViewHolder.setOnClickListener(R.id.tv_bianji,new OnItemChildClickListener());
        baseViewHolder.setOnClickListener(R.id.xiajia,new OnItemChildClickListener());
        baseViewHolder.setOnClickListener(R.id.tv_shanchu,new OnItemChildClickListener());
    }
}
