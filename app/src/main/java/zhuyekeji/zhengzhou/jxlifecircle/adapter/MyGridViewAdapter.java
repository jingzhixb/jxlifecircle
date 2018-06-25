package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.bean.HomeIconInfo;

/**
 * Created by Administrator on 2018/6/22.
 */

public class MyGridViewAdapter extends BaseAdapter
{
    private List<HomeIconInfo> itemData = new ArrayList<HomeIconInfo>();
    private Context mContext;

    public MyGridViewAdapter(List<HomeIconInfo> itemData, Context context) {
        this.itemData = itemData;
        mContext = context;
    }

    //根据容器的大小,显示多少个item
    @Override
    public int getCount() {
        return itemData.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //把一个item的xml布局转换为一个View资源
        View inflater = LayoutInflater.from(mContext).inflate(R.layout.home_grid_item, null);
        //初始化ImageView对象,设置图片资源
        ImageView imageView = (ImageView) inflater.findViewById(R.id.iv);
        imageView.setImageResource(itemData.get(i).getIconID());
        //初始化TextView对象,设置文本资源
        TextView textView = (TextView) inflater.findViewById(R.id.tv);
        textView.setText(itemData.get(i).getIconName());
        return inflater;
    }
    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {

        return position;
    }
}
