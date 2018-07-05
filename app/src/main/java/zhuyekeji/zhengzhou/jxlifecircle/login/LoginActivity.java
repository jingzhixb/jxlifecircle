package zhuyekeji.zhengzhou.jxlifecircle.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuyekeji.zhengzhou.jxlifecircle.MainActivity;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.SPUtils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.ed_username)
    EditText edUsername;
    @BindView(R.id.edpassword)
    EditText edpassword;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.tv_regist)
    TextView tvRegist;
    @BindView(R.id.tv_forgetpassword)
    TextView tvForgetpassword;
    @BindView(R.id.l)
    LinearLayout l;
    @BindView(R.id.tv_other_register)
    TextView tvOtherRegister;
    @BindView(R.id.qq)
    ImageView qq;
    @BindView(R.id.wx)
    ImageView wx;
private static final String TAG=LoginActivity.class.getName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        rlBack.setOnClickListener(this);
        login.setOnClickListener(this);
        tvRegist.setOnClickListener(this);
        tvForgetpassword.setOnClickListener(this);
        tvOtherRegister.setOnClickListener(this);
        qq.setOnClickListener(this);
        wx.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.login://登录
                SPUtils.getInstance().put("token", "3125ed7e31765e51c4e7ccd987b48d70");
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_regist://注册
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.putExtra("type", "1");
                startActivity(intent);
                break;
            case R.id.tv_forgetpassword://忘记密码
                Intent intent2 = new Intent(LoginActivity.this, RegisterActivity.class);
                intent2.putExtra("type", "5");
                startActivity(intent2);
                break;
            case R.id.tv_other_register://其他方式注册
                Intent intent1 = new Intent(LoginActivity.this, OtherRegistActivity.class);
                startActivity(intent1);
                break;
            case R.id.qq:
                authorization(SHARE_MEDIA.QQ);
                break;
            case R.id.wx:
                authorization(SHARE_MEDIA.WEIXIN);
                break;
        }
    }
    private void authorization(SHARE_MEDIA share_media) {
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(LoginActivity.this).setShareConfig(config);
        UMShareAPI.get(this).getPlatformInfo(this, share_media, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                Log.d(TAG, "onStart " + "授权开始");
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                Log.d(TAG, "onComplete: " + "授权完成");
                String open_id = null;
                String sex;
                //sdk是6.4.4的,但是获取值的时候用的是6.2以前的(access_token)才能获取到值,未知原因
                String uid = map.get("uid");
                String openid = map.get("openid");//微博没有
                String unionid = map.get("unionid");//微博没有
                String access_token = map.get("access_token");
                String refresh_token = map.get("refresh_token");//微信,qq,微博都没有获取到
                String expires_in = map.get("expires_in");
                String name = map.get("name");                  //用户昵称
                String gender = map.get("gender");              //用户性别
                String iconurl = map.get("iconurl");            //用户头像
                if (SHARE_MEDIA.QQ == share_media || SHARE_MEDIA.WEIXIN == share_media) {
                    open_id = openid;
                } else if (SHARE_MEDIA.SINA == share_media) {
                    open_id = uid;
                }
                if (gender.equals("男")) {
                    sex = "1";
                } else if (gender.equals("女")) {
                    sex = "2";
                } else {
                    sex = "0";
                }
                //拿到信息去请求登录接口...
                Log.e("open_id=" + open_id + "name=" + name + ",sex=" + sex + ",iconurl=" + iconurl);
//                DreamApi.login(RegisterActivity.this, LOGIN_WHAT, "0", "", "", open_id, iconurl, sex, name, callBack);
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                Log.d(TAG, "onError " + "授权失败");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                Log.d(TAG, "onCancel " + "授权取消");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
