package com.sv.test_task.feature.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.sv.test_task.R;
import com.sv.test_task.feature.ProductsFragment;
import com.sv.test_task.feature.model.ProductCodeCountry;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class GoodsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_all, R.string.tab_by,
            R.string.tab_russia };
    private final Context mContext;

    public GoodsPagerAdapter(Context context, FragmentManager fm) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            default:
                return ProductsFragment.newInstance(ProductCodeCountry.ALL);
            case 1:
                return ProductsFragment.newInstance(ProductCodeCountry.BY);
            case 2:
                return ProductsFragment.newInstance(ProductCodeCountry.RU);
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }
}