package zhuyekeji.zhengzhou.jxlifecircle.homeacyivity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;

public class PinCheFaBuActivity extends BaseActivity
{


    @Override
    public int getViewId()
    {
        return R.layout.activity_pin_che_fa_bu;
    }

    @Override
    protected void processLogic()
    {
      if (false)
      {
          //JxApiCallBack.addchefindren();
      }
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
