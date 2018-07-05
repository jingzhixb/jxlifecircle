package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.bean.PinCheBean;

/**
 * Created by Administrator on 2018/7/4.
 */

public class PinCheAdapter extends BaseQuickAdapter<PinCheBean>
{
    public PinCheAdapter(Context context, int layoutResId, List<PinCheBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, PinCheBean pinCheBean)
    {
        //1是车找人，2是人找车，3是天天拼车
        if (pinCheBean.getType().equals("1"))
        {
           baseViewHolder.setBackgroundColor(R.id.qi,R.color.orange);
            baseViewHolder.setBackgroundColor(R.id.end,R.color.orange);
            baseViewHolder.setBackgroundColor(R.id.type,R.color.orange);
          baseViewHolder.setText(R.id.start,pinCheBean.getSetout());
            baseViewHolder.setText(R.id.zhongdian,pinCheBean.getDestination());
            baseViewHolder.setText(R.id.time,pinCheBean.getTime());
            baseViewHolder.setText(R.id.content,pinCheBean.getOth_requst());
        }else if (pinCheBean.getType().equals("2"))
        {
            baseViewHolder.setBackgroundColor(R.id.qi,R.color.jxcolor);
            baseViewHolder.setBackgroundColor(R.id.end,R.color.jxcolor);
            baseViewHolder.setBackgroundColor(R.id.type,R.color.jxcolor);
            baseViewHolder.setText(R.id.start,pinCheBean.getSetout());
            baseViewHolder.setText(R.id.zhongdian,pinCheBean.getDestination());
            baseViewHolder.setText(R.id.time,pinCheBean.getTime());
            baseViewHolder.setText(R.id.content,pinCheBean.getOth_requst());
        }else if (pinCheBean.getType().equals("3"))
        {
            baseViewHolder.setText(R.id.start,pinCheBean.getSetout());
            baseViewHolder.setText(R.id.zhongdian,pinCheBean.getDestination());
            baseViewHolder.setText(R.id.time,pinCheBean.getTime());
            baseViewHolder.setText(R.id.content,pinCheBean.getOth_requst());
        }
    }
}
