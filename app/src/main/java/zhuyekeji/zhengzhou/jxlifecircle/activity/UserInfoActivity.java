package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.login.CheZhuWanShanActivity;
import zhuyekeji.zhengzhou.jxlifecircle.widget.CircleImageView;

public class UserInfoActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.im_user)
    CircleImageView imUser;
    @BindView(R.id.rl_touxian)
    RelativeLayout rlTouxian;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.rl_nickname)
    RelativeLayout rlNickname;
    @BindView(R.id.tv_lianxiname)
    TextView tvLianxiname;
    @BindView(R.id.rl_lianxi)
    RelativeLayout rlLianxi;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.rl_mobile)
    RelativeLayout rlMobile;
    @BindView(R.id.tv_cd_number)
    TextView tvCdNumber;
    @BindView(R.id.rl_cd_number)
    RelativeLayout rlCdNumber;
    @BindView(R.id.tv_jiashi)
    TextView tvJiashi;
    @BindView(R.id.rl_jiashi)
    RelativeLayout rlJiashi;
    @BindView(R.id.rl_bank)
    RelativeLayout rlBank;

    @Override
    public int getViewId()
    {
        return R.layout.activity_user_info;
    }

    @Override
    protected void processLogic()
    {
tvTitle.setText("个人资料");
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

    @OnClick({R.id.rl_back, R.id.rl_touxian, R.id.rl_nickname, R.id.rl_lianxi, R.id.rl_mobile, R.id.rl_cd_number, R.id.rl_jiashi, R.id.rl_bank})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_touxian:
                break;
            case R.id.rl_nickname:
                break;
            case R.id.rl_lianxi:
                break;
            case R.id.rl_mobile:
                Intent intent3=new Intent(UserInfoActivity.this,BingPhoneActivity.class);
                startActivity(intent3);
                break;
            case R.id.rl_cd_number:
                Intent intent=new Intent(UserInfoActivity.this,CDActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_jiashi:
                Intent intent2=new Intent(UserInfoActivity.this, CheZhuWanShanActivity.class);
                intent2.putExtra("type","2");
                startActivity(intent2);
                break;
            case R.id.rl_bank:
                break;
        }
    }
}
