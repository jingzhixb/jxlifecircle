package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.bean.MyJiFenBean;

/**
 * Created by Administrator on 2018/7/16.
 */

public class MyJiFenAfapter extends BaseQuickAdapter<MyJiFenBean>
{
    public MyJiFenAfapter(Context context, int layoutResId, List<MyJiFenBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MyJiFenBean s)
    {
      if (s.getType().equals("1"))
      {
       baseViewHolder.setText(R.id.type,"购物获得积分");
      }else if (s.getType().equals("2"))
      {
          baseViewHolder.setText(R.id.type,"积分兑换");
      }else if (s.getType().equals("3"))
      {
          baseViewHolder.setText(R.id.type,"积分签到");
      }else if (s.getType().equals("4"))
      {
          baseViewHolder.setText(R.id.type,"应用分享得积分");
      }

      baseViewHolder.setText(R.id.time,s.getThedate());
      baseViewHolder.setText(R.id.tv_shop_jifen,s.getJifen());
    }
}
