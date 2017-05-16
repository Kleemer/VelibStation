package com.example.yassine.recyclerviewtests.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yassine.recyclerviewtests.fragment.DetailFragment;
import com.example.yassine.recyclerviewtests.json.VelibStation;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ArrayList<VelibStation> mDataset = new ArrayList<>();
    public int firstPos;
    public ViewPagerAdapter(FragmentManager fm, ArrayList<VelibStation> mDataset, int firstPos) {
        super(fm);
        this.mDataset = mDataset;
        this.firstPos = firstPos;
    }

    @Override
    public Fragment getItem(int position) {
        return DetailFragment.newInstance(mDataset.get(position).field.name);
    }
    @Override
    public int getCount() {
        return mDataset.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return (mDataset.get(position).field.name);
    }
}