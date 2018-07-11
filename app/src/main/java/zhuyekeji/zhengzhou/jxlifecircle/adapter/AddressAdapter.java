package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.bean.AddressBean;
import zhuyekeji.zhengzhou.jxlifecircle.bean.MyAddrseeBean;

/**
 * Created by Administrator on 2018/6/8.
 */

public class AddressAdapter extends BaseQuickAdapter<MyAddrseeBean>
{
    public AddressAdapter(Context context, int layoutResId, List<MyAddrseeBean> data)
    {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MyAddrseeBean addressBean)
    {
        if (addressBean.getStatus().equals("1"))
        {
            baseViewHolder.setBackgroundRes(R.id.im_duihao, R.mipmap.gouxuan_on);
        }else {
            baseViewHolder.setBackgroundRes(R.id.im_duihao, R.mipmap.gouxuan_off);
        }
        baseViewHolder.setText(R.id.name,addressBean.getName());
        baseViewHolder.setText(R.id.phonenumber,addressBean.getTel());
        baseViewHolder.setText(R.id.tv_address,addressBean.getDiqu()+" "+addressBean.getJiedao());
        baseViewHolder.setOnClickListener(R.id.rl_moren,new OnItemChildClickListener());
        baseViewHolder.setOnClickListener(R.id.rl_delete,new OnItemChildClickListener());
        baseViewHolder.setOnClickListener(R.id.rl_bianji,new OnItemChildClickListener());
    }
}
