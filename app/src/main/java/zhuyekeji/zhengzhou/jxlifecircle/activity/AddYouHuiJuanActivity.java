package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;

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
                break;
        }
    }
}
