package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.bean.AddressBean;
import zhuyekeji.zhengzhou.jxlifecircle.bean.MyAddrseeBean;

/**
 * Created by Administrator on 2018/7/14.
 */

public class SelectAddressAdapter extends BaseQuickAdapter<MyAddrseeBean>
{
    public SelectAddressAdapter(Context context, int layoutResId, List<MyAddrseeBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MyAddrseeBean myAddrseeBean)
    {
baseViewHolder.setText(R.id.name,myAddrseeBean.getName()+"  "+myAddrseeBean.getTel());
baseViewHolder.setText(R.id.address,myAddrseeBean.getAddre()+""+myAddrseeBean.getDiqu()+myAddrseeBean.getJiedao());
    }
}
