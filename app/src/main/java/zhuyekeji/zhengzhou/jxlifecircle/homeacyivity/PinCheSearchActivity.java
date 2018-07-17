package zhuyekeji.zhengzhou.jxlifecircle.homeacyivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.activity.PInCheDeliteActivity;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.PinCheAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.bean.PinCheBean;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

public class PinCheSearchActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.search)
    RecyclerView search;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    private List<PinCheBean> beans=new ArrayList<>();
    private PinCheAdapter adapter;

    @Override
    public int getViewId()
    {
        return R.layout.activity_pin_che_search;
    }

    @Override
    protected void processLogic()
    {
        String start=getIntent().getStringExtra("start");
        String end=getIntent().getStringExtra("end");
        String data=getIntent().getStringExtra("data");
        JxApiCallBack.search(getToken(),1,start,end,data,1,this,callBack);
        tvTitle.setText("搜索结果");
        adapter = new PinCheAdapter(this, R.layout.pinche_item, beans);
        search.setLayoutManager(new LinearLayoutManager(this));
        search.setAdapter(adapter);
        adapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener()
        {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i)
            {
                switch (view.getId())
                {
                    case R.id.phone:
                        Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+adapter.getItem(i).getContact_tel()));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        break;
                }
            }
        });
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener()
        {
            @Override
            public void onItemClick(View view, int i)
            {
                Intent intent=new Intent(PinCheSearchActivity.this,PInCheDeliteActivity.class);
                intent.putExtra("id",adapter.getItem(i).getId());
                startActivity(intent);
            }
        });
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
                    try
                    {
                        JSONObject object=new JSONObject(result.body());
                        String data=object.getString("data");
                        List list = Arrays.asList( new Gson().fromJson(data, PinCheBean[].class));
                        beans.addAll(list);
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }


                } else
                {
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
}
