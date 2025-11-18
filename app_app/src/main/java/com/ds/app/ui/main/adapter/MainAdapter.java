package com.ds.app.ui.main.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ljiezhou
 * @date 2023/5/14
 * @Description
 */
public class MainAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList = new ArrayList();

    public MainAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }
    public void clear() {
        this.fragmentList.clear();
        notifyDataSetChanged();
    }

    public void addFragment(Fragment fragment) {
        this.fragmentList.add(fragment);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
