package com.teklooncheah.selangorhaze.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tekloon on 11/13/15.
 */
public class SelangorHazePagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[];
    private Context context;
    private List<Fragment> fragmentList = new ArrayList<>();

    public SelangorHazePagerAdapter(FragmentManager fm, Context context, List<Fragment> list){
        super(fm);
        this.context = context;
        tabTitles = new String[] {"Latest", "Nearest", "Maps"};
        fragmentList.addAll(list);
    }

    @Override
    public Fragment getItem(int position) {
//        if(position == 1)
//            return LatestHazeInfoFragment.newInstance();
//        else
//            return HazeMapFragment.newInstance();
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
