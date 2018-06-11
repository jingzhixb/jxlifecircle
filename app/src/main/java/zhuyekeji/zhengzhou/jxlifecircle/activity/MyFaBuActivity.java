package zhuyekeji.zhengzhou.jxlifecircle.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseActivity;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

public class MyFaBuActivity extends BaseActivity
{

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_fabu)
    SwipeMenuRecyclerView rvFabu;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;

    @Override
    public int getViewId()
    {
        return R.layout.activity_my_fa_bu;
    }

    @Override
    protected void processLogic()
    {

    }


    // 创建菜单：
    SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
            SwipeMenuItem deleteItem = new SwipeMenuItem(MyFaBuActivity.this); // 各种文字和图标属性设置。
            deleteItem.setText("删除");
            deleteItem.setBackground(R.color.red);
            deleteItem.setWidth(250);
            deleteItem.setHeight(300);
            rightMenu.addMenuItem(deleteItem); // 在Item左侧添加一个菜单。

//            SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity()); // 各种文字和图标属性设置。
//            leftMenu.addMenuItem(deleteItem); // 在Item右侧添加一个菜单。

            // 注意：哪边不想要菜单，那么不要添加即可。
        }
    };
    SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
            menuBridge.closeMenu();
            ToastUtils.showShort("点击了侧滑菜单");
            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
        }
    };

    @Override
    protected void setListener()
    {

    }

    @Override
    protected Context getActivityContext()
    {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
