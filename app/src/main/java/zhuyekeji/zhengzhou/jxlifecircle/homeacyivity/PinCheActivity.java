package zhuyekeji.zhengzhou.jxlifecircle.homeacyivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.Gson;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.PinCheHotRouteAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.bean.HotRouteBean;
import zhuyekeji.zhengzhou.jxlifecircle.frament.home.CheFindRenFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.home.PinCheAllFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.home.RenFindCheFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.home.TianTianFrament;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;
import zhuyekeji.zhengzhou.jxlifecircle.widget.MyViewPage;
import zhuyekeji.zhengzhou.jxlifecircle.widget.VerticalScrollView;

public class PinCheActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.ed_start)
    EditText edStart;
    @BindView(R.id.ed_end)
    EditText edEnd;
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.rl_data)
    RelativeLayout rlData;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.rl_hot_route)
    RelativeLayout rlHotRoute;
    @BindView(R.id.rv_hot_search)
    RecyclerView rvHotSearch;
    @BindView(R.id.slidingtl)
    SlidingTabLayout slidingtl;
    @BindView(R.id.viewpage)
    MyViewPage viewpage;
    @BindView(R.id.scrollView)
    VerticalScrollView scrollView;
    private String[] titles = new String[]{"全部", "车找人", "人找车", "天天拼"};
    private ArrayList<Fragment> mFraments = new ArrayList<>();
    private List<HotRouteBean> beans = new ArrayList<>();
    private PinCheHotRouteAdapter adapter;
    private WindowManager windowManager;

    @Override
    public int getViewId()
    {
        return R.layout.activity_pin_che;
    }

    @Override
    protected void processLogic()
    {
        JxApiCallBack.gethot(getToken(), 1, 1, this, callBack);
        tvTitle.setText("顺分车");
        titleRight.setText("发布");
        mFraments.add(new PinCheAllFrament());
        mFraments.add(new CheFindRenFrament());
        mFraments.add(new RenFindCheFrament());
        mFraments.add(new TianTianFrament());
        slidingtl.setViewPager(viewpage, titles, this, mFraments);
        adapter = new PinCheHotRouteAdapter(this, R.layout.pinche_hot_item, beans);
        rvHotSearch.setLayoutManager(new GridLayoutManager(this, 4));
        rvHotSearch.setAdapter(adapter);
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

    @OnClick({R.id.rl_back, R.id.title_right, R.id.rl_data, R.id.tv_search, R.id.rl_hot_route})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.title_right://发布
                showPopup0(view);
                break;
            case R.id.rl_data:
                break;
            case R.id.tv_search:
                String setou = edStart.getText().toString().trim();
                String end = edEnd.getText().toString().trim();
                String time = tvData.getText().toString().trim();
                if (setou == null || setou.length() == 0)
                {
                    ToastUtils.showShort("请输入出发地点");
                    return;
                }
                if (end == null || end.length() == 0)
                {
                    ToastUtils.showShort("请输入目的地");
                    return;
                }
                if (time == null || time.length() == 0)
                {
                    ToastUtils.showShort("请输入出发时间");
                    return;
                }
                Intent intent=new Intent(PinCheActivity.this,PinCheSearchActivity.class);
                intent.putExtra("start",setou);
                intent.putExtra("end",end);
                intent.putExtra("data",time);
                startActivity(intent);
                break;
            case R.id.rl_hot_route:
                break;
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
                    if (JsonUtile.getCode(result.body()).equals("200"))
                    {
                        List list = Arrays.asList(new Gson().fromJson(JsonUtile.getbody(result.body()), HotRouteBean[].class));
                        beans.addAll(list);
                        adapter.notifyDataSetChanged();

                    } else
                    {
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

    private void showPopup0(View parent)//房屋信息
    {
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.pinche_layout, null);
        final PopupWindow popWindow = new PopupWindow(contentView,
                RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT,
                true);
        TextView renfindche = contentView.findViewById(R.id.renfindche);
        TextView chefindren = contentView.findViewById(R.id.chefindren);
        TextView tiantian = contentView.findViewById(R.id.tiantian);
        renfindche.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(PinCheActivity.this, PinCheFaBuActivity.class);
                startActivity(intent);

            }
        });
        chefindren.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
        tiantian.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

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
