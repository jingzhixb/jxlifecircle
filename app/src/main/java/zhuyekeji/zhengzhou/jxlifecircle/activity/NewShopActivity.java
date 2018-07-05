package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.lzy.okgo.model.Response;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPicker;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.FilesUtil;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

public class NewShopActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_shopname)
    EditText tvShopname;
    @BindView(R.id.ed_miaoshu)
    TextView edMiaoshu;
    @BindView(R.id.tv_iaoqian)
    TextView tvIaoqian;
    @BindView(R.id.rl_biaoqian)
    RelativeLayout rlBiaoqian;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.fenlei)
    RelativeLayout fenlei;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.sp_start_youxiao)
    Spinner spStartYouxiao;
    @BindView(R.id.sp_end_youxiao)
    Spinner spEndYouxiao;
    @BindView(R.id.im_tongyong)
    ImageView imTongyong;
    @BindView(R.id.sp_start_time)
    Spinner spStartTime;
    @BindView(R.id.sp_end_time)
    Spinner spEndTime;
    @BindView(R.id.jia2)
    ImageView jia2;
    @BindView(R.id.rl_guize)
    RelativeLayout rlGuize;
    @BindView(R.id.tv_baocun)
    TextView tvBaocun;
    @BindView(R.id.tv_fenlei)
    TextView tvFenlei;
    @BindView(R.id.ed_price)
    EditText edPrice;
    @BindView(R.id.ed_shopprice)
    EditText edShopprice;
    private int mImgId = 0;

    @Override
    public int getViewId()
    {
        return R.layout.activity_new_shop;
    }

    @Override
    protected void processLogic()
    {
        String type=getIntent().getStringExtra("type");
        if (type.equals("1"))
        {
            tvTitle.setText("新建商品");
        }else if (type.equals("2"))
        {
            tvTitle.setText("编辑商品");
          //  JxApiCallBack.set_shelf(getToken(),);
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

    @OnClick({R.id.rl_back, R.id.rl_biaoqian, R.id.fenlei, R.id.rl_guize, R.id.tv_baocun, R.id.rl_miaoshu, R.id.im_img})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_biaoqian://标签
                Intent intent = new Intent(NewShopActivity.this, BiaoQianActivity.class);
                startActivity(intent);
                break;
            case R.id.fenlei://分类
                Intent intent2 = new Intent(NewShopActivity.this, TypeActivity.class);
                startActivity(intent2);
                break;
            case R.id.rl_guize://使用规则
                break;
            case R.id.tv_baocun://保存
                if (mImgId == 0)
                {
                    ToastUtils.showShort("请上传店铺招牌");
                    return;
                }
                String shopname = tvShopname.getText().toString().trim();
                if (shopname == null || shopname.length() == 0)
                {
                    ToastUtils.showShort("请输入商品名字");
                    return;
                }
                String miaoshu = edMiaoshu.getText().toString().trim();
                if (miaoshu == null || miaoshu.length() == 0)
                {
                    ToastUtils.showShort("请输入商品描述");
                    return;
                }
                String biaoqin = tvIaoqian.getText().toString().trim();
                if (biaoqin == null || biaoqin.length() == 0)
                {
                    ToastUtils.showShort("请输入商品标签");
                    return;
                }
                String fenlei = tvFenlei.getText().toString().trim();
                if (fenlei == null || fenlei.length() == 0)
                {
                    ToastUtils.showShort("分类不能为空");
                    return;
                }
                String price=edPrice.getText().toString().trim();
                if (price==null||price.length()==0)
                {
                    ToastUtils.showShort("请输入价格");
                    return;
                }
                String shopprice=edShopprice.getText().toString().trim();
                if (shopprice==null||shopprice.length()==0)
                {
                    ToastUtils.showShort("请输入市场价");
                    return;
                }
                //JxApiCallBack.add_shop();
                break;
            case R.id.rl_miaoshu://商品描述
                Intent intent1 = new Intent(NewShopActivity.this, MiaoShuActivity.class);
                startActivity(intent1);
                break;
            case R.id.im_img:
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
                .start(NewShopActivity.this, PhotoPicker.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE && data != null)
        {
            ArrayList<String> imgUris = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            for (String s : imgUris)
            {
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
