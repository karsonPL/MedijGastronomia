package co.gostyn.karson.medijgastronomia;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;


public class MenuActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;


    public static final String TAG = "TAG_KARSON";
    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        app = (App) getApplication();

        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_toolbar);
        toolbar.setTitle(app.getMenuToolbarName());
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.menu_vp_container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.menu_tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //ustawienie tablayout na dniu tygodnia jaki wlasnie jest
        if (app.getWhatMenuType() == "s" && app.getIsDayOfWeek() > 6) {
            tabLayout.getTabAt(0).select();
        } else {
            tabLayout.getTabAt(app.getIsDayOfWeek() - 2).select();
        }





      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        //ustawienie nazwy itema w menu w toolbar
        MenuItem item = menu.findItem(R.id.change_menu_type);
        item.setTitle(app.getOtherMenuPressOnToolbar());

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.change_menu_type) { // zmiana z obiadu na sniadanie i odswiezenie activyty

            if (app.getWhatMenuType() == "s") {
                app.setWhatMenuType("o");
                app.setMenuToolbarName(R.string.title_activity_menu_obiad);
                app.setOtherMenuPressOnToolbar(R.string.txt_other_menu_type_sniadanie);

            } else {
                app.setWhatMenuType("s");
                app.setMenuToolbarName(R.string.title_activity_menu_sniadanie);
                app.setOtherMenuPressOnToolbar(R.string.txt_other_menu_type_obiad);
            }

            startActivity(getIntent());
            this.finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
            TextView tvMenuData = (TextView) rootView.findViewById(R.id.menu_data);
            WebView wbMenu = (WebView) rootView.findViewById(R.id.menu);
            wbMenu.getSettings().setJavaScriptEnabled(true);
            App app = (App) getActivity().getApplication();

            Bundle args = getArguments();
            int currentView = args.getInt(ARG_SECTION_NUMBER) - 1;


            if (app.getWhatMenuType() == "o") {
                String html = app.getArrayMenuO().get(currentView).getMenuHTML();
                tvMenuData.setText(app.getArrayMenuO().get(currentView).getData());
                wbMenu.loadDataWithBaseURL(App.getURL(), html, "text/html", "utf-8", null);
            } else {
                String html = app.getArrayMenuS().get(currentView).getMenuHTML();
                tvMenuData.setText(app.getArrayMenuS().get(currentView).getData());
                wbMenu.loadDataWithBaseURL(App.getURL(), html, "text/html", "utf-8", null);
            }
            return rootView;
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        App app = (App) getApplication();

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            if (app.getWhatMenuType() == "o") {
                return app.getArrayMenuO().size();
            } else {
                return app.getArrayMenuS().size();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (app.getWhatMenuType() == "o") {
                return app.getArrayMenuO().get(position).getDayName();
            } else {
                return app.getArrayMenuS().get(position).getDayName();
            }


        }
    }


}
