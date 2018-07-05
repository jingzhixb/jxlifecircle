package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;

public class PInCheDeliteActivity extends BaseActivity
{

/*
*
* //启动编辑短信的界面
Intent intent = new Intent(Intent.ACTION_VIEW);
intent.setType("vnd.android-dir/mms-sms");
 // intent.setData(Uri.parse("content://mms-sms/conversations/"));//此为号码
startActivity(intent);
* */
    @Override
    public int getViewId()
    {
        return R.layout.activity_pin_che_delite;
    }

    @Override
    protected void processLogic()
    {

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
}
