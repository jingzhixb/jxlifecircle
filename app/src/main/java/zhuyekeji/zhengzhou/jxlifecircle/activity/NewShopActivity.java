package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPicker;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.ShopImageAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.homeacyivity.PinCheActivity;
import zhuyekeji.zhengzhou.jxlifecircle.homeacyivity.PinCheFaBuActivity;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.EncodeUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.FileIOUtils;
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
    @BindView(R.id.im_tongyong)
    ImageView imTongyong;
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
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.ed_guize)
    EditText edGuize;
    @BindView(R.id.tv_start_date)
    TextView tvStartDate;
    @BindView(R.id.tv_end_date)
    TextView tvEndDate;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    private int mImgId = 0;
    private ShopImageAdapter adapter;
    private List<String> images = new ArrayList<>();
    private String[] ids;
    ArrayList getid = new ArrayList<>();
    private String label;
    private String shopid;
    private String classid;
    private String mStarteDate;
    private int select_id;
    private String mEndDate;
    private String mStartTime;
    private String mEndTime;
    private int is_week=2;
    private String content;

    @Override
    public int getViewId()
    {
        return R.layout.activity_new_shop;
    }

    @Override
    protected void processLogic()
    {
        String type = getIntent().getStringExtra("type");
        shopid = getIntent().getStringExtra("shopid");
        if (type.equals("1"))
        {
            tvTitle.setText("新建商品");
        } else if (type.equals("2"))
        {
            tvTitle.setText("编辑商品");
            //  JxApiCallBack.set_shelf(getToken(),);
        }

        adapter = new ShopImageAdapter(this, R.layout.item_shop_img, images);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvList.setLayoutManager(manager);
        rvList.setAdapter(adapter);
        RelativeLayout view = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.image_zw, null);
        adapter.addFooterView(view);
        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getTu();
            }
        });
        adapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter.OnRecyclerViewItemChildClickListener()
        {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i)
            {
                switch (view.getId())
                {
                    case R.id.del:
                        if (adapter.getItemCount() >= getid.size())
                        {
                            getid.remove(i);
                            adapter.remove(i);
                        }
                        break;
                }
            }
        });


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

    @OnClick({R.id.rl_back, R.id.rl_biaoqian, R.id.fenlei, R.id.rl_guize, R.id.tv_baocun, R.id.rl_miaoshu,
            R.id.tv_start_date, R.id.tv_end_date, R.id.tv_start_time, R.id.tv_end_time,R.id.im_tongyong})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.tv_start_date://有效期开始
                select_id = 1;
                showPopup0(view);
                break;
            case R.id.tv_end_date://有效期结束
                select_id = 2;
                showPopup0(view);
                break;
            case R.id.tv_start_time://使用时间开始
                select_id = 3;
                showPopup0(view);
                break;
            case R.id.tv_end_time://使用时间结束
                select_id = 4;
                showPopup0(view);
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.im_tongyong:
               if (is_week==2)
               {
                   imTongyong.setBackgroundResource(R.mipmap.on);
                   is_week=1;
               }else {
                   imTongyong.setBackgroundResource(R.mipmap.off);
                   is_week=2;
               }
                break;
            case R.id.rl_biaoqian://标签
                Intent intent = new Intent(NewShopActivity.this, BiaoQianActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.fenlei://分类
                Intent intent2 = new Intent(NewShopActivity.this, TypeActivity.class);
                intent2.putExtra("shopid", shopid);
                startActivityForResult(intent2, 2);
                break;
            case R.id.rl_guize://使用规则
                break;
            case R.id.tv_baocun://保存
                if (getid.size() == 0)
                {
                    ToastUtils.showShort("请上传商品图片");
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
                if (label == null || label.length() == 0)
                {
                    ToastUtils.showShort("请输入商品标签");
                    return;
                }
                if (classid == null || classid.length() == 0)
                {
                    ToastUtils.showShort("分类不能为空");
                    return;
                }
                String price = edPrice.getText().toString().trim();
                if (price == null || price.length() == 0)
                {
                    ToastUtils.showShort("请输入价格");
                    return;
                }
                String shopprice = edShopprice.getText().toString().trim();
                if (shopprice == null || shopprice.length() == 0)
                {
                    ToastUtils.showShort("请输入市场价");
                    return;
                }
                String ids="";
                if (getid.size()!=0)
                {
                    for (int i=0;i<getid.size();i++)
                    {
                        ids+=getid.get(i)+",";
                    }
                    ids=ids.substring(0,ids.length()-1);
                }else {
                    ToastUtils.showShort("请上传商品图片");
                    return;
                }
                String huize=edGuize.getText().toString().trim();
                if (huize==null||huize.length()==0)
                {
                    ToastUtils.showShort("请填写使用规则");
                    return;
                }
                JxApiCallBack.add_shop(getToken(),Integer.parseInt(shopid),1,ids,
                        shopname,miaoshu,label,null,price,shopprice,mStarteDate+","+mEndDate
                        ,mStartTime,mEndTime,is_week,huize,2,NewShopActivity.this,callBack);
                break;
            case R.id.rl_miaoshu://商品描述
                Intent intent1 = new Intent(NewShopActivity.this, MiaoShuActivity.class);
                startActivityForResult(intent1,3);
                break;
        }
    }

    private void getTu()
    {
        PhotoPicker.builder()
                .setPhotoCount(5)
                .setShowCamera(true)
                .setSelected((ArrayList<String>) images)
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
            StringBuffer buffer = new StringBuffer();
            ArrayList<String> imgUris = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            images.clear();
            for (String s : imgUris)
            {
                File file = FilesUtil.getSmallBitmap(this, s);
                String sFile = EncodeUtils.base64Encode2String(FileIOUtils.readFile2BytesByChannel(file));
                buffer.append(sFile + ",");
                images.add(s);
            }
            adapter.notifyDataSetChanged();
            buffer.delete(buffer.length() - 1, buffer.length());
            JxApiCallBack.upduotu(buffer.toString(), 1, NewShopActivity.this, callBack);
        }
        if (requestCode == 1 && resultCode == 1)//标签
        {
            String countLabel = data.getStringExtra("count");
            label = data.getStringExtra("label");
            tvIaoqian.setText("一共" + countLabel + "个标签");

        }
        if (requestCode == 2 && resultCode == 2)//分类
        {
            classid = data.getStringExtra("id");
            String calssname = data.getStringExtra("name");
            tvFenlei.setText(calssname);
        }
        if (requestCode==3&&resultCode==3)//使用规则
        {
            content = data.getStringExtra("content");
            edMiaoshu.setText(content);
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
                            JSONObject object = new JSONObject(JsonUtile.getbody(result.body()));
                            String fileid = object.getString("fileid");
                            ids = fileid.split(",");
                            for (int i = 0; i < ids.length; i++)
                            {
                                getid.add(ids[i]);
                            }
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
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
    String date;
    String time;
    @SuppressLint("NewApi")
    private void showPopup0(View parent)//房屋信息
    {

        View contentView = LayoutInflater.from(this).inflate(
                R.layout.layout, null);
        final PopupWindow popWindow = new PopupWindow(contentView,
                RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT,
                true);
        DatePicker  picker=contentView.findViewById(R.id.data_p);
        TimePicker timep=contentView.findViewById(R.id.time_p);
        if (select_id==1||select_id==2)
        {
            timep.setVisibility(View.GONE);
            picker.setVisibility(View.VISIBLE);
        }else {
            timep.setVisibility(View.VISIBLE);
            picker.setVisibility(View.GONE);
        }
        timep.setIs24HourView(true);
        timep.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener()
        {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1)
            {
                time=i+":"+i1;
            }
        });
        contentView.findViewById(R.id.quren).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (select_id==1)
                {
                    mStarteDate=date;
                    tvStartDate.setText(mStarteDate);
                }
                if (select_id==2)
                {
                    mEndDate = date;
                    tvEndDate.setText(mEndDate);
                }
                 if (select_id==3)
                 {
                     mStartTime = time;
                     tvStartTime.setText(mStartTime);
                 }
                 if (select_id==4)
                 {
                     mEndTime = time;
                     tvEndTime.setText(mEndTime);
                 }
                popWindow.dismiss();
            }
        });
        picker.setCalendarViewShown(false);
   picker.init(2018, 7, 14, new DatePicker.OnDateChangedListener()
   {
       @Override
       public void onDateChanged(DatePicker datePicker, int i, int i1, int i2)
       {
           date = i+"-"+i1+"-"+i2;
       }
   });

//        popWindow.setAnimationStyle(R.style.popupWindowAnimation);//设置动画
        popWindow.setFocusable(true);
        // 设置允许在外点击消失
        popWindow.setOutsideTouchable(true);
        // 设置背景，这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        //软键盘不会挡着popupwindow
        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        //设置SelectPicPopupWindow弹出窗体的背景
//        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.alpha = 0.7f;
//        getWindow().setAttributes(lp);
        //设置菜单显示的位置
        popWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        //监听菜单的关闭事件
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener()
        {
            @Override
            public void onDismiss()
            {
//                WindowManager.LayoutParams lp = getWindow().getAttributes();
//                lp.alpha = 1f;
//                getWindow().setAttributes(lp);
            }
        });
        //监听触屏事件
        popWindow.setTouchInterceptor(new View.OnTouchListener()
        {
            public boolean onTouch(View view, MotionEvent event)
            {
                return false;
            }
        });
    }
}
