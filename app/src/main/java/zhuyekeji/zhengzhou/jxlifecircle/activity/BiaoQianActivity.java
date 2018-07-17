package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.BiaoQianAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.bean.LabelBean;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

public class BiaoQianActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_select_on)
    RecyclerView rvSelectOn;
    @BindView(R.id.rv_alll)
    RecyclerView rvAlll;
    @BindView(R.id.baocun)
    TextView baocun;
    List<LabelBean> list=new ArrayList<>();
    private List<LabelBean> beans=new ArrayList<>();
    private BiaoQianAdapter adapter,adapterAll;
    private boolean is=false;
    @Override
    public int getViewId()
    {
        return R.layout.activity_biao_qian;
    }

    @Override
    protected void processLogic()
    {
tvTitle.setText("商品标签");
    }

    @Override
    protected void setListener()
    {

        JxApiCallBack.label(1,this,callBack);
          rvAlll.setLayoutManager(new GridLayoutManager(BiaoQianActivity.this,4));
        rvSelectOn.setLayoutManager(new GridLayoutManager(BiaoQianActivity.this,4));
        adapter=new BiaoQianAdapter(this,R.layout.biaoqian_item,beans,1);
        adapterAll=new BiaoQianAdapter(this,R.layout.biaoqian_item,list,2);
        rvSelectOn.setAdapter(adapter);
        rvAlll.setAdapter(adapterAll);
        adapterAll.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener()
        {

            @Override
            public void onItemClick(View view, int i)
            {
                is=false;
               if (beans.size()==0)
               {
                   beans.add(adapterAll.getItem(i));
                   adapter.notifyDataSetChanged();
                   return;
               }
               for (int j=0;j<beans.size();j++)
               {
                   if (beans.get(j).getId().equals(adapterAll.getItem(i).getId()))
                   {
                      is=true;
                      return;
                   }
               }
               if (!is)
               {
                   beans.add(adapterAll.getItem(i));
                   adapter.notifyDataSetChanged();
               }
            }
        });
        adapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener()
        {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i)
            {
                switch (view.getId())
                {
                    case R.id.im_delete:
                        beans.remove(i);
                        adapter.notifyDataSetChanged();
                        break;
                }
            }
        });
    }

    CallBack callBack=new CallBack()
    {
        @Override
        public void onSuccess(int what, Response<String> result)
        {
           if (JsonUtile.isTrue(result.body()))
           {
               List l= Arrays.asList(new Gson().fromJson(JsonUtile.getbody(result.body()),LabelBean[].class));
               list.addAll(l);
               adapterAll.notifyDataSetChanged();
           }else {
               ToastUtils.showShort(JsonUtile.getresulter(result.body()));
           }
        }

        @Override
        public void onFail(int what, Response<String> result)
        {

        }

        @Override
        public void onFinish(int what)
        {

        }
    };
    @Override
    protected Context getActivityContext()
    {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_back, R.id.baocun})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.baocun:
                Intent intent=new Intent();
                String label="";
                if (adapter.getItemCount()==0)
                {
                    ToastUtils.showShort("请选择商品标签");
                    return;
                }
                for (int i=0;i<adapter.getItemCount();i++)
                {
                    label+=adapter.getItem(i).getId()+",";
                }
               label=label.substring(0,label.length()-1);
                intent.putExtra("count",adapter.getItemCount()+"");
                intent.putExtra("label",label);
                setResult(1,intent);
                finish();
                break;
        }
    }
}
