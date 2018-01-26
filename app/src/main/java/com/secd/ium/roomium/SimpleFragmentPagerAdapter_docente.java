package com.secd.ium.roomium;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by fedus on 19/01/2018.
 */

public class SimpleFragmentPagerAdapter_docente extends FragmentPagerAdapter {

    private Context mContext;

    public SimpleFragmentPagerAdapter_docente(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new DocenteFragment();
        } else if (position == 1){
            return new PrenotaFragment();
        } else if (position == 2){
            return new AvvisiDocenteFragment();
        } else if (position==3){
            return new SegnalaFragment();
        } else return new FeedbackFragment();

    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 5;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return "Docente";
            case 1:
                return "Prenota";
            case 2:
                return "Avvisi";
            case 3:
                return "Segnala";
            case 4:
                return "Feedback";
            default:
                return null;
        }
    }

}