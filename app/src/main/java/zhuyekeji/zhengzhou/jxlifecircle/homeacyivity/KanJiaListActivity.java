package zhuyekeji.zhengzhou.jxlifecircle.homeacyivity;

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
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.KanJiaAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.SelectAddressAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.bean.JiFenOrderBean;
import zhuyekeji.zhengzhou.jxlifecircle.bean.KanJiaBean;
import zhuyekeji.zhengzhou.jxlifecircle.bean.MyAddrseeBean;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

public class KanJiaListActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.im_kj)
    ImageView imKj;
    @BindView(R.id.rv_kj)
    RecyclerView rvKj;
    private KanJiaAdapter adapter;
    private List<KanJiaBean> beans = new ArrayList<>();
    private int position;

    @Override
    public int getViewId()
    {
        return R.layout.activity_kan_jia_list;
    }

    @Override
    protected void processLogic()
    {
        JxApiCallBack.kanjia(getToken(),1,this,callBack);
        adapter = new KanJiaAdapter(this, R.layout.kanjia_item, beans);
        rvKj.setLayoutManager(new LinearLayoutManager(this));
        rvKj.setAdapter(adapter);
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener()
        {
            @Override
            public void onItemClick(View view, int i)
            {
                position=i;
                if (true)
                {
                    showPopup0(view);
                }else {
                    Intent intent=new Intent(KanJiaListActivity.this,KanJiaShareActivity.class);
                    startActivity(intent);
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

    @OnClick({R.id.rl_back, R.id.im_kj})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.im_kj:
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
                case 1:
                    if (JsonUtile.getCode(result.body()).equals("200"))
                    {
                        List list= Arrays.asList(new Gson().fromJson(JsonUtile.getbody(result.body()),KanJiaBean[].class));
                        beans.addAll(list);
                        adapter.notifyDataSetChanged();
                    }else {
                        ToastUtils.showShort(JsonUtile.getresulter(result.body()));
                    }
                    break;
                case 2:
                    if (JsonUtile.getCode(result.body()).equals("200"))
                    {
                        List list= Arrays.asList(new Gson().fromJson(JsonUtile.getbody(result.body()),MyAddrseeBean[].class));
                        addrseeBeans.addAll(list);
                        addressAdapter.notifyDataSetChanged();
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
private SelectAddressAdapter addressAdapter;
private List<MyAddrseeBean> addrseeBeans=new ArrayList<>();
    private void showPopup0(View parent)//房屋信息
    {
        JxApiCallBack.myaddress(getToken(),2,KanJiaListActivity.this,callBack);
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.layout_address, null);
        final PopupWindow popWindow = new PopupWindow(contentView,
                RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT,
                true);
        final LinearLayout ll_add_address=contentView.findViewById(R.id.ll_add_address);
        final RelativeLayout rl_select_address=contentView.findViewById(R.id.rl_select_address);
        final RecyclerView address=contentView.findViewById(R.id.rv_address);
        contentView.findViewById(R.id.cha2).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                popWindow.dismiss();
            }
        });
        contentView.findViewById(R.id.cha3).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                popWindow.dismiss();
            }
        });
        address.setLayoutManager(new LinearLayoutManager(KanJiaListActivity.this));
        addressAdapter=new SelectAddressAdapter(KanJiaListActivity.this,R.layout.item_select_address,addrseeBeans);
        address.setAdapter(addressAdapter);
        TextView textView=new TextView(KanJiaListActivity.this);
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,130);
        params.leftMargin=22;
        params.setMargins(52,60,0,20);
        textView.setLayoutParams(params);
        textView.setText("新增收货地址");
        textView.setTextSize(14);
        textView.setTextColor(getResources().getColor(R.color.title_666));
        addressAdapter.addFooterView(textView);
        textView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                rl_select_address.setVisibility(View.GONE);
                ll_add_address.setVisibility(View.VISIBLE);
            }
        });
        addressAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener()
        {
            @Override
            public void onItemClick(View view, int i)
            {
                Intent intent=new Intent(KanJiaListActivity.this,KanJiaShareActivity.class);
                intent.putExtra("porid",adapter.getItem(position).getId());
                startActivity(intent);
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
