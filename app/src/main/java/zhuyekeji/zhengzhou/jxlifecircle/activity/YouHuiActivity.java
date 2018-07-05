package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.YouHuiAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.bean.MyCouPon;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

public class YouHuiActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_youhui)
    RecyclerView rvYouhui;

    private YouHuiAdapter adapter;
    private List<MyCouPon> beans;
    @Override
    public int getViewId()
    {
        return R.layout.activity_you_hui;
    }

    @Override
    protected void processLogic()
    {
        JxApiCallBack.mycoupon(getToken(),1, (Activity) mContext,callBack);
        tvTitle.setText("优惠卷");
        rlBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
        adapter=new YouHuiAdapter(this,R.layout.youhui_item,null);
        rvYouhui.setAdapter(adapter);
        rvYouhui.setLayoutManager(new LinearLayoutManager(this));

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
                      String body=JsonUtile.getbody(result.body());
                      MyCouPon[] bean=new Gson().fromJson(body,MyCouPon[].class);
                      List list= Arrays.asList(bean);
                      beans=new ArrayList<>(list);
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
