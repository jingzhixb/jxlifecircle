package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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
import zhuyekeji.zhengzhou.jxlifecircle.adapter.ShopTypeAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.bean.ShopType2Bean;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

public class ShopTypeActivity extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_erji)
    RecyclerView rvErji;
    private List<ShopType2Bean> beans = new ArrayList<>();
    private ShopTypeAdapter adapter;

    @Override
    public int getViewId()
    {
        return R.layout.activity_shop_type;
    }

    @Override
    protected void processLogic()
    {
        tvTitle.setText("请选择商家分类");
        int type = getIntent().getIntExtra("type", 0);
        if (type==1)
        {
            JxApiCallBack.youhui_er(type, 1, this, callBack);
        }else if (type==4)
        {
            ShopType2Bean bean=new ShopType2Bean();
            bean.setType("休闲娱乐");
            bean.setClassid("4");
            beans.add(bean);
            ShopType2Bean bean1=new ShopType2Bean();
            bean1.setType("家政");
            bean1.setClassid("5");
            beans.add(bean1);
            ShopType2Bean bean2=new ShopType2Bean();
            bean2.setType("KTV");
            bean2.setClassid("6");
            beans.add(bean2);
            ShopType2Bean bean3=new ShopType2Bean();
            bean3.setType("丽人美发");
            bean3.setClassid("7");
            beans.add(bean3);
            ShopType2Bean bean4=new ShopType2Bean();
            bean4.setType("教育培训");
            bean4.setClassid("10");
            beans.add(bean4);
        }
        rvErji.setLayoutManager(new LinearLayoutManager(this));
        adapter=new ShopTypeAdapter(this,R.layout.shop_type_item,beans);
        rvErji.setAdapter(adapter);
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener()
        {
            @Override
            public void onItemClick(View view, int i)
            {
                Intent intent=new Intent();
                intent.putExtra("classid",adapter.getItem(i).getClassid());
                intent.putExtra("type",adapter.getItem(i).getType());
                setResult(2,intent);
                finish();
            }
        });
    }

    @Override
    protected void setListener()
    {

    }

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

    @OnClick(R.id.rl_back)
    public void onViewClicked()
    {
    }

    CallBack callBack = new CallBack()
    {
        @Override
        public void onSuccess(int what, Response<String> result)
        {
         if (JsonUtile.getCode(result.body()).equals("200"))
         {
             List list= Arrays.asList(new Gson().fromJson(JsonUtile.getbody(result.body()),ShopType2Bean[].class));
             beans.addAll(list);
             adapter.notifyDataSetChanged();
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
}
