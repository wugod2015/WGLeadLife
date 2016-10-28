package com.jackhan.wgleadlife.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */

public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;

    public BaseFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    private BaseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
