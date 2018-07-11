package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuyekeji.zhengzhou.jxlifecircle.App;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.Jxapi;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

public class SettingActivity extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.version_code)
    TextView versionCode;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.name2)
    TextView name2;
    @BindView(R.id.compony)
    TextView compony;

    @Override
    public int getViewId()
    {
        return R.layout.activity_setting;
    }

    @Override
    protected void processLogic()
    {
        tvTitle.setText("关于软件");
        JxApiCallBack.guanyu(getToken(), 1, this, callBack);
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

    CallBack callBack = new CallBack()
    {
        @Override
        public void onSuccess(int what, Response<String> result)
        {
            switch (what)
            {
                case 1:
                    if (JsonUtile.getCode(result.body()).equals("200"))
                    {
                        try
                        {
                            JSONObject object = new JSONObject(result.body());
                            String id = object.getString("id");
                            String names = object.getString("name");
                            String imgs = object.getString("img");
                            String componys = object.getString("compony");
                            String levels = object.getString("level");
                            name.setText(names);
                            name2.setText(names);
                            compony.setText(componys);
                            versionCode.setText(levels);
                            Glide.with(App.getInstance()).load(Jxapi.BASE+imgs).into(img);
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
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
