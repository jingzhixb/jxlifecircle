package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import javax.json.Json;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPicker;
import zhuyekeji.zhengzhou.jxlifecircle.App;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.login.ShangJiaWanShanActivity;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.FilesUtil;
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
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.rl_renzheng)
    RelativeLayout rlRenzheng;
    @BindView(R.id.tv_tijiao)
    TextView tvTijiao;
    @BindView(R.id.tv_address)
    EditText tvAddress;
    private String mShopId,mType;
    private int mImageId = 0;
    private int id1,id2,id3;

    @Override
    public int getViewId()
    {
        return R.layout.activity_shop_info;
    }

    @Override
    protected void processLogic()
    {
        tvTitle.setText("商家信息");
        mShopId=getIntent().getStringExtra("shopid");
        mType=getIntent().getStringExtra("type");
        tvType.setText(mType);
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

    @OnClick({R.id.rl_back, R.id.rl_xinxiang, R.id.rl_renzheng, R.id.tv_tijiao})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_xinxiang://店铺牌子
                getTu();
                break;
            case R.id.rl_renzheng://认证资料
                Intent intent = new Intent(ShopInfoActivity.this, ShangJiaWanShanActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_tijiao://提交
                if (mImageId == 0)
                {
                    ToastUtils.showShort("请上传店铺招牌");
                    return;
                }
                String name = edName.getText().toString().trim();
                if (name == null || name.length() == 0)
                {
                    ToastUtils.showShort("请输入店铺名字");
                    return;
                }
                String phone = edPhone.getText().toString().trim();
                if (!RegexUtils.isMobileExact(phone))
                {
                    ToastUtils.showShort("请输入正确的手机号");
                    return;
                }
                String address = tvAddress.getText().toString();
                if (address == null || address.length() == 0)
                {
                    ToastUtils.showShort("请输入店铺地址");
                    return;
                }
                //店铺信息提交审核
                JxApiCallBack.shop_check(getToken(), Integer.parseInt(mShopId), mImageId, name, phone, address, 2, ShopInfoActivity.this, callBack);
                break;
        }
    }

    private void getTu()
    {
        PhotoPicker.builder()
                .setPhotoCount(1)
                .setShowCamera(true)
                .setShowGif(false)
                .setPreviewEnabled(false)
                .start(ShopInfoActivity.this, PhotoPicker.REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==1)
        {
            id1=data.getIntExtra("id1",0);
            id2=data.getIntExtra("id2",0);
            id3=data.getIntExtra("id3",0);
        }
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE)
        {
            ArrayList<String> imgUris = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            for (String s : imgUris)
            {
                Glide.with(App.getInstance()).load(s).into(image);
                File file = FilesUtil.getSmallBitmap(this, s);
                JxApiCallBack.up_img(file, 1, this, callBack);
            }
        }
    }

    CallBack callBack = new CallBack()
    {
        @Override
        public void onSuccess(int what, Response<String> result)
        {

            switch (what)
            {
                case 2://提交审核
                    break;
                case 1://上传店铺照片
                    if (JsonUtile.getCode(result.body()).equals("200"))
                    {
                        try
                        {
                            JSONObject object=new JSONObject(JsonUtile.getbody(result.body()));
                            mImageId = object.getInt("fileid");
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }else {
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
}
