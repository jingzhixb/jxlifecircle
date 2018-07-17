package zhuyekeji.zhengzhou.jxlifecircle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.List;

import zhuyekeji.zhengzhou.jxlifecircle.R;

/**
 * Created by Administrator on 2018/7/13.
 */

public class SpAdapter extends BaseAdapter
{
    private List<String> beans;
    private Context context;

    public SpAdapter(List<String> beans, Context context)
    {
        this.beans = beans;
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return beans.size();
    }

    @Override
    public Object getItem(int i)
    {
        return beans.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (view==null)
        {
            view= LayoutInflater.from(context).inflate(R.layout.item_sp,null);
        }
        TextView v=(TextView)view.findViewById(R.id.name);
        v.setText(beans.get(i));
        return view;
    }
}
