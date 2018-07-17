package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
import zhuyekeji.zhengzhou.jxlifecircle.api.Jxapi;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.login.CheZhuWanShanActivity;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.FilesUtil;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;
import zhuyekeji.zhengzhou.jxlifecircle.widget.CircleImageView;

public class UserInfoActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.im_user)
    CircleImageView imUser;
    @BindView(R.id.rl_touxian)
    RelativeLayout rlTouxian;
    @BindView(R.id.rl_lianxi)
    RelativeLayout rlLianxi;
    @BindView(R.id.rl_mobile)
    RelativeLayout rlMobile;
    @BindView(R.id.rl_cd_number)
    RelativeLayout rlCdNumber;
    @BindView(R.id.tv_jiashi)
    TextView tvJiashi;
    @BindView(R.id.rl_jiashi)
    RelativeLayout rlJiashi;
    @BindView(R.id.rl_bank)
    RelativeLayout rlBank;
    @BindView(R.id.ed_username)
    EditText edUsername;
    @BindView(R.id.tv_lianxiname)
    EditText tvLianxiname;
    @BindView(R.id.tv_mobile)
    EditText tvMobile;
    @BindView(R.id.tv_cd_number)
    EditText tvCdNumber;
    @BindView(R.id.baocun)
    TextView baocun;
    private String mImagePath;
    private int fileid=0;

    @Override
    public int getViewId()
    {
        return R.layout.activity_user_info;
    }

    @Override
    protected void processLogic()
    {
        tvTitle.setText("个人资料");
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

    @OnClick({R.id.rl_back, R.id.rl_touxian, R.id.rl_nickname, R.id.rl_lianxi, R.id.rl_mobile,
            R.id.rl_cd_number, R.id.rl_jiashi, R.id.rl_bank,R.id.baocun})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_touxian:
                getTu();
                break;
            case R.id.rl_nickname:
                break;
            case R.id.rl_lianxi:
                break;
            case R.id.rl_mobile:
                Intent intent3 = new Intent(UserInfoActivity.this, BingPhoneActivity.class);
                intent3.putExtra("phone",tvMobile.getText().toString());
                startActivity(intent3);
                break;
            case R.id.rl_cd_number:
                Intent intent = new Intent(UserInfoActivity.this, CDActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_jiashi:
                Intent intent2 = new Intent(UserInfoActivity.this, CheZhuWanShanActivity.class);
                intent2.putExtra("type", "2");
                startActivity(intent2);
                break;
            case R.id.rl_bank:
                Intent intent1 = new Intent(UserInfoActivity.this, AddBankActivity.class);
                startActivity(intent1);
                break;
            case R.id.baocun:
                String username=edUsername.getText().toString().trim();
                String lianxiren=tvLianxiname.getText().toString().trim();
                String mobile=tvMobile.getText().toString().trim();
                String cdnumber=tvCdNumber.getText().toString().trim();

                if (lianxiren==null||lianxiren.length()==0)
                {
                    ToastUtils.showShort("请输入输入联系人");
                    return;
                }
                if (mobile==null||mobile.length()==0)
                {
                    ToastUtils.showShort("请输入手机号");
                    return;
                }
                if (cdnumber==null||cdnumber.length()==0)
                {
                    ToastUtils.showShort("请输入身份证号码");
                    return;
                }
                JxApiCallBack.eduserinfo(getToken(),username,lianxiren,fileid,2,UserInfoActivity.this,callBack);
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
                .start(UserInfoActivity.this, PhotoPicker.REQUEST_CODE);
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
                mImagePath = s;
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
                case 1:
                    if (JsonUtile.isTrue(result.body()))
                    {

                        try
                        {
                            JSONObject object=new JSONObject(JsonUtile.getbody(result.body()));
                            fileid = object.getInt("fileid");
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                        Glide.with(App.getInstance()).load(mImagePath).into(imUser);
                    } else
                    {
                        ToastUtils.showShort(JsonUtile.getresulter(result.body()));
                    }
                    break;
                case 2:
                    if (JsonUtile.isTrue(result.body()))
                    {
                        ToastUtils.showShort(JsonUtile.getresulter(result.body()));
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
