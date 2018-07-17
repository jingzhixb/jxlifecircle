package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.TiXIanJiLuActivity;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.ZhiChuAdapter;

public class MyWalletActivity extends AppCompatActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_allmoney)
    TextView tvAllmoney;
    @BindView(R.id.tv_tixian)
    TextView tvTixian;
    @BindView(R.id.rl_jilu)
    RelativeLayout rlJilu;
    @BindView(R.id.rl_shouzhi)
    RelativeLayout rlShouzhi;
    @BindView(R.id.rv_type)
    RecyclerView rvType;
    @BindView(R.id.tv_chongzhi)
    TextView tvChongzhi;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_my_wallet);
        ButterKnife.bind(this);

        ZhiChuAdapter adapter = new ZhiChuAdapter(this, R.layout.zhichu_item, null);
    }

    @OnClick({R.id.rl_back, R.id.rl_jilu, R.id.rl_shouzhi,R.id.tv_chongzhi, R.id.tv_tixian})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_jilu://提现记录
                break;
            case R.id.rl_shouzhi://收支记录
                Intent intent = new Intent(MyWalletActivity.this, TiXIanJiLuActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_chongzhi:
                Intent intent1=new Intent(MyWalletActivity.this,ChongZhiActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_tixian:
                Intent intent2 = new Intent(MyWalletActivity.this, TiXianActivity.class);
                startActivity(intent2);
                break;
        }
    }



}
