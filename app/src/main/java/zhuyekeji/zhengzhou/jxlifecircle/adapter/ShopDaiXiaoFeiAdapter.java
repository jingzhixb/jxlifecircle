package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.App;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.bean.JiFenOrderBean;
import zhuyekeji.zhengzhou.jxlifecircle.bean.UserOrderYOuHuiBean;

/**
 * Created by Administrator on 2018/6/25.
 */

public class ShopDaiXiaoFeiAdapter extends BaseQuickAdapter<UserOrderYOuHuiBean>
{
    private int type;
    public ShopDaiXiaoFeiAdapter(Context context, int layoutResId, List<UserOrderYOuHuiBean> data,int type)
    {
        super(context, layoutResId, data);
        this.type=type;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, UserOrderYOuHuiBean order)
    {
        if (type==1)
        {
            baseViewHolder.getView(R.id.bt).setVisibility(View.GONE);
        }else if (type==2)
        {
            baseViewHolder.getView(R.id.bt).setVisibility(View.VISIBLE);
            baseViewHolder.setText(R.id.bt,"删除订单");
            baseViewHolder.setOnClickListener(R.id.bt,new OnItemChildClickListener());
        }else if (type==3)
        {
            baseViewHolder.getView(R.id.bt).setVisibility(View.VISIBLE);
            baseViewHolder.setText(R.id.bt,"退款原因");
            baseViewHolder.setOnClickListener(R.id.bt,new OnItemChildClickListener());
        }
        baseViewHolder.setText(R.id.tv_title,order.getTitle());
        baseViewHolder.setText(R.id.tv_price,"￥"+order.getTotalprice());
        baseViewHolder.setText(R.id.tv_bianhao,"订单编号:"+order.getOrder_num());
        baseViewHolder.setText(R.id.tv_time,order.getCreatedtime());
        Glide.with(App.getInstance()).load(order.getPic()).into((ImageView)baseViewHolder.getView(R.id.img));
    }

}
