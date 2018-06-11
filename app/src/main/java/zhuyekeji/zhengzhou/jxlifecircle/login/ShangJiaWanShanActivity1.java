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

public class ShangJiaWanShanActivity1 extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_lianxi)
    EditText edLianxi;
    @BindView(R.id.ed_shopname)
    EditText edShopname;
    @BindView(R.id.ed_cdnumber)
    EditText edCdnumber;
    @BindView(R.id.im_waimai)
    ImageView imWaimai;
    @BindView(R.id.ll_waimai)
    LinearLayout llWaimai;
    @BindView(R.id.im_tuangou)
    ImageView imTuangou;
    @BindView(R.id.ll_tuangou)
    LinearLayout llTuangou;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.view)
    View view;
    private String mType;

    @Override
    public int getViewId()
    {
        return R.layout.activity_shang_jia_wan_shan1;
    }

    @Override
    protected void processLogic()
    {
        tvTitle.setText("完善资料");
    }

    @Override
    protected void setListener()
    {
        mType = getIntent().getStringExtra("type");
        if (mType.equals("3")||mType.equals("4"))
        {
            edShopname.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
        }
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

    @OnClick({R.id.rl_back, R.id.ll_waimai, R.id.ll_tuangou, R.id.next})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                break;
            case R.id.ll_waimai:
                imTuangou.setBackground(getResources().getDrawable(R.mipmap.gouxuan_off));
                imWaimai.setBackground(getResources().getDrawable(R.mipmap.gouxuan_on));
                break;
            case R.id.ll_tuangou:
                imTuangou.setBackground(getResources().getDrawable(R.mipmap.gouxuan_on));
                imWaimai.setBackground(getResources().getDrawable(R.mipmap.gouxuan_off));
                break;
            case R.id.next:
                if (mType.equals("1"))
                {
                    Intent intent = new Intent(ShangJiaWanShanActivity1.this, UserWanShanActivity.class);//用戶
                    startActivity(intent);
                } else if (mType.equals("2"))
                {
                    Intent intent = new Intent(ShangJiaWanShanActivity1.this, ShangJiaWanShanActivity.class);//商家
                    startActivity(intent);
                } else if (mType.equals("3"))
                {
                    Intent intent = new Intent(ShangJiaWanShanActivity1.this, PaiSongWanShanActivity.class);//派送
                    startActivity(intent);
                } else if (mType.equals("4"))
                {
                    Intent intent = new Intent(ShangJiaWanShanActivity1.this, CheZhuWanShanActivity.class);//車主
                    startActivity(intent);
                }
                break;
        }
    }
}
