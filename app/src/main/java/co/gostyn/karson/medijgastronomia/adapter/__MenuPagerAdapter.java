package co.gostyn.karson.medijgastronomia.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.gostyn.karson.medijgastronomia.LokaleInfoFragment;

/**
 * Created by Karol on 2017-06-06.
 */

public class __MenuPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public __MenuPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:

                LokaleInfoFragment tab1 = new LokaleInfoFragment();
                return tab1;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }


}
