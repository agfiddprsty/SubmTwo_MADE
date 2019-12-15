package com.fiid.moviescataloguesub2;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.fiid.moviescataloguesub2.Fragment.MoviesFragment;
import com.fiid.moviescataloguesub2.Fragment.TVShowFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        mContext = context;
    }

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.tab_1,
            R.string.tab_2
    };

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = MoviesFragment.newInstance(position + 1);
        switch (position){
            case 0:
                fragment = new MoviesFragment();
                break;

            case 1:
                fragment = new TVShowFragment();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
