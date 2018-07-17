package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.App;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.bean.KanJiaDeliteBean;

/**
 * Created by Administrator on 2018/7/16.
 */

public class KanJIaBangAdapter extends BaseQuickAdapter<KanJiaDeliteBean.KanjiabangBean>
{
    public KanJIaBangAdapter(Context context, int layoutResId, List<KanJiaDeliteBean.KanjiabangBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, KanJiaDeliteBean.KanjiabangBean s)
    {
          baseViewHolder.setText(R.id.name,s.getNickname());
          baseViewHolder.setText(R.id.kandiao,s.getTime());
        Glide.with(App.getInstance()).load(s.getHeader()).into((ImageView)baseViewHolder.getView(R.id.image));
    }
}
