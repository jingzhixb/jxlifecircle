package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.R;

/**
 * Created by Administrator on 2018/6/26.
 */

public class BiaoQianAdapter extends BaseQuickAdapter<String>
{
    private int type;
    public BiaoQianAdapter(Context context, int layoutResId, List<String> data,int type)
    {
        super(context, layoutResId, data);
        this.type=type;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s)
    {
       if (type==1)
       {
           baseViewHolder.getView(R.id.im_delete).setVisibility(View.VISIBLE);
       }else {
           baseViewHolder.getView(R.id.im_delete).setVisibility(View.GONE);
       }
       baseViewHolder.setText(R.id.name,s);
    }
}
