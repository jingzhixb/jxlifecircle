package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.bean.MeBean;

/**
 * Created by Administrator on 2018/6/7.
 */

public class MeAdapter extends BaseQuickAdapter<MeBean>
{


    public MeAdapter(Context context, int layoutResId, List<MeBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MeBean meBean)
    {
        baseViewHolder.setImageResource(R.id.image,meBean.getImage());
        baseViewHolder.setText(R.id.text,meBean.getText());
    }
}
