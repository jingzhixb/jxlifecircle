package zhuyekeji.zhengzhou.jxlifecircle.homeacyivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.KanJiaAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.bean.JiFenOrderBean;

public class KanJiaListActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.im_kj)
    ImageView imKj;
    @BindView(R.id.rv_kj)
    RecyclerView rvKj;
    private KanJiaAdapter adapter;
    private List<JiFenOrderBean> beans = new ArrayList<>();

    @Override
    public int getViewId()
    {
        return R.layout.activity_kan_jia_list;
    }

    @Override
    protected void processLogic()
    {
        for (int i = 0; i < 10; i++)
        {
            beans.add(new JiFenOrderBean());
        }
        adapter = new KanJiaAdapter(this, R.layout.kanjia_item, beans);
        rvKj.setLayoutManager(new LinearLayoutManager(this));
        rvKj.setAdapter(adapter);
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener()
        {
            @Override
            public void onItemClick(View view, int i)
            {
                if (true)
                {
                    Intent intent=new Intent(KanJiaListActivity.this,KanJiaShareActivity.class);
                    startActivity(intent);
                }else {

                }
            }
        });
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

    @OnClick({R.id.rl_back, R.id.im_kj})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.im_kj:
                break;
        }
    }
}
