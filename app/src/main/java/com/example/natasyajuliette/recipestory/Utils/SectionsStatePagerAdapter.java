package com.example.natasyajuliette.recipestory.Utils;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by natasyajuliette on 01/03/18.
 */

public class SectionsStatePagerAdapter extends FragmentStatePagerAdapter {

    public final List<Fragment> mFragmentList = new ArrayList<>();
    public final HashMap<Fragment, Integer> mFragments = new HashMap<>();
    public final HashMap<String, Integer> mFragmentNumbers = new HashMap<>();
    public final HashMap<Integer, String> mFragmentNames = new HashMap<>();

    public SectionsStatePagerAdapter (FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String fragmentName) {
        mFragmentList.add(fragment);
        mFragments.put(fragment, mFragmentList.size()-1);
        mFragmentNumbers.put(fragmentName, mFragmentList.size()-1);
        mFragmentNames.put(mFragmentList.size()-1, fragmentName);
    }

    public Integer getFragmentNumber(String fragmentName) {
        if (mFragmentNumbers.containsKey(fragmentName)) {
            return mFragmentNumbers.get(fragmentName);
        } else {
            return null;
        }
    }

    public Integer getFragmentNumber(Fragment fragment) {
        if (mFragments.containsKey(fragment)) {
            return mFragments.get(fragment);
        } else {
            return null;
        }
    }

    public String getFragmentNames(Integer fragmentNumber) {
        if (mFragmentNames.containsKey(fragmentNumber)) {
            return mFragmentNames.get(fragmentNumber);
        } else {
            return null;
        }
    }

}
