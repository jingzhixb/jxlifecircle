package zhuyekeji.zhengzhou.jxlifecircle.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import zhuyekeji.zhengzhou.jxlifecircle.R;
import zhuyekeji.zhengzhou.jxlifecircle.api.FragmentBackHandler;
import zhuyekeji.zhengzhou.jxlifecircle.base.BaseFragment;
import zhuyekeji.zhengzhou.jxlifecircle.utils.other.UIThread;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ActivityUtils;
import zhuyekeji.zhengzhou.jxlifecircle.utils.util.ToastUtils;

/**
 * Created by Administrator on 2018/6/6.
 */

    public class HomeFrament extends BaseFragment implements FragmentBackHandler
    {
        private View view;
        Unbinder unbinder;
        private boolean isDoubleClick = false;
        @Override
        protected View initView(LayoutInflater inflater, ViewGroup container)
        {
            view = inflater.inflate(R.layout.home_frament, container, false);
            return view;
        }

        @Override
        protected void initListener()
        {

        }

        @Override
        protected void initData()
        {

        }

        @Override
        public boolean onBackPressed()
        {
            if (isDoubleClick)
            {
                ActivityUtils.finishAllActivities();System.exit(0);
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
    }
