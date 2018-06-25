package zhuyekeji.zhengzhou.jxlifecircle;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.JiLuAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.frament.user.PayJiLuFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.user.TiXianJiluFrament;

public class TiXIanJiLuActivity extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.slidingtl)
    SlidingTabLayout slidingtl;
    @BindView(R.id.viewpage)
    ViewPager viewpage;
    private String[] titles = new String[]{"支出记录", "提现记录"};
    private ArrayList<Fragment> mFraments = new ArrayList<>();

    @Override
    public int getViewId()
    {
        return R.layout.activity_ti_xian_ji_lu;
    }

    @Override
    protected void processLogic()
    {
        tvTitle.setText("支出记录");
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
    protected void setListener()
    {
        JiLuAdapter adapter = new JiLuAdapter(this, R.layout.jilu_item, null);

        mFraments.add(new PayJiLuFrament());
        mFraments.add(new TiXianJiluFrament());
        slidingtl.setViewPager(viewpage, titles, this, mFraments);
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
