package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.AppUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.CacheUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.CleanUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.SPUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

public class SettingActivity2 extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_xiugaipassword)
    RelativeLayout rlXiugaipassword;
    @BindView(R.id.tv_cache)
    TextView tvCache;
    @BindView(R.id.rl_calean)
    RelativeLayout rlCalean;
    @BindView(R.id.rl_guanyu)
    RelativeLayout rlGuanyu;
    @BindView(R.id.rl_genduo)
    RelativeLayout rlGenduo;

    @Override
    public int getViewId()
    {
        return R.layout.activity_setting2;
    }

    @Override
    protected void processLogic()
    {
        tvTitle.setText("设置");
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

    @OnClick({R.id.rl_back, R.id.rl_xiugaipassword, R.id.rl_calean, R.id.rl_guanyu, R.id.rl_genduo,R.id.exit})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_xiugaipassword:
                Intent intent2 = new Intent(SettingActivity2.this, XiuGaiMMActivity.class);
                startActivity(intent2);
                break;
            case R.id.rl_calean://清除缓存
               if ( CacheUtils.getInstance().clear())
               {
                   ToastUtils.showShort("已清除缓存："+(CacheUtils.getInstance().getCacheSize()/(1024*1024)+"M"));
               }
                break;
            case R.id.rl_guanyu:
                Intent intent = new Intent(SettingActivity2.this, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_genduo:
                break;
            case R.id.exit:
                SPUtils.getInstance().put("islogin",false,true);
                AppUtils.exitApp();
                break;
        }
    }
}
