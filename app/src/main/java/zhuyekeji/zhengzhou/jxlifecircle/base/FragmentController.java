package zhuyekeji.zhengzhou.jxlifecircle.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

import zhuyekeji.zhengzhou.jxlifecircle.frament.FriendFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.HomeFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.MeFrament;
import zhuyekeji.zhengzhou.jxlifecircle.frament.OrderFrament;


/**
 * Created by hpc on 2016/12/21.
 */

public class FragmentController
{
    private int containerId;
    private FragmentManager fm;
    private ArrayList<Fragment> fragments;

    private static FragmentController controller;
    private Bundle bundle;
    public static FragmentController getInstance(FragmentActivity activity, int containerId) {
//        if (controller == null) {
        controller = new FragmentController(activity, containerId);
//        }
        return controller;
    }

    public static void onDestroy() {
        controller = null;
    }

    public FragmentController(FragmentActivity activity, int containerId) {
        this.containerId = containerId;
        fm = activity.getSupportFragmentManager();
        initFragment();
    }

    public void initFragment() {
        if (fragments==null)
        {
            fragments = new ArrayList<Fragment>();
        }
        fragments.add(new HomeFrament());
        fragments.add(new FriendFrament());
        fragments.add(new OrderFrament());
        fragments.add(new MeFrament());
        FragmentTransaction ft = fm.beginTransaction();
        for (Fragment fragment : fragments) {
            ft.add(containerId, fragment);
        }
        ft.commitAllowingStateLoss();
    }

    public void showFragment(int position) {
        hideFragments();
        Fragment fragment = fragments.get(position);
        FragmentTransaction ft = fm.beginTransaction();
        ft.show(fragment);
        ft.commitAllowingStateLoss();
    }

    public void hideFragments() {
        FragmentTransaction ft = fm.beginTransaction();
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                ft.hide(fragment);
            }
        }
        ft.commitAllowingStateLoss();
    }

    public Fragment getFragment(int position) {
        return fragments.get(position);
    }
}
