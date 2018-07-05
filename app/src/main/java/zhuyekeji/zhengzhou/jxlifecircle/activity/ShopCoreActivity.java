package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

public class ShopCoreActivity extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.im_title)
    ImageView imTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;
    @BindView(R.id.rl_dingdan)
    RelativeLayout rlDingdan;
    @BindView(R.id.rl_guanli)
    RelativeLayout rlGuanli;
    @BindView(R.id.rl_youhui)
    RelativeLayout rlYouhui;

    @Override
    public int getViewId()
    {
        return R.layout.activity_shop_core;
    }

    @Override
    protected void processLogic()
    {
        tvTitle.setText("商家中心");
      //  JxApiCallBack.shopcore(getToken(),1,this,callBack);
    }

    @Override
    protected void setListener()
    {

    }

    CallBack callBack=new CallBack()
    {
        @Override
        public void onSuccess(int what, Response<String> result)
        {
           String body=JsonUtile.getbody(result.body());
           if (!body.equals(""))
           {

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

    @OnClick({R.id.rl_back, R.id.ll_info, R.id.rl_dingdan, R.id.rl_guanli, R.id.rl_youhui})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_info://商家信息
                Intent intent4 = new Intent(ShopCoreActivity.this, ShopInfoActivity.class);
                startActivity(intent4);
                break;
            case R.id.rl_dingdan://商家订单
                if (true)
                {
                    Intent intent = new Intent(ShopCoreActivity.this, ShopOrederActivity.class);
                    startActivity(intent);
                } else
                {
                    Intent intent = new Intent(ShopCoreActivity.this, ShopOrederActivity2.class);
                    startActivity(intent);
                }

                break;
            case R.id.rl_guanli://商品管理
                Intent intent1 = new Intent(ShopCoreActivity.this, MyAllShopActivity.class);
                startActivity(intent1);
                break;
            case R.id.rl_youhui://优惠卷
                Intent intent = new Intent(ShopCoreActivity.this, ShopYouHuiJuanActivity.class);
                startActivity(intent);
                break;
        }
    }
}
