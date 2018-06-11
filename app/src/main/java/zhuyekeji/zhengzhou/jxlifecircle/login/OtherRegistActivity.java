package zhuyekeji.zhengzhou.jxlifecircle.login;

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

public class OtherRegistActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_shangjia)
    TextView tvShangjia;
    @BindView(R.id.tv_paisong)
    TextView tvPaisong;
    @BindView(R.id.tv_chezhu)
    TextView tvChezhu;

    @Override
    public int getViewId()
    {
        return R.layout.activity_other_regist;
    }

    @Override
    protected void processLogic()
    {

    }

    @Override
    protected void setListener()
    {

    }

    @Override
    protected Context getActivityContext()
    {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_back, R.id.tv_shangjia, R.id.tv_paisong, R.id.tv_chezhu})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_shangjia://商家注册
                Intent intent = new Intent(OtherRegistActivity.this, RegisterActivity.class);
                intent.putExtra("type", "2");
                startActivity(intent);
                break;
            case R.id.tv_paisong://派送
                Intent intent2 = new Intent(OtherRegistActivity.this, RegisterActivity.class);
                intent2.putExtra("type", "3");
                startActivity(intent2);

                break;
            case R.id.tv_chezhu://车主
                Intent intent3 = new Intent(OtherRegistActivity.this, RegisterActivity.class);
                intent3.putExtra("type", "4");
                startActivity(intent3);
                break;
        }
    }
}
