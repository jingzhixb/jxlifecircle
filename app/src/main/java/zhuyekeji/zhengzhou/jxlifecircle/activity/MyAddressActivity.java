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
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.AddressAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.bean.MyAddrseeBean;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.SPUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

public class MyAddressActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_addrsee)
    RecyclerView rvAddrsee;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    @BindView(R.id.rl_add)
    RelativeLayout rlAdd;
    private AddressAdapter adapter;
    private List<MyAddrseeBean> beans=new ArrayList<>();
    private int position;

    @Override
    public int getViewId()
    {
        return R.layout.activity_my_address;
    }

    @Override
    protected void processLogic()
    {
        tvTitle.setText("地址管理");
        JxApiCallBack.myaddress(getToken(),1,this,callBack);
    }

    @Override
    protected void setListener()
    {
        rvAddrsee.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AddressAdapter(mContext, R.layout.myaddress_item, beans);
        adapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener()
        {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i)
            {
                position = i;
                switch (view.getId())
                {
                    case R.id.rl_moren://默認地址
                        if (adapter.getItem(i).getStatus().equals("0"))//设置默认地址
                        {
                            JxApiCallBack.defaddress(getToken(),adapter.getItem(i).getId(),1,3,MyAddressActivity.this,callBack);
                        }else {//取消默认地址
                            JxApiCallBack.defaddress(getToken(),adapter.getItem(i).getId(),0,4,MyAddressActivity.this,callBack);
                        }
                        break;
                    case R.id.rl_bianji://編輯地址
                        Intent intent = new Intent(MyAddressActivity.this, AddAddressActivity.class);
                        intent.putExtra("type","2");
                        intent.putExtra("id",adapter.getItem(i).getId());
                        startActivityForResult(intent,1);
                        break;
                    case R.id.rl_delete://删除地址
                        JxApiCallBack.deleteaddress(getToken(),adapter.getItem(i).getId(),2,MyAddressActivity.this,callBack);
                        break;
                }
            }
        });
        rvAddrsee.setAdapter(adapter);
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

    @OnClick({R.id.rl_back, R.id.rl_add})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_add:
                Intent intent = new Intent(MyAddressActivity.this, AddAddressActivity.class);
                intent.putExtra("type","1");
                startActivityForResult(intent,1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1)
        {
            String edit= SPUtils.getInstance().getString("edit");
            SPUtils.getInstance().put("edit","");
            if (edit!=null&&edit.equals("1"))
            {
                JxApiCallBack.myaddress(getToken(),1,this,callBack);
            }
        }
    }

    CallBack callBack=new CallBack()
    {
        @Override
        public void onSuccess(int what, Response<String> result)
        {
            switch (what)
            {
                case 1:
                    if (JsonUtile.getCode(result.body()).equals("200"))
                    {
                        List list= Arrays.asList(new Gson().fromJson(JsonUtile.getbody(result.body()),MyAddrseeBean[].class));
                        beans.addAll(list);
                        adapter.notifyDataSetChanged();
                    }else {
                        ToastUtils.showShort(JsonUtile.getresulter(result.body()));
                    }
                    break;
                case 2://删除地址
                    if (JsonUtile.getCode(result.body()).equals("200"))
                    {
                        ToastUtils.showShort(JsonUtile.getresulter(result.body()));
                        adapter.remove(position);
                    }else {
                    ToastUtils.showShort(JsonUtile.getresulter(result.body()));
                    }
                    break;
                case 3://设置默认地址
                    if (JsonUtile.getCode(result.body()).equals("200"))
                    {
                        ToastUtils.showShort(JsonUtile.getresulter(result.body()));
                        for (int i=0;i<adapter.getItemCount();i++)
                        {
                            adapter.getItem(i).setStatus("0");
                        }
                        adapter.getItem(position).setStatus("1");
                        adapter.notifyDataSetChanged();
                    }else {
                        ToastUtils.showShort(JsonUtile.getresulter(result.body()));
                    }
                    break;
                case 4://取消默认地址
                    if (JsonUtile.getCode(result.body()).equals("200"))
                    {
                        ToastUtils.showShort(JsonUtile.getresulter(result.body()));
                        adapter.getItem(position).setStatus("0");
                        adapter.notifyDataSetChanged();
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
}
