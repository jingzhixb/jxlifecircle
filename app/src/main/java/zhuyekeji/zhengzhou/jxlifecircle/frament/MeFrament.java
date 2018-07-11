package zhuyekeji.zhengzhou.jxlifecircle.frament;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.activity.DaiPingJiaActivity;
import zhuyekeji.zhengzhou.jxlifecircle.activity.JiFenActivity;
import zhuyekeji.zhengzhou.jxlifecircle.activity.LiShiZuJiActivity;
import zhuyekeji.zhengzhou.jxlifecircle.activity.MyAddressActivity;
import zhuyekeji.zhengzhou.jxlifecircle.activity.MyConllentActivity;
import zhuyekeji.zhengzhou.jxlifecircle.activity.MyFaBuActivity;
import zhuyekeji.zhengzhou.jxlifecircle.activity.MyFenSiActivity;
import zhuyekeji.zhengzhou.jxlifecircle.activity.MyGuanZhuActivity;
import zhuyekeji.zhengzhou.jxlifecircle.activity.MyWalletActivity;
import zhuyekeji.zhengzhou.jxlifecircle.activity.SettingActivity2;
import zhuyekeji.zhengzhou.jxlifecircle.activity.ShopCooperation;
import zhuyekeji.zhengzhou.jxlifecircle.activity.ShopCoreActivity;
import zhuyekeji.zhengzhou.jxlifecircle.activity.TuiGUangActivity;
import zhuyekeji.zhengzhou.jxlifecircle.activity.UserInfoActivity;
import zhuyekeji.zhengzhou.jxlifecircle.activity.YouHuiActivity;
import zhuyekeji.zhengzhou.jxlifecircle.adapter.MeAdapter;
import zhuyekeji.zhengzhou.jxlifecircle.api.CallBack;
import zhuyekeji.zhengzhou.jxlifecircle.api.FragmentBackHandler;
import zhuyekeji.zhengzhou.jxlifecircle.api.JxApiCallBack;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseFragment;
import zhuyekeji.zhengzhou.jxlifecircle.bean.MeBean;
import zhuyekeji.zhengzhou.jxlifecircle.utils.JsonUtile;
import zhuyekeji.zhengzhou.jxlifecircle.utils.other.UIThread;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ActivityUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;
import zhuyekeji.zhengzhou.jxlifecircle.widget.CircleImageView;

/**
 * Created by Administrator on 2018/6/6.
 */

