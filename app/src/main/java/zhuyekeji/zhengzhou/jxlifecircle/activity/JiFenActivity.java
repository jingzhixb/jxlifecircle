package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.MyJiFenAfapter;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.bean.MyJiFenBean;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.SPUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;


public class JiFenActivity extends AppCompatActivity
{
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.rv_jifen)
    RecyclerView rvJifen;
    @BindView(R.id.tv_alljifen)
    TextView tvAlljifen;
    private MyJiFenAfapter afapter;
    private List<MyJiFenBean> beans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_ji_fen);
        ButterKnife.bind(this);
        intdata();
        rlBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
        rvJifen.setLayoutManager(new LinearLayoutManager(this));
        String jifen = getIntent().getStringExtra("jifen");
        if (jifen != null)
        {
            tvAlljifen.setText(jifen);
        }
    }

    private void intdata()
    {
        String token = SPUtils.getInstance().getString("token");
        JxApiCallBack.jifen(token, 1, this, callBack);
        afapter = new MyJiFenAfapter(this, R.layout.item_jifen, beans);
        rvJifen.setAdapter(afapter);
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
                        List list = Arrays.asList(new Gson().fromJson(JsonUtile.getbody(result.body()), MyJiFenBean[].class));
                        beans.addAll(list);
                        afapter.notifyDataSetChanged();
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
