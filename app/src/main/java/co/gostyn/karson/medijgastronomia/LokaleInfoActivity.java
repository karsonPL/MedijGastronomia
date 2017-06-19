package co.gostyn.karson.medijgastronomia;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class LokaleInfoActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private App app = (App) getApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokale_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
        // getMenuInflater().inflate(R.menu.menu_lokale_info, menu); //OFF
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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
        App app;

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
            View rootView = inflater.inflate(R.layout.fragment_lokale_info, container, false);

            TextView tvIsOpen = (TextView) rootView.findViewById(R.id.is_open_close);
            TextView tvWelcome = (TextView) rootView.findViewById(R.id.open_close_how_long);
            TextView tvAddress = (TextView) rootView.findViewById(R.id.address);
            ImageView ivImg = (ImageView) rootView.findViewById(R.id.open_close_img);
            WebView wbOpenCloseHTML = (WebView) rootView.findViewById(R.id.open_close_html);
            wbOpenCloseHTML.getSettings().setJavaScriptEnabled(true);

            Bundle args = getArguments();
            int currentView = args.getInt(ARG_SECTION_NUMBER) - 1;

            app = (App) getActivity().getApplication();

            if (currentView == 0) {
                tvAddress.setText(getString(R.string.adr_lok1)); //wstawia adres lokalu
                if (isOpen(0) != 0) {
                    tvIsOpen.setText(R.string.jest_czynne); //czynne
                    if (isOpen(0) == 1) {
                        tvWelcome.setText(app.getString(R.string.welcomeS) + " "
                                + app.getArrayMenuS().get(app.getIsDayOfWeek()).getLok1close());
                    } else {
                        tvWelcome.setText(app.getString(R.string.welcomeO) + " "
                                + app.getArrayMenuO().get(app.getIsDayOfWeek()).getLok1close());
                    }
                } else {
                    tvIsOpen.setTextColor(Color.RED);
                    tvIsOpen.setText(R.string.jest_nieczynne); //nieczynne
                    tvWelcome.setVisibility(View.GONE);
                    ivImg.setImageResource(R.mipmap.ic_round_red);
                }
                wbOpenCloseHTML.loadDataWithBaseURL(App.getURL(), openCloseHTML(0), "text/html", "utf-8", null);

            } else {
                tvAddress.setText(getString(R.string.adr_lok2));
                if (isOpen(1) != 0) {
                    tvIsOpen.setText(R.string.jest_czynne); //czynne
                    tvWelcome.setText(app.getString(R.string.welcomeO) + " "
                            + app.getArrayMenuO().get(app.getIsDayOfWeek()).getLok2close());
                } else {
                    tvIsOpen.setTextColor(Color.RED);
                    tvIsOpen.setText(R.string.jest_nieczynne);  //nieczynne
                    tvWelcome.setVisibility(View.GONE);
                    ivImg.setImageResource(R.mipmap.ic_round_red);
                }
                wbOpenCloseHTML.loadDataWithBaseURL(App.getURL(), openCloseHTML(1), "text/html", "utf-8", null);
            }


            return rootView;
        }

        private int isOpen(int lokal) { //zwraca: 0 - zamkniete; 1 - otwarte sniadanie; 2 - otwarte obiad

            Date currentDate = new Date();
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
            timeFormat.setTimeZone(TimeZone.getTimeZone("Poland"));
            Integer time = Integer.parseInt(timeFormat.format(currentDate)); //aktualna godzina jako Int - HHmm


            //ustalenie pozycji tablicy z ktorej ma brac dane w zaleznosci od dnia tygodnia
            int arrayPoz = app.getIsDayOfWeek();

            int timeOpen, timeClose;

            if (lokal == 0) {
                if (arrayPoz < 5) {// sniadania sprawdza tylko do piatku
                    timeOpen = Integer.parseInt((app.getArrayMenuS().get(arrayPoz).getLok1open()).replace(":", ""));
                    timeClose = Integer.parseInt((app.getArrayMenuO().get(arrayPoz).getLok1open()).replace(":", ""));
                    if (timeOpen != 0 && timeClose != 0 && timeOpen < time && timeClose > time) {
                        return 1;
                    }
                }
                timeOpen = Integer.parseInt((app.getArrayMenuO().get(arrayPoz).getLok1open()).replace(":", ""));
                timeClose = Integer.parseInt((app.getArrayMenuO().get(arrayPoz).getLok1close()).replace(":", ""));
                if (timeOpen != 0 && timeClose != 0 && timeOpen < time && timeClose > time) {
                    return 2;
                }
            } else {
                timeOpen = Integer.parseInt((app.getArrayMenuO().get(arrayPoz).getLok2open()).replace(":", ""));
                timeClose = Integer.parseInt((app.getArrayMenuO().get(arrayPoz).getLok2close()).replace(":", ""));
                if (timeOpen != 0 && timeClose != 0 && timeOpen < time && timeClose > time) {
                    return 2;
                }
            }
            //Log.e("TAG_KARSON", "aa  bbb ");
            return 0;
        }

        private String openCloseHTML(int lokal) {
            String html = "";

            html = "<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"style-api.css\">" +
                    "<title></title></head><body bgcolor=\"#f0feda\">" +
                    "<table align=\"center\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">";
            if (lokal == 0) {

                html += "<tr><td></td><td align=\"center\"><font class=\"tekst-m\">ŚNIADANIE<HR></td>" +
                        "<td  align=\"center\"><font class=\"tekst-m\">OBIAD<HR></td></tr>";

                for (int i = 0; i < 7; i++) {
                    html += "<tr>" +
                            "<td><font class=\"tekst-m\">" + app.getArrayMenuO().get(i).getDayName() + "&nbsp;&nbsp;<HR></td>";
                    if (i < 5) {
                        html += "<td align=\"center\"><font class=\"tekst-m\">&nbsp;&nbsp;" +
                                isNieczynne(app.getArrayMenuS().get(i).getLok1open(), app.getArrayMenuS().get(i).getLok1close()) +
                                "&nbsp;&nbsp;<HR></td>";
                    } else {
                        html += "<td align=\"center\"><font class=\"tekst-m\">&nbsp;&nbsp;nieczynne&nbsp;&nbsp;<HR></td>";
                    }
                    html += "<td align=\"center\"><font class=\"tekst-m\">&nbsp;&nbsp;" +
                            isNieczynne(app.getArrayMenuO().get(i).getLok1open(), app.getArrayMenuO().get(i).getLok1close()) +
                            "&nbsp;&nbsp;<HR></td></tr>";
                }
            } else {
                html += "<tr><td></td><td  align=\"center\"><font class=\"tekst-m\">OBIAD<HR></td></tr>";

                for (int i = 0; i < 7; i++) {
                    html += "<tr>" +
                            "<td><font class=\"tekst-m\">" + app.getArrayMenuO().get(i).getDayName() + "&nbsp;&nbsp;<HR></td>";

                    html += "<td align=\"center\"><font class=\"tekst-m\">&nbsp;&nbsp;" +
                            isNieczynne(app.getArrayMenuO().get(i).getLok2open(), app.getArrayMenuO().get(i).getLok2close()) +
                            "&nbsp;&nbsp;<HR></td></tr>";

                }
            }
            html += "</body></html>";
            return html;
        }

        private String isNieczynne(String open, String close) {

            if (Integer.parseInt(open.replace(":", "")) == 0) {
                return "nieczynne";
            } else {
                return open + " - " + close;
            }
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

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Lokal Gastronomiczny\n KAPI";
                case 1:
                    return "Sala bankietowa\n MEDIJ";

            }
            return null;
        }
    }

}
