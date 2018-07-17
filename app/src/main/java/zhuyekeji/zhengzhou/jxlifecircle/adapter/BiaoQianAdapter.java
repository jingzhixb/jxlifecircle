package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.bean.LabelBean;

/**
 * Created by Administrator on 2018/6/26.
 */

public class BiaoQianAdapter extends BaseQuickAdapter<LabelBean>
{
    private int type;
    public BiaoQianAdapter(Context context, int layoutResId, List<LabelBean> data,int type)
    {
        super(context, layoutResId, data);
        this.type=type;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, LabelBean s)
    {
        baseViewHolder.setOnClickListener(R.id.im_delete,new OnItemChildClickListener());
       if (type==1)
       {
           baseViewHolder.getView(R.id.im_delete).setVisibility(View.VISIBLE);
       }else {
           baseViewHolder.getView(R.id.im_delete).setVisibility(View.GONE);
       }
       baseViewHolder.setText(R.id.name,s.getLabel());
    }
}
