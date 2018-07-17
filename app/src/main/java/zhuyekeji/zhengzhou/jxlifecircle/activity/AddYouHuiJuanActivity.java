package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class AddYouHuiJuanActivity extends BaseActivity
{
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ed_mianzhi)
    EditText edMianzhi;
    @BindView(R.id.tv_biaoqian)
    TextView tvBiaoqian;
    @BindView(R.id.rl_bianqian)
    RelativeLayout rlBianqian;
    @BindView(R.id.ed_shuliang)
    EditText edShuliang;
    @BindView(R.id.ed_jiage)
    EditText edJiage;
    @BindView(R.id.tv_baocun)
    TextView tvBaocun;

    @Override
    public int getViewId()
    {
        return R.layout.activity_add_you_hui_juan;
    }

    @Override
    protected void processLogic()
    {
        if (false)
        {
            JxApiCallBack.editcoupon(getToken(),1,1, (Activity) mContext,callBack);//优惠券点击编辑时返回的数据
        }
tvTitle.setText("新增优惠卷");
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

    @OnClick({R.id.rl_back, R.id.rl_bianqian, R.id.tv_baocun})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_bianqian:
                Intent intent=new Intent(AddYouHuiJuanActivity.this,BiaoQianActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_baocun:
                String money=edJiage.getText().toString().trim();
                int biaoqian=1;
                String unm=edShuliang.getText().toString().trim();
                String mianzhi=edMianzhi.getText().toString().trim();
                if (mianzhi==null||mianzhi.length()==0)
                {
                    ToastUtils.showShort("请输入面值金额");
                    return;
                }
                if (unm==null||unm.length()==0)
                {
                    ToastUtils.showShort("请输入优惠卷数量");
                    return;
                }
                if (money==null||money.length()==0)
                {
                    ToastUtils.showShort("请输入优惠卷价格");
                    return;
                }
                JxApiCallBack.add_youhuijuan(getToken(),mianzhi,biaoqian,Integer.parseInt(unm),
                        money,1,AddYouHuiJuanActivity.this,callBack);//新增优惠卷
                if (false)
                {
                    JxApiCallBack.updatecoupon(getToken(),"",1,
                            1,"",1,1,AddYouHuiJuanActivity.this,callBack);//优惠卷信息修改
                }
                break;
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
            ToastUtils.showShort(JsonUtile.getresulter(result.body()));
            setResult(1);
            finish();
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
