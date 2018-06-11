package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;

public class AddAddressActivity extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ed_shouhuo_name)
    EditText edShouhuoName;
    @BindView(R.id.ed_lianxi_type)
    EditText edLianxiType;
    @BindView(R.id.ed_area)
    EditText edArea;
    @BindView(R.id.ed_address)
    EditText edAddress;
    @BindView(R.id.queren)
    TextView queren;

    @Override
    public int getViewId()
    {
        return R.layout.activity_add_address;
    }

    @Override
    protected void processLogic()
    {
tvTitle.setText("新增地址");
    }

    @Override
    protected void setListener()
    {
rlBack.setOnClickListener(new View.OnClickListener()
{
    @Override
    public void onClick(View view)
    {
        finish();
    }
});
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
