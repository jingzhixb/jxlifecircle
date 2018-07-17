package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.model.Response;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.TypeAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.bean.TypeShopBean;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

public class TypeActivity extends BaseActivity
{
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.rv_shop)
    SwipeMenuRecyclerView rvShop;
    private String mShopid;
    private TypeAdapter adapter;
    private List<TypeShopBean> beans=new ArrayList<>();
   private  int position;
    private String content;
    private PopupWindow popWindow;

    //套餐
    @Override
    public int getViewId()
    {
        return R.layout.activity_type;
    }

    @Override
    protected void processLogic()
    {

        beans = new ArrayList<>();
        tvTitle.setText("分类管理");
        titleRight.setText("添加");
        mShopid=getIntent().getStringExtra("shopid");
        rvShop.setSwipeMenuCreator(mSwipeMenuCreator);
        rvShop.setSwipeMenuItemClickListener(mMenuItemClickListener);
        rvShop.setLayoutManager(new LinearLayoutManager(TypeActivity.this));
        rvShop.setItemAnimator(new DefaultItemAnimator());
        adapter = new TypeAdapter(this, R.layout.item_type_shop, beans);
        rvShop.setAdapter(adapter);
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener()
        {
            @Override
            public void onItemClick(View view, int i)
            {
                Intent intent=new Intent();
                intent.putExtra("id",adapter.getItem(i).getClass_id());
                intent.putExtra("name",adapter.getItem(i).getClass_name());
                setResult(2,intent);
                finish();
            }
        });
        JxApiCallBack.shop_list(getToken(),Integer.parseInt(mShopid),2,this,callBack);
    }


    // 创建菜单：
    SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator()
    {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType)
        {
            SwipeMenuItem deleteItem = new SwipeMenuItem(TypeActivity.this); // 各种文字和图标属性设置。
            deleteItem.setText("删除");
            deleteItem.setBackground(R.color.red);
            deleteItem.setWidth(250);
            deleteItem.setHeight(135);
            rightMenu.addMenuItem(deleteItem); // 在Item左侧添加一个菜单。
        }
    };

    SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
            menuBridge.closeMenu();
            position = menuBridge.getPosition();
            ToastUtils.showShort("点击了侧滑菜单");
            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
            int classid=Integer.parseInt(adapter.getItem(position).getClass_id());
            JxApiCallBack.del_type(getToken(),classid,1,TypeActivity.this,callBack);
        }
    };
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

    @OnClick({R.id.rl_back, R.id.title_right})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            case R.id.title_right:
                showPopup0(view);
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
               if (JsonUtile.isTrue(result.body()))
               {
                   ToastUtils.showShort(JsonUtile.getresulter(result.body()));
                   try
                   {
                       JSONObject object=new JSONObject(JsonUtile.getbody(result.body()));
                       String id=object.getString("id");
                       TypeShopBean bean=new TypeShopBean();
                       bean.setClass_id(id);
                       bean.setClass_name(content);
                       beans.add(bean);
                       adapter.notifyDataSetChanged();
                       popWindow.dismiss();
                   } catch (JSONException e)
                   {
                       e.printStackTrace();
                   }

               }else {
                   ToastUtils.showShort(JsonUtile.getresulter(result.body()));
               }
               break;
           case 2:
               if (JsonUtile.isTrue(result.body()))
               {
                   List list= Arrays.asList(new Gson().fromJson(JsonUtile.getbody(result.body()),TypeShopBean[].class));
                   beans.addAll(list);
                   adapter.notifyDataSetChanged();
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
    private void showPopup0(View parent)//房屋信息
    {
        RelativeLayout layout = (RelativeLayout) LayoutInflater.from(TypeActivity.this).inflate(R.layout.layout_type_shop, null);
        popWindow = new PopupWindow(layout,
                RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT,
                true);
        final EditText ed_address = layout.findViewById(R.id.ed_address);
        TextView cancle = layout.findViewById(R.id.cancle);
        TextView ok = layout.findViewById(R.id.ok);
        cancle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                popWindow.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                content = ed_address.getText().toString().trim();
                if (content ==null|| content.length()==0)
                {
                    ToastUtils.showShort("请输入分类");
                    return;
                }
                JxApiCallBack.add_type(getToken(), content, Integer.parseInt(mShopid), 1, TypeActivity.this, callBack);
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
