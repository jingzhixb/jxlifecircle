package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.bean.ShopTypeBean;

/**
 * Created by Administrator on 2018/7/2.
 */

public class YIJiAdapter extends BaseQuickAdapter<ShopTypeBean>
{
    public YIJiAdapter(Context context, int layoutResId, List<ShopTypeBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ShopTypeBean s)
    {
          baseViewHolder.setText(R.id.name,s.getClass_name());
          if (s.getIs_show()==1)
          {
              baseViewHolder.getView(R.id.view).setVisibility(View.VISIBLE);
          }else {
              baseViewHolder.getView(R.id.view).setVisibility(View.GONE);
          }
    }
}
