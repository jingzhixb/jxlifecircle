package zhuyekeji.zhengzhou.jxlifecircle.homeacyivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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
import zhuyekeji.zhengzhou.jxlifecircle.adapter.KanJiaAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.bean.JiFenOrderBean;
import zhuyekeji.zhengzhou.jxlifecircle.bean.KanJiaBean;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

public class KanJiaListActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.im_kj)
    ImageView imKj;
    @BindView(R.id.rv_kj)
    RecyclerView rvKj;
    private KanJiaAdapter adapter;
    private List<KanJiaBean> beans = new ArrayList<>();

    @Override
    public int getViewId()
    {
        return R.layout.activity_kan_jia_list;
    }

    @Override
    protected void processLogic()
    {
        JxApiCallBack.kanjia(getToken(),1,this,callBack);
        adapter = new KanJiaAdapter(this, R.layout.kanjia_item, beans);
        rvKj.setLayoutManager(new LinearLayoutManager(this));
        rvKj.setAdapter(adapter);
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener()
        {
            @Override
            public void onItemClick(View view, int i)
            {
                if (true)
                {
                    Intent intent=new Intent(KanJiaListActivity.this,KanJiaShareActivity.class);
                    startActivity(intent);
                }else {

                }
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

    @OnClick({R.id.rl_back, R.id.im_kj})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.im_kj:
                break;
        }
    }
    CallBack callBack=new CallBack()
    {
        @Override
        public void onSuccess(int what, Response<String> result)
        {
             if (JsonUtile.getCode(result.body()).equals("200"))
             {
                 List list= Arrays.asList(new Gson().fromJson(JsonUtile.getbody(result.body()),KanJiaBean[].class));
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
