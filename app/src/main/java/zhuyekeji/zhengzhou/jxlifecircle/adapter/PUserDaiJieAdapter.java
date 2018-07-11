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
import zhuyekeji.zhengzhou.jxlifecircle.bean.OrderJIudianBean;

/**
 * Created by Administrator on 2018/6/12.
 */

public class PUserDaiJieAdapter extends BaseQuickAdapter<OrderJIudianBean>
{
    private int type;
    public PUserDaiJieAdapter(Context context, int layoutResId, List<OrderJIudianBean> data,int type)
    {
        super(context, layoutResId, data);
        this.type=type;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, OrderJIudianBean order)
    {
        if (type==1||type==4)
        {
            baseViewHolder.getView(R.id.bt).setVisibility(View.GONE);
            if (type==4&&true)//
            {
              baseViewHolder.setText(R.id.bt,"退款申请中...");
              baseViewHolder.setTextColor(R.id.bt,R.color.jxcolor);
              baseViewHolder.setBackgroundRes(R.id.bt,R.color.white);
            }
        }else {
            baseViewHolder.getView(R.id.bt).setVisibility(View.VISIBLE);
            baseViewHolder.setOnClickListener(R.id.bt,new OnItemChildClickListener());
            if (type==2)
            {
                baseViewHolder.setText(R.id.bt,"申请退款");
            }else if (type==3)
            {
                baseViewHolder.setText(R.id.bt,"评价");
            }else if (type==5)
            {
                baseViewHolder.setText(R.id.bt,"删除订单");
            }
        }
        baseViewHolder.setText(R.id.tv_title,order.getTitle());
        baseViewHolder.setText(R.id.tv_price,order.getTotalprice());
       // baseViewHolder.setText(R.id.tv_geshu,order.getTitle());
        baseViewHolder.setText(R.id.tv_bianhao,order.getOrder_num());
        baseViewHolder.setText(R.id.tv_time,order.getCreatedtime());
        baseViewHolder.setText(R.id.start_time,order.getCome_time());
        baseViewHolder.setText(R.id.end_time,order.getLeave_time());
        baseViewHolder.setText(R.id.tv_prices,order.getTotalprice());
        if (order.getPic()!=null)
        {
            Glide.with(App.getInstance()).load(order.getPic()).into((ImageView)baseViewHolder.getView(R.id.img));
        }
    }
}
