package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;

public class OrderDelitActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitles;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl_persion)
    RelativeLayout rlPersion;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.statu)
    TextView statu;
    @BindView(R.id.tv_shuliang)
    TextView tvShuliang;
    @BindView(R.id.tv_youhuijuan)
    TextView tvYouhuijuan;
    @BindView(R.id.tv_shifukuan)
    TextView tvShifukuan;
    @BindView(R.id.order_number)
    TextView orderNumber;
    @BindView(R.id.creat_time)
    TextView creatTime;
    @BindView(R.id.pay_time)
    TextView payTime;
    @BindView(R.id.hexiao_time)
    TextView hexiaoTime;
    @BindView(R.id.why_tuikuan)
    TextView whyTuikuan;
    @BindView(R.id.hexiao)
    TextView hexiao;
    @BindView(R.id.jujue)
    TextView jujue;
    @BindView(R.id.tongyi)
    TextView tongyi;
    @BindView(R.id.rl_tuikuan)
    RelativeLayout rlTuikuan;
    @BindView(R.id.tv_titles)
    TextView tvTitle;
    private String type;

    @Override
    public int getViewId()
    {
        return R.layout.activity_order_delit;
    }

    @Override
    protected void processLogic()
    {
        type = getIntent().getStringExtra("type");
        if (type.equals("1"))//订单详情
        {
            tvTitles.setText("订单详情");
            rlTuikuan.setVisibility(View.GONE);
            hexiao.setVisibility(View.GONE);
        } else if (type.equals("2"))//退款原因
        {
            tvTitles.setText("退款原因");
            rlTuikuan.setVisibility(View.VISIBLE);
            hexiao.setVisibility(View.GONE);
        } else if (type.equals("3"))//核销
        {
            tvTitles.setText("核销订单");
            rlTuikuan.setVisibility(View.GONE);
            hexiao.setVisibility(View.VISIBLE);
        }
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

    @OnClick({R.id.rl_back, R.id.rl_persion, R.id.hexiao, R.id.jujue, R.id.tongyi})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                break;
            case R.id.rl_persion:
                break;
            case R.id.hexiao:
                break;
            case R.id.jujue:
                break;
            case R.id.tongyi:
                break;
        }
    }
}
