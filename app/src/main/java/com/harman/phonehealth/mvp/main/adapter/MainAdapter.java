package com.harman.phonehealth.mvp.main.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.harman.phonehealth.mvp.sevenday.view.SevenDayFragment;
import com.harman.phonehealth.mvp.today.view.TodayFragment;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mTitle = new ArrayList<>();
    public MainAdapter(FragmentManager fm) {
        super(fm);
        mFragmentList.add(TodayFragment.getInstance());
        mFragmentList.add(SevenDayFragment.getInstance());
        mTitle.add("今天");
        mTitle.add("过去七天");
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }
}
