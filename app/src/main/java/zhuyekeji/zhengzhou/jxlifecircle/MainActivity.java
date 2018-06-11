package zhuyekeji.zhengzhou.jxlifecircle;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuyekeji.zhengzhou.jxlifecircle.base.FragmentController;
import zhuyekeji.zhengzhou.jxlifecircle.utils.other.BackHandlerHelper;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener
{


   // @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
  //  @BindView(R.id.rb_home)
    RadioButton rbHome;
  //  @BindView(R.id.rb_friend)
    RadioButton rbFriend;
  //  @BindView(R.id.rb_order)
    RadioButton rbOrder;
   // @BindView(R.id.rb_me)
    RadioButton rbMe;
  //  @BindView(R.id.hometab_radio)
    RadioGroup hometabRadio;
    private FragmentController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButterKnife.bind(this);
        frameLayout=findViewById(R.id.frame_layout);
        rbHome=findViewById(R.id.rb_home);
        rbFriend=findViewById(R.id.rb_friend);
        rbOrder=findViewById(R.id.rb_order);
        rbMe=findViewById(R.id.rb_me);
        hometabRadio=findViewById(R.id.hometab_radio);
        initListener();
    }

    @SuppressLint("MissingSuperCall")
    protected void onSaveInstanceState(Bundle outState)
    {
//如果用以下这种做法则不保存状态，再次进来的话会显示默认tab
//总是执行这句代码来调用父类去保存视图层的状态
//super.onSaveInstanceState(outState);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i)
    {
        switch (i)
        {
            case R.id.rb_home:
                controller.showFragment(0);
                break;
            case R.id.rb_friend:
                controller.showFragment(1);
                break;
            case R.id.rb_order:
                controller.showFragment(2);
                break;
            case R.id.rb_me:
                controller.showFragment(3);
                break;
        }
    }

    private void initListener()
    {
        if (controller == null)
        {
            controller = FragmentController.getInstance(this, R.id.frame_layout);
            controller.showFragment(0);
            rbHome.setChecked(true);
            hometabRadio.setOnCheckedChangeListener(this);
        }
    }

    @Override
    public void onBackPressed()
    {
        if (!BackHandlerHelper.handleBackPress(this))
        {
            super.onBackPressed();
        }
    }
}
