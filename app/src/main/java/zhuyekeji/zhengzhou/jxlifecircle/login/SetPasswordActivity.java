package zhuyekeji.zhengzhou.jxlifecircle.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;

public class SetPasswordActivity extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ed_mobile)
    EditText edMobile;
    @BindView(R.id.ed_againpassword)
    EditText edAgainpassword;
    @BindView(R.id.im_xieyi)
    ImageView imXieyi;
    @BindView(R.id.tv_xieyi)
    TextView tvXieyi;
    @BindView(R.id.ll_xieyi)
    LinearLayout llXieyi;
    private boolean mXieyi=false;
    private String mType;

    @Override
    public int getViewId()
    {
        return R.layout.activity_set_password;
    }

    @Override
    protected void processLogic()
    {
        tvTitle.setText("设置密码");
    }

    @Override
    protected void setListener()
    {
mType=getIntent().getStringExtra("type");
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

    @OnClick({R.id.rl_back, R.id.im_xieyi, R.id.tv_xieyi, R.id.wancheng})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.im_xieyi:
                if (!mXieyi)
                {
                    imXieyi.setBackground(getResources().getDrawable(R.mipmap.gouxuan_on));
                    mXieyi=true;
                }else {
                    imXieyi.setBackground(getResources().getDrawable(R.mipmap.gouxuan_off));
                    mXieyi=false;
                }
                break;
            case R.id.tv_xieyi:
                break;
            case R.id.wancheng:
                if (mType.equals("1"))
                {
                    Intent intent=new Intent(SetPasswordActivity.this,UserWanShanActivity.class);//用戶
                    startActivity(intent);
                } else if (mType.equals("2"))
                {
                    Intent intent=new Intent(SetPasswordActivity.this,ShangJiaWanShanActivity1.class);//商家
                    startActivity(intent);
                } else if (mType.equals("3"))
                {
                    Intent intent=new Intent(SetPasswordActivity.this,ShangJiaWanShanActivity1.class);//派送
                    intent.putExtra("type",mType);
                    startActivity(intent);
                } else if (mType.equals("4"))
                {
                    Intent intent=new Intent(SetPasswordActivity.this,ShangJiaWanShanActivity1.class);//車主
                    intent.putExtra("type",mType);
                    startActivity(intent);
                }

                break;
        }
    }
}