public class MeFrament extends BaseFragment implements FragmentBackHandler
{
    @BindView(R.id.im_user)
    CircleImageView imUser;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.ll_message)
    LinearLayout llMessage;
    @BindView(R.id.tv_jifen)
    TextView tvJifen;
    @BindView(R.id.ll_jifen)
    LinearLayout llJifen;
    @BindView(R.id.tv_guanzhu)
    TextView tvGuanzhu;
    @BindView(R.id.ll_guanzhu)
    LinearLayout llGuanzhu;
    @BindView(R.id.tv_fensi)
    TextView tvFensi;
    @BindView(R.id.ll_fensi)
    LinearLayout llFensi;
    @BindView(R.id.rv_me)
    RecyclerView rvMe;
    @BindView(R.id.sign)
    TextView sign;
    private View view;
    Unbinder unbinder;

    private List<MeBean> beans = new ArrayList<>();
    private boolean isDoubleClick = false;
    private String[] textuser = new String[]{"我的收藏", "我的发布", "待评价", "历史足迹",
            "我的优惠卷", "我的钱包", "我的地址", "推广有奖", "设置", "平台客服", "生活服务", "最新在线电影", "商家中心", "商家合作"};
    private int[] iconuser = new int[]{R.mipmap.shoucang, R.mipmap.fabu, R.mipmap.pingjia, R.mipmap.zuji, R.mipmap.youhuiquan, R.mipmap.qianbao, R.mipmap.dizhi, R.mipmap.tuiguang,
            R.mipmap.shezhi, R.mipmap.kefu, R.mipmap.shenghuofuwu, R.mipmap.dianying, R.mipmap.ic_launcher, R.mipmap.ic_launcher};


    private String[] textshangjia = new String[]{"我的收藏", "我的论坛",
            "优惠卷", "我的商品", "我的钱包", "我的地址", "设置", "应用推广", "平台客服"};


    private int[] iconshangjia = new int[]{R.mipmap.shoucang, R.mipmap.luntan, R.mipmap.youhuiquan, R.mipmap.shangpin, R.mipmap.qianbao, R.mipmap.dizhi, R.mipmap.shezhi, R.mipmap.tuiguang, R.mipmap.kefu};

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container)
    {
        view = inflater.inflate(R.layout.me_frament, container, false);
        return view;
    }

    @Override
    protected void initListener()
    {

    }

    @Override
    protected void initData()
    {
        for (int i = 0; i < textuser.length; i++)
        {
            MeBean bean = new MeBean();
            bean.setText(textuser[i]);
            bean.setImage(iconuser[i]);
            beans.add(bean);
        }
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        rvMe.setLayoutManager(manager);
        final MeAdapter adapter = new MeAdapter(getActivity(), R.layout.me_item, beans);
        rvMe.setAdapter(adapter);
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener()
        {
            @Override
            public void onItemClick(View view, int i)
            {
                switch (adapter.getItem(i).getText())
                {
                    case "我的收藏":
                        Intent intent = new Intent(getActivity(), MyConllentActivity.class);
                        startActivity(intent);
                        break;
                    case "我的发布":
                        Intent intent1 = new Intent(getActivity(), MyFaBuActivity.class);
                        startActivity(intent1);
                        break;
                    case "待评价":
                        Intent intent9 = new Intent(getActivity(), DaiPingJiaActivity.class);
                        startActivity(intent9);
                        break;
                    case "历史足迹":
                        Intent intent7 = new Intent(getActivity(), LiShiZuJiActivity.class);
                        startActivity(intent7);
                        break;
                    case "我的优惠卷":
                        Intent intent6 = new Intent(getActivity(), YouHuiActivity.class);
                        startActivity(intent6);

                        break;
                    case "优惠卷":
                        Intent intent8 = new Intent(getActivity(), YouHuiActivity.class);
                        startActivity(intent8);
                        break;
                    case "我的钱包":
                        Intent intent4 = new Intent(getActivity(), MyWalletActivity.class);
                        startActivity(intent4);
                        break;
                    case "我的地址":
                        Intent intent3 = new Intent(getActivity(), MyAddressActivity.class);
                        startActivity(intent3);
                        break;
                    case "推广有奖":
                        Intent intent5 = new Intent(getActivity(), TuiGUangActivity.class);
                        startActivity(intent5);
                        break;
                    case "设置":
                        Intent intent2 = new Intent(getActivity(), SettingActivity2.class);
                        startActivity(intent2);
                        break;
                    case "平台客服":

                        break;
                    case "生活服务":

                        break;
                    case "最新在线电影":

                        break;

                    case "我的商品":

                        break;
                    case "我的论坛":

                        break;
                    case "商家合作":
                        Intent intent10 = new Intent(getActivity(), ShopCooperation.class);
                        startActivity(intent10);
                        break;
                    case "商家中心":
                        Intent intent11 = new Intent(getActivity(), ShopCoreActivity.class);
                        startActivity(intent11);
                        break;

                }
            }
        });
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

    @OnClick({R.id.im_user, R.id.ll_message, R.id.ll_jifen, R.id.ll_guanzhu, R.id.ll_fensi})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.im_user:
                Intent intent1 = new Intent(getActivity(), UserInfoActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_message:
                break;
            case R.id.ll_jifen://积分
                Intent intent = new Intent(getActivity(), JiFenActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_guanzhu:
                Intent intent2 = new Intent(getActivity(), MyGuanZhuActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll_fensi:
                Intent intent3 = new Intent(getActivity(), MyFenSiActivity.class);
                startActivity(intent3);
                break;
            case R.id.sign:
                JxApiCallBack.sign(getToken(),1,getActivity(),callBack);
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
              case 1://签到
                  if (JsonUtile.getCode(result.body()).equals("200"))
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
