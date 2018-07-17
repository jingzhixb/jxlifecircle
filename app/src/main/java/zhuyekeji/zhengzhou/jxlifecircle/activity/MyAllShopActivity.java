package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.ErJiAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.YIJiAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.bean.ErJiBean;
import zhuyekeji.zhengzhou.jxlifecircle.bean.JiFenOrderBean;
import zhuyekeji.zhengzhou.jxlifecircle.bean.ShopTypeBean;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

public class MyAllShopActivity extends BaseActivity
{
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.yiji)
    RecyclerView yiji;
    @BindView(R.id.erji)
    RecyclerView erji;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.rl_newshop)
    RelativeLayout rlNewshop;
    private String mShopId;
    private YIJiAdapter yiJiAdapter;
    private ErJiAdapter erJiAdapter;
    private List<ShopTypeBean> yijibean=new ArrayList<>();
    private List<ErJiBean> erJiBeans;
    private int yiPosition;
    private View v;

    @Override
    public int getViewId()
    {
        return R.layout.activity_my_all_shop;
    }

    @Override
    protected void processLogic()
    {
        mShopId=getIntent().getStringExtra("shopid");
        tvTitle.setText("全部商品");
        JxApiCallBack.shop_list(getToken(), Integer.parseInt(mShopId), 1, this, callBack);
        yiji.setLayoutManager(new LinearLayoutManager(this));
        erji.setLayoutManager(new LinearLayoutManager(this));
        yiJiAdapter = new YIJiAdapter(this, R.layout.yiji_item, yijibean);
        yiJiAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener()
        {
            @Override
            public void onItemClick(View view, int i)
            {
                yiPosition = i;
              JxApiCallBack.goods_list(getToken(),mShopId,1,yiJiAdapter.getItem(i).getClass_id()
                      ,2,MyAllShopActivity.this,callBack);
            }
        });
        RelativeLayout view = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.layout_type, null);
        yiJiAdapter.addFooterView(view);
        v = view.findViewById(R.id.v);
        v.setVisibility(View.GONE);
        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                for (int i=0;i<yijibean.size();i++)
                {
                    yijibean.get(i).setIs_show(2);
                }
                v.setVisibility(View.VISIBLE);
            }
        });
        TextView tv = view.findViewById(R.id.tv);

        yiji.setAdapter(yiJiAdapter);
    }

    @Override
    protected void setListener()
    {

    }

    CallBack callBack = new CallBack()
    {
        @Override
        public void onSuccess(int what, Response<String> result)
        {
            switch (what)
            {
                case 1:
                    String code= JsonUtile.getCode(result.body());
                    if (code.equals("200"))
                    {

//                            JSONObject object=new JSONObject(result.body());
//                            String type=object.getString("type");
                            ShopTypeBean[] bean=new Gson().fromJson(JsonUtile.getbody(result.body()),ShopTypeBean[].class);
                            List list= Arrays.asList(bean);
                            yijibean.addAll(list);
                            for (int i=0;i<yijibean.size();i++)
                            {
                               if (i==0)
                               {
                                   yijibean.get(0).setIs_show(1);
                               }else {
                                   yijibean.get(i).setIs_show(2);
                               }
                            }
                            yiJiAdapter.notifyDataSetChanged();
                    }else {
                        ToastUtils.showShort(JsonUtile.getresulter(result.body()));
                    }
                    break;
                case 2:
                    String code2= JsonUtile.getCode(result.body());
                    if (code2.equals("200"))
                    {

                        JSONObject object= null;
                        try
                        {
                            object = new JSONObject(JsonUtile.getbody(result.body()));
                            String type=object.getString("list");
                            ErJiBean[] bean=new Gson().fromJson(type,ErJiBean[].class);
                            List list= Arrays.asList(bean);
                            for (int i=0;i<yijibean.size();i++)
                            {
                                yijibean.get(i).setIs_show(2);
                            }
                            yijibean.get(yiPosition).setIs_show(1);
                            v.setVisibility(View.GONE);
                            yiJiAdapter.notifyDataSetChanged();
                            erJiBeans=new ArrayList<>(list);
                            erJiAdapter = new ErJiAdapter(MyAllShopActivity.this, R.layout.erji_item, erJiBeans);
                            erJiAdapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener()
                            {
                                @Override
                                public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i)
                                {
                                    switch (view.getId())
                                    {
                                        case R.id.tv_bianji:
                                            Intent intent = new Intent(MyAllShopActivity.this, NewShopActivity.class);
                                            intent.putExtra("shopid",mShopId);
                                            intent.putExtra("type", "2");
                                            startActivity(intent);
                                            break;
                                        case R.id.tv_xiajia:
                                            //  JxApiCallBack.set_shelf();
                                            break;
                                    }
                                }
                            });
                            erji.setAdapter(erJiAdapter);
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }else {
                        ToastUtils.showShort(JsonUtile.getresulter(result.body()));
                    }
                    break;
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

    @OnClick({R.id.rl_back, R.id.tv_type, R.id.rl_newshop})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_type://管理分类
                Intent intent = new Intent(MyAllShopActivity.this, TypeActivity.class);
                intent.putExtra("shopid",mShopId);
                startActivity(intent);
                break;
            case R.id.rl_newshop://新建商品
                Intent intent2 = new Intent(MyAllShopActivity.this, NewShopActivity.class);//新增商品
                intent2.putExtra("type", "1");
                intent2.putExtra("shopid",mShopId);
                startActivity(intent2);
                break;
        }
    }
}
