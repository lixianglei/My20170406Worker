package com.example.d.my20170406worker.bean;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by d on 2017/4/6.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<String> mListName;
    private ArrayList<Fragment> mList;

    public FragmentAdapter(FragmentManager fm, ArrayList<Fragment> mList, ArrayList<String> mListName) {
        super(fm);
        this.mListName = mListName;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mListName.isEmpty() ? 0 : mListName.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mListName.get(position);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return null;
    }
}
