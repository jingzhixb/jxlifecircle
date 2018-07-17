package zhuyekeji.zhengzhou.jxlifecircle.homeacyivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuyekeji.zhengzhou.jxlifecircle.App;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.KanJIaBangAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.bean.KanJiaDeliteBean;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;
import zhuyekeji.zhengzhou.jxlifecircle.widget.CircleImageView;

public class KanJiaShareActivity extends BaseActivity
{
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_kanjia)
    TextView tvKanjia;
    @BindView(R.id.tv_next_time)
    TextView tvNextTime;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.rv_guize)
    RecyclerView rvGuize;
    @BindView(R.id.image)
    CircleImageView image;
    @BindView(R.id.tv_yikan)
    TextView tvYikan;
    @BindView(R.id.tv_haicha)
    TextView tvHaicha;
    @BindView(R.id.im_shop)
    ImageView imShop;
    @BindView(R.id.tv_title_shop)
    TextView tvTitleShop;
    @BindView(R.id.tv_price_shop)
    TextView tvPriceShop;
    @BindView(R.id.rv_other)
    RecyclerView rvOther;
    private KanJiaDeliteBean bean;
    private KanJIaBangAdapter adapter;
    @Override
    public int getViewId()
    {
        return R.layout.activity_kan_jia_share;
    }

    @Override
    protected void processLogic()
    {
        tvTitle.setText("砍价免费拿");
        String id = getIntent().getStringExtra("porid");
        JxApiCallBack.kanjia("9225ed7e31765e51c4e7ccd987b48d70", "1", "1", 1, this, callBack);
    }

    @Override
    protected void setListener()
    {
rvOther.setLayoutManager(new LinearLayoutManager(this));
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

    CallBack callBack = new CallBack()
    {
        @Override
        public void onSuccess(int what, Response<String> result)
        {
            switch (what)
            {
                case 1:
                    if (JsonUtile.isTrue(result.body()))
                    {
                        bean = new Gson().fromJson(JsonUtile.getbody(result.body()), KanJiaDeliteBean.class);
                        Glide.with(App.getInstance()).load(bean.getHeaderpic()).into(image);
                        tvYikan.setText(bean.getYikan());
                        tvHaicha.setText(bean.getShengyu()+"");
                       if (bean.getMessage()!=null)
                       {
                           Glide.with(App.getInstance()).load(bean.getMessage().get(0).getImg()).into(imShop);
                           tvTitleShop.setText(bean.getMessage().get(0).getName());
                           tvPriceShop.setText(bean.getMessage().get(0).getPrice());
                       }
                       if (bean.getKanjiabang()!=null)
                       {
                           adapter=new KanJIaBangAdapter(KanJiaShareActivity.this,R.layout.item_kanjiabang,bean.getKanjiabang());
                           rvOther.setAdapter(adapter);
                       }
                    } else
                    {
                        ToastUtils.showShort(JsonUtile.getresulter(result.body()));
                    }
                    break;
            }
        }

        @Override
        public void onFail(int what, Response<String> result)
        {

        }

        @Override
        public void onFinish(int what)
        {

        }
    };
}
