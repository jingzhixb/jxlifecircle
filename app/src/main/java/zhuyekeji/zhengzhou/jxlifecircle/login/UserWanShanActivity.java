package zhuyekeji.zhengzhou.jxlifecircle.login;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.App;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.activity.UserInfoActivity;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.CropUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.FilesUtil;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.PermissionUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;
import zhuyekeji.zhengzhou.jxlifecircle.widget.CircleImageView;

public class UserWanShanActivity extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.wancheng)
    TextView wancheng;
    @BindView(R.id.tv_skip)
    TextView tvSkip;
    @BindView(R.id.im_img)
    CircleImageView imImg;
    private String mPhone,mPassword;
    private File mFile;
    private Uri mUri;
    private static final int REQUEST_CODE_TAKE_PHOTO = 1;
    private static final int REQUEST_CODE_ALBUM = 2;
    private static final int REQUEST_CODE_CROUP_PHOTO = 3;
    private String mFileid;

    @Override
    public int getViewId()
    {
        return R.layout.activity_user_wan_shan;
    }

    @Override
    protected void processLogic()
    {
        tvTitle.setText("完善资料");
        mPassword=getIntent().getStringExtra("password");
        mPhone=getIntent().getStringExtra("phone");
        init();
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

    @OnClick({R.id.rl_back, R.id.wancheng, R.id.tv_skip,R.id.im_img})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.wancheng://完成
                break;
            case R.id.tv_skip://跳过
                JxApiCallBack.user_register(mPhone,mPassword,"","",1, UserWanShanActivity.this,callBack);
                break;
            case R.id.im_img:
                chooseType();
                break;
        }
    }
    /**
     * 建立相机存储的缓存的路径
     */
    private void init()
    {
        mFile = new File(FilesUtil.getCachePath(this), "idcard.jpg");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
        {
            mUri = Uri.fromFile(mFile);
        } else
        {
            //通过FileProvider创建一个content类型的Uri(android 7.0需要这样的方法跨应用访问)
            mUri = FileProvider.getUriForFile(App.getInstance(), "zhuyekeji.zhengzhou.jxlifecircle.FileProvider", mFile);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ALBUM && data != null) {
            Uri newUri;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                newUri = Uri.parse("file:///" + CropUtils.getPath(this, data.getData()));
            } else {
                newUri = data.getData();
            }
            if (newUri != null) {
                startPhotoZoom(newUri);
            } else {
                Toast.makeText(this, "没有得到相册图片", Toast.LENGTH_LONG).show();
            }
            //相机
        } else if (requestCode == REQUEST_CODE_TAKE_PHOTO) {
            startPhotoZoom(mUri);
        } else if (requestCode == REQUEST_CODE_CROUP_PHOTO) {
            uploadAvatarFromPhoto();
        }
    }

    private void chooseType() {
        final String[] stringItems = {"拍照", "从相册选取"};
        final ActionSheetDialog dialog = new ActionSheetDialog(mContext, stringItems, null);
        dialog.title("选择头像").titleTextSize_SP(14.5f).show();


        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @SuppressLint("WrongConstant")
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    //拍照
                    case 0:
                        if (PermissionUtils.isGranted(Manifest.permission.CAMERA)) {
                            uploadAvatarFromPhotoRequest();
                            dialog.dismiss();
                        }else {
                            PermissionUtils.permission(Manifest.permission.CAMERA).request();
                        }
                        break;
                    //相册
                    case 1:
                        if (PermissionUtils.isGranted(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            uploadAvatarFromAlbumRequest();
                            dialog.dismiss();
                        }else {
                            PermissionUtils.permission(Manifest.permission.READ_EXTERNAL_STORAGE).request();
                        }
                        break;
                    default:
                        break;
                }

            }
        });
    }

    private void uploadAvatarFromPhoto() {
        compressAndUploadAvatar(mFile.getPath());
    }


    /**
     * 压缩加载头像
     *
     * @param fileSrc
     */
    private void compressAndUploadAvatar(String fileSrc) {
        final File cover = FilesUtil.getSmallBitmap(this, fileSrc);
        //加载本地图片
        Uri uri = Uri.fromFile(cover);
        Glide.with(App.getInstance()).load(uri).into(imImg);
        File file= FilesUtil.getFileByUri(uri, this);
        if (file!=null)
        {
            JxApiCallBack.up_img(file,1,this,callBack);
        }
        //上传文件

//        DreamApi.uploadAvator(this, 0x002, token, file, uploadCallBack);
    }
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra("crop", "true");// crop=true 有这句才能出来最后的裁剪页面.
        intent.putExtra("aspectX", 1);// 这两项为裁剪框的比例.
        intent.putExtra("aspectY", 0.8);// x:y=1:1
//        intent.putExtra("outputX", 400);//图片输出大小
//        intent.putExtra("outputY", 400);
        intent.putExtra("output", Uri.fromFile(mFile));
        intent.putExtra("outputFormat", "JPEG");// 返回格式
        startActivityForResult(intent, REQUEST_CODE_CROUP_PHOTO);
    }
    /**
     * photo
     */
    private void uploadAvatarFromPhotoRequest() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
    }

    /**
     * album
     */
    private void uploadAvatarFromAlbumRequest() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_CODE_ALBUM);
    }
    CallBack callBack=new CallBack()
    {
        @Override
        public void onSuccess(int what, Response<String> result)
        {
                 switch (what)
                 {
                     case 1://图片上传
                         try
                         {
                             JSONObject object=new JSONObject(result.body());
                             String code=object.getString("code");
                             if (code.equals("200"))
                             {
                                 JSONObject jsonObject=new JSONObject(object.getString("body"));
                                 mFileid = jsonObject.getString("fileid");
                             }else {
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
