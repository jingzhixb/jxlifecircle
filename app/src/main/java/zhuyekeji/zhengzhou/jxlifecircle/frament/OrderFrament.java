package zhuyekeji.zhengzhou.jxlifecircle.frament;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.api.FragmentBackHandler;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseFragment;
import zhuyekeji.zhengzhou.jxlifecircle.orderactivity.ShopOrderActivity;
import zhuyekeji.zhengzhou.jxlifecircle.orderactivity.ShopOrderShangjiaActivity;
import zhuyekeji.zhengzhou.jxlifecircle.utils.other.UIThread;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ActivityUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

/**
 * Created by Administrator on 2018/6/6.
 */

public class OrderFrament extends BaseFragment implements FragmentBackHandler
{
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.message)
    TextView message;
    @BindView(R.id.rl_tuangou)
    RelativeLayout rlTuangou;
    @BindView(R.id.message_tuangou)
    TextView messageTuangou;
    @BindView(R.id.rl_jifen)
    RelativeLayout rlJifen;
    @BindView(R.id.message_pinche)
    TextView messagePinche;
    @BindView(R.id.rl_pinche)
    RelativeLayout rlPinche;
    private View view;
    Unbinder unbinder;
    private boolean isDoubleClick = false;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container)
    {
        view = inflater.inflate(R.layout.order_frament, container, false);
        return view;
    }

    @Override
    protected void initListener()
    {

    }

    @Override
    protected void initData()
    {
tvTitle.setText("订单");
rlBack.setVisibility(View.GONE);
    }

    @Override
    public boolean onBackPressed()
    {
        if (isDoubleClick)
        {
            ActivityUtils.finishAllActivities();
            System.exit(0);
        } else
        {
            ToastUtils.showShort("再次点击一次退出程序");
            isDoubleClick = true;
            UIThread.getInstance().postDelay(new Runnable()
            {
                @Override
                public void run()
                {
                    isDoubleClick = false;
                }
            }, 1000);
        }
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_back, R.id.rl_tuangou, R.id.rl_jifen, R.id.rl_pinche})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_back:

                break;
            case R.id.rl_tuangou://商城
                int i=0;
               if (i%2==0)
               {
                   Intent intent=new Intent(getActivity(),ShopOrderActivity.class);
                   startActivity(intent);
               }else {
                   Intent intent=new Intent(getActivity(),ShopOrderShangjiaActivity.class);
                   startActivity(intent);
               }
               i++;
                break;
            case R.id.rl_jifen://积分
                break;
            case R.id.rl_pinche://拼车
                break;
        }
    }
}
