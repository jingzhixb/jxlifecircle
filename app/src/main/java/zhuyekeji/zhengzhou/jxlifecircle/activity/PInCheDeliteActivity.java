package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lzy.okgo.model.Response;

import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
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
        String id=getIntent().getStringExtra("id");
        JxApiCallBack.pinchedelite(getToken(),1,Integer.parseInt(id),1,this,callBack);
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
    CallBack callBack=new CallBack()
    {
        @Override
        public void onSuccess(int what, Response<String> result)
        {
            switch (what)
            {
                case 1:

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
