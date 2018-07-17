package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.App;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.bean.ErJiBean;
import zhuyekeji.zhengzhou.jxlifecircle.bean.JiFenOrderBean;

/**
 * Created by Administrator on 2018/7/2.
 */

public class ErJiAdapter extends BaseQuickAdapter<ErJiBean>
{
    public ErJiAdapter(Context context, int layoutResId, List<ErJiBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ErJiBean s)
    {
        Glide.with(App.getInstance()).load(s.getPic()).into((ImageView)baseViewHolder.getView(R.id.im_img));
        baseViewHolder.setText(R.id.tv_name,s.getTitle());
        baseViewHolder.setText(R.id.desc,s.getPrice());
        baseViewHolder.setText(R.id.tv_price,s.getPrice());
       baseViewHolder.setOnClickListener(R.id.tv_bianji,new OnItemChildClickListener());
        baseViewHolder.setOnClickListener(R.id.tv_xiajia,new OnItemChildClickListener());
    }
}
