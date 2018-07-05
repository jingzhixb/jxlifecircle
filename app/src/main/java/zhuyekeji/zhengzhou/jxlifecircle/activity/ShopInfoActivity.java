package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.RegexUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

public class ShopInfoActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.rl_xinxiang)
    RelativeLayout rlXinxiang;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.rl_renzheng)
    RelativeLayout rlRenzheng;
    @BindView(R.id.tv_tijiao)
    TextView tvTijiao;
    private int mShopId;
    private int mImageId=0;
    @Override
    public int getViewId()
    {
        return R.layout.activity_shop_info;
    }

    @Override
    protected void processLogic()
    {
tvTitle.setText("商家信息");
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

    @OnClick({R.id.rl_back, R.id.image, R.id.rl_address, R.id.rl_renzheng, R.id.tv_tijiao})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.image://店铺牌子
                break;
            case R.id.rl_address://店铺地址
                break;
            case R.id.rl_renzheng://认证资料
                break;
            case R.id.tv_tijiao://提交
                if (mImageId==0)
                {
                    ToastUtils.showShort("请上传店铺招牌");
                    return;
                }
                String name=edName.getText().toString().trim();
                if (name==null||name.length()==0)
                {
                    ToastUtils.showShort("请输入店铺名字");
                    return;
                }
                String phone=edPhone.getText().toString().trim();
                if (!RegexUtils.isMobileExact(phone))
                {
                    ToastUtils.showShort("请输入正确的手机号");
                    return;
                }
                String address=tvAddress.getText().toString();
                if (address==null||address.length()==0)
                {
                    ToastUtils.showShort("请输入店铺地址");
                    return;
                }
                //店铺信息提交审核
                JxApiCallBack.shop_check(getToken(),mShopId,mImageId,name,phone,address,2,ShopInfoActivity.this,callBack);
                break;
        }
    }

    CallBack callBack=new CallBack()
    {
        @Override
        public void onSuccess(int what, Response<String> result)
        {

            switch (what)
            {
                case 2://提交审核
                    break;
                case 1://上传店铺照片
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
