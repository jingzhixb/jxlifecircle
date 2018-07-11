package zhuyekeji.zhengzhou.jxlifecircle.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.ButterKnife;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.BarUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.KeyboardUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.NetWorkStateReceiver;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.SPUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.SpanUtils;


public abstract class BaseActivity extends AppCompatActivity
{
    protected Context mContext;
    private ConnectivityManager manager;
    NetWorkStateReceiver netWorkStateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 锁定竖屏
        setContentView(getViewId());
        mContext = getActivityContext();
        setstatucolor(this,getResources().getColor(R.color.jxcolor));
        KeyboardUtils.hideSoftInput(this);
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//首次进入界面不让软键盘弹出
        initView();
        ButterKnife.bind(this);
        initdata();

        //MIUI 9「状态栏黑色字符」
//        Window window = getWindow();
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        setStatusBarDarkMode(false,this);
    }

    public void setstatucolor(Activity activity,int colorid)
    {
        BarUtils.setStatusBarColor(activity, colorid,000,false);
    }

    public String getToken()
    {
        return SPUtils.getInstance().getString("token");
    }


    public String getCity_id()
    {
        return SPUtils.getInstance().getString("city_id");
    }

    public abstract int getViewId();

    /**
     * 获取状态栏高度
     *
     * @return
     */

    public int getStatusBarHeight()
    {
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
        {
            //根据资源ID获取响应的尺寸值
            return getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public void setStatusBarDarkMode(boolean darkmode, Activity activity)
    {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try
        {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void initView()
    {

    }

    private void initdata()
    {
        setListener();
        processLogic();
    }

    /**
     * 业务逻辑处理，主要与后端交互
     */
    protected abstract void processLogic();

    /**
     * 设置各种事件的监听器
     */
    protected abstract void setListener();


    /**
     * Activity.this
     */
    protected abstract Context getActivityContext();

    /**
     * 子类可以重写改变状态栏颜色
     */
    protected int setStatusBarColor()
    {
        return getColorPrimary();
    }

    /**
     * 子类可以重写决定是否使用透明状态栏
     */
    protected boolean translucentStatusBar()
    {
        return false;
    }

    /**
     * 获取主题色
     */
    public int getColorPrimary()
    {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

    /**
     * 获取深主题色
     */
    public int getDarkColorPrimary()
    {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
        return typedValue.data;
    }

    /**
     * 初始化 Toolbar
     */
    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title)
    {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, int resTitle)
    {
        initToolBar(toolbar, homeAsUpEnabled, getString(resTitle));
    }

    public void showToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private ProgressDialog dialog;

    public void showLoading()
    {
        if (dialog != null && dialog.isShowing()) return;
        dialog = new ProgressDialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("请求网络中...");
        dialog.show();
    }

    public void dismissLoading()
    {
        if (dialog != null && dialog.isShowing())
        {
            dialog.dismiss();
        }
    }

    public void displayImage(String url, ImageView imageView)
    {
//        Glide.with(App.getInstance())//
//                .load(url)//
//                .error(R.mipmap.ic_launcher)//
//                .into(imageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:// 点击返回图标事件
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume()
    {
        // TODO Auto-generated method stub
        if (netWorkStateReceiver == null)
        {
            netWorkStateReceiver = new NetWorkStateReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkStateReceiver, filter);
        System.out.println("注册");
        super.onResume();
//		StatService.onPause(mContext);
    }

    @Override
    protected void onPause()
    {
        // TODO Auto-generated method stub
        unregisterReceiver(netWorkStateReceiver);
        System.out.println("注销");
        super.onPause();
    }


    @Override
    protected void onDestroy()
    {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

}
