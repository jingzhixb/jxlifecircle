package zhuyekeji.zhengzhou.jxlifecircle.orderactivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

public class UserPingJiaActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.im1)
    ImageView im1;
    @BindView(R.id.im2)
    ImageView im2;
    @BindView(R.id.im3)
    ImageView im3;
    @BindView(R.id.im4)
    ImageView im4;
    @BindView(R.id.im5)
    ImageView im5;
    @BindView(R.id.ed_content)
    EditText edContent;
    @BindView(R.id.rv_up)
    RecyclerView rvUp;
    @BindView(R.id.tv_queren)
    TextView tvQueren;
    private String orderid;
    private int xing=5;

    @Override
    public int getViewId()
    {
        return R.layout.activity_user_ping_jia;
    }

    @Override
    protected void processLogic()
    {
        orderid = getIntent().getStringExtra("orderid");
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

    @OnClick({R.id.rl_back, R.id.im1, R.id.im2, R.id.im3, R.id.im4, R.id.im5, R.id.tv_queren})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                break;
            case R.id.im1:
                im1.setBackgroundResource(R.mipmap.xingkong);
                im2.setBackgroundResource(R.mipmap.xing);
                im3.setBackgroundResource(R.mipmap.xing);
                im4.setBackgroundResource(R.mipmap.xing);
                im5.setBackgroundResource(R.mipmap.xing);
                xing=1;
                break;
            case R.id.im2:
                im1.setBackgroundResource(R.mipmap.xingkong);
                im2.setBackgroundResource(R.mipmap.xingkong);
                im3.setBackgroundResource(R.mipmap.xing);
                im4.setBackgroundResource(R.mipmap.xing);
                im5.setBackgroundResource(R.mipmap.xing);
                xing=2;
                break;
            case R.id.im3:
                im1.setBackgroundResource(R.mipmap.xingkong);
                im2.setBackgroundResource(R.mipmap.xingkong);
                im3.setBackgroundResource(R.mipmap.xingkong);
                im4.setBackgroundResource(R.mipmap.xing);
                im5.setBackgroundResource(R.mipmap.xing);
                xing=3;
                break;
            case R.id.im4:
                im1.setBackgroundResource(R.mipmap.xingkong);
                im2.setBackgroundResource(R.mipmap.xingkong);
                im3.setBackgroundResource(R.mipmap.xingkong);
                im4.setBackgroundResource(R.mipmap.xingkong);
                im5.setBackgroundResource(R.mipmap.xing);
                xing=4;
                break;
            case R.id.im5:
                im1.setBackgroundResource(R.mipmap.xingkong);
                im2.setBackgroundResource(R.mipmap.xingkong);
                im3.setBackgroundResource(R.mipmap.xingkong);
                im4.setBackgroundResource(R.mipmap.xingkong);
                im5.setBackgroundResource(R.mipmap.xingkong);
                xing=5;
                break;
            case R.id.tv_queren:
                String content=edContent.getText().toString().trim();
                if (content==null||content.length()==0)
                {
                    ToastUtils.showShort("请输入评价内容");
                    return;
                }
                JxApiCallBack.pinjia(getToken(),orderid,content,xing,"",1,UserPingJiaActivity.this,callBack);
                break;
        }
    }
    CallBack callBack=new CallBack()
    {
        @Override
        public void onSuccess(int what, Response<String> result)
        {
            if (JsonUtile.isTrue(result.body()))
            {
                ToastUtils.showShort(JsonUtile.getresulter(result.body()));
                finish();
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
