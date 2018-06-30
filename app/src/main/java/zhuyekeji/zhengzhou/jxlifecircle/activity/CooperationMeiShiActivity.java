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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPicker;
import zhuyekeji.zhengzhou.jxlifecircle.App;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.FilesUtil;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.SPUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

public class CooperationMeiShiActivity extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_mingcheng)
    TextView tvMingcheng;
    @BindView(R.id.ed_mingcheng)
    EditText edMingcheng;
    @BindView(R.id.tv_zhaopai)
    TextView tvZhaopai;
    @BindView(R.id.im_zhaopai)
    ImageView imZhaopai;
    @BindView(R.id.rl_zhaopai)
    RelativeLayout rlZhaopai;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.rl_shangjia)
    RelativeLayout rlShangjia;
    @BindView(R.id.ed_faren)
    EditText edFaren;
    @BindView(R.id.ed_mobile)
    EditText edMobile;
    @BindView(R.id.image_zhizhao)
    ImageView imageZhizhao;
    @BindView(R.id.im_registerxieyi)
    ImageView imRegisterxieyi;
    @BindView(R.id.rl_zhizhao)
    RelativeLayout rlZhizhao;
    @BindView(R.id.tijiao)
    TextView tijiao;
    @BindView(R.id.image_zheng)
    ImageView imageZheng;
    @BindView(R.id.im_zheng)
    ImageView imZheng;
    @BindView(R.id.tv_zheng)
    TextView tvZheng;
    @BindView(R.id.rl_zheng)
    RelativeLayout rlZheng;
    @BindView(R.id.image_fan)
    ImageView imageFan;
    @BindView(R.id.im_fan)
    ImageView imFan;
    @BindView(R.id.tv_fan)
    TextView tvFan;
    @BindView(R.id.rl_fan)
    RelativeLayout rlFan;
    @BindView(R.id.tv_zhizhao)
    TextView tvZhizhao;
    private String mDianpuId, mZhengId, mFanId, mZhizhaoId;
    private int type = 0;
    private String img;

    @Override
    public int getViewId()
    {
        return R.layout.activity_cooperation_mei_shi;
    }

    @Override
    protected void processLogic()
    {
        String type = getIntent().getStringExtra("type");
        if (type.equals("1"))
        {
            tvTitle.setText("美食商家入驻");
        } else if (type.equals("2"))
        {
            tvTitle.setText("酒店商家入驻");
        } else if (type.equals("3"))
        {
            tvTitle.setText("境内度假商家入驻");
        } else if (type.equals("4"))
        {
            tvTitle.setText("综合商家入驻");
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_back, R.id.rl_zhaopai, R.id.rl_address, R.id.rl_shangjia, R.id.rl_zhizhao, R.id.tijiao, R.id.rl_zheng, R.id.rl_fan})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_zhaopai:
                getTu();
                type = 1;
                break;
            case R.id.rl_address://店铺地址
                Intent intent = new Intent(CooperationMeiShiActivity.this, AddressSelectActivity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.rl_shangjia://商家分类
                Intent intent1 = new Intent(CooperationMeiShiActivity.this, ShopTypeActivity.class);
                startActivity(intent1);
                break;
            case R.id.rl_zhizhao://营业执照
                type = 4;
                getTu();
                break;
            case R.id.tijiao:
              // JxApiCallBack.shopjoin();
                break;
            case R.id.rl_zheng:
                type = 2;
                getTu();
                break;
            case R.id.rl_fan:
                type = 3;
                getTu();
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
                .start(CooperationMeiShiActivity.this, PhotoPicker.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE)
        {
            ArrayList<String> imgUris = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            for (String s : imgUris)
            {
                img = s;
                File file = FilesUtil.getSmallBitmap(this, s);
                JxApiCallBack.up_img(file, 1, this, callBack);
            }
        }
        if (requestCode==1)
        {
            String address= SPUtils.getInstance().getString("address");
            SPUtils.getInstance().put("address","");
            if (address!=null&&!address.equals(""))
            {
                tvAddress.setText(address);
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
                case 1:
                    try
                    {
                        JSONObject object = new JSONObject(result.body());
                        String code = object.getString("code");
                        if (code.equals("200"))
                        {
                            JSONObject jsonObject = new JSONObject(object.getString("body"));
                            if (type == 1)
                            {
                                mDianpuId = jsonObject.getString("fileid");
                                Glide.with(App.getInstance()).load(img).into(imZhaopai);
                            } else if (type == 2)
                            {
                                mZhengId = jsonObject.getString("fileid");
                                imageZheng.setVisibility(View.VISIBLE);
                                imZheng.setVisibility(View.GONE);
                                tvZheng.setVisibility(View.GONE);
                                Glide.with(App.getInstance()).load(img).into(imageZheng);
                            } else if (type == 3)
                            {
                                mFanId = jsonObject.getString("fileid");
                                imageFan.setVisibility(View.VISIBLE);
                                imFan.setVisibility(View.GONE);
                                tvFan.setVisibility(View.GONE);
                                Glide.with(App.getInstance()).load(img).into(imZhaopai);
                            } else if (type == 4)
                            {
                                mZhizhaoId = jsonObject.getString("fileid");
                                imageZhizhao.setVisibility(View.VISIBLE);
                                imRegisterxieyi.setVisibility(View.GONE);
                                tvZhizhao.setVisibility(View.GONE);
                                Glide.with(App.getInstance()).load(img).into(imZhaopai);
                            }
                        } else
                        {
                            ToastUtils.showShort(object.getString("result"));
                        }
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
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
