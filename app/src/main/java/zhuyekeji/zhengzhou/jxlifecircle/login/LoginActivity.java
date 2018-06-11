package zhuyekeji.zhengzhou.jxlifecircle.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuyekeji.zhengzhou.jxlifecircle.MainActivity;
import zhuyekeji.zhengzhou.jxlifecircle.R;

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
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.login://登录
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_regist://注册
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.putExtra("type","1");
                startActivity(intent);
                break;
            case R.id.tv_forgetpassword://忘记密码
                Intent intent2=new Intent(LoginActivity.this,RegisterActivity.class);
            intent2.putExtra("type","5");
            startActivity(intent2);
                break;
            case R.id.tv_other_register://其他方式注册
                Intent intent1 = new Intent(LoginActivity.this, OtherRegistActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
