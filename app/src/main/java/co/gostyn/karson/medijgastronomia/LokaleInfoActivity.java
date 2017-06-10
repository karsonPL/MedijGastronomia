package co.gostyn.karson.medijgastronomia;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import co.gostyn.karson.medijgastronomia.utils.InternetConnection;


public class LokaleInfoActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokale_info);


        Toolbar toolbar = (Toolbar) findViewById(R.id.lokale_info_toolbar);
        setSupportActionBar(toolbar);

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.lokale_info_viewPager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.lokale_info_tab);
        tabLayout.setupWithViewPager(viewPager);





        //Log.d(LokaleInfoActivity.class.getSimpleName(),"ABC list: "+ abc);












    }

    /**
     * Creating Get Data Task for Getting Data From Web
     */



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LokaleInfoFragment(), "Lokal Gastronomiczny KAPI");
        adapter.addFragment(new LokaleInfoFragment(), "Sala bankietowa MEDIJ");
        viewPager.setAdapter(adapter);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }




}


