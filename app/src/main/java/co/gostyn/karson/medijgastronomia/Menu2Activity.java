package co.gostyn.karson.medijgastronomia;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import co.gostyn.karson.medijgastronomia.adapter.MenuObjectAdapter;
import co.gostyn.karson.medijgastronomia.objects.Keys;
import co.gostyn.karson.medijgastronomia.objects.MenuObject;

import static junit.framework.Assert.assertEquals;



public class Menu2Activity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
   public ArrayList<MenuObject> list;



    public static final String URL_O = "http://83.144.104.86/medij/api_generujHTML.php?for=0&typ=o&day=0&ver=1";
    public static final String URL_S = "http://83.144.104.86/medij/api_generujHTML.php?for=0&typ=s&day=0&ver=1";
    public static final String TAG = "TAG_KARSON";
    private static Response response;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //new GetDataTask().execute();

        //MenuObject[] menuObiady = new MenuObject[];

        /*String jsonn;

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(URL_O)
                    .build();
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        // jsonn = response.body().string();
         //   Log.e(TAG, "KKK" + jsonn);


        new GetJSONfromUrl().execute(URL_O);























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
        getMenuInflater().inflate(R.menu.menu_menu2, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_menu2, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>" +
                    "<table><tr><td>1</td><td>2</td></tr><tr><td>3</td><td>4</td></table>"));

            WebView wb = (WebView) rootView.findViewById(R.id.web);

            String myTable = "<h2>Title</h2><br><p>Description here</p>" +
                    "<table><tr><td>1</td><td>2</td></tr><tr><td>3</td><td>4</td></table>";

            wb.loadDataWithBaseURL( null, myTable,  "text/html", "utf-8", null);


            //Log.e(TAG,list.get(0).getMenuHTML());








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



        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }



    private class GetJSONfromUrl extends AsyncTask<String, Void, String> {

        // okienko dialogowe, które każe użytkownikowi czekać
        private ProgressDialog dialog = new ProgressDialog(Menu2Activity.this);

        // metoda wykonywana jest zaraz przed główną operacją (doInBackground())
        // mamy w niej dostęp do elementów UI
        @Override
        protected void onPreExecute() {
            // wyświetlamy okienko dialogowe każące czekać
            dialog.setMessage("Czekaj...");
            dialog.show();

        }

        // główna operacja, która wykona się w osobnym wątku
        // nie ma w niej dostępu do elementów UI
        @Override
        protected String doInBackground(String... urls) {

            try {
                // zakładamy, że jest tylko jeden URL
                URL url = new URL(urls[0]);
                URLConnection connection = url.openConnection();

                // pobranie danych do InputStream
                InputStream in = new BufferedInputStream(
                        connection.getInputStream());

                // konwersja InputStream na String
                // wynik będzie przekazany do metody onPostExecute()
                return streamToString(in);

            } catch (Exception e) {
                // obsłuż wyjątek
                Log.d(MainActivity.class.getSimpleName(), e.toString());
                return null;
            }

        }

        // metoda wykonuje się po zakończeniu metody głównej,
        // której wynik będzie przekazany;
        // w tej metodzie mamy dostęp do UI
        @Override
        protected void onPostExecute(String result) {

            // chowamy okno dialogowe
            dialog.dismiss();
            Log.e(TAG,"aafa");
            try {
                // reprezentacja obiektu JSON w Javie
                JSONObject json = new JSONObject(result);

                JSONArray array = json.getJSONArray(Keys.KEY_MENU);

                int lenArray = array.length();
                if (lenArray > 0) {
                    for (int jIndex = 0; jIndex < lenArray; jIndex++) {

                        MenuObject model = new MenuObject();


                        JSONObject innerObject = array.getJSONObject(jIndex);
                        String day = innerObject.getString(Keys.KEY_DAY);
                        String data= innerObject.getString(Keys.KEY_DATA);
                        String dayName= innerObject.getString(Keys.KEY_DAY_NAME);
                        String lok1open = innerObject.getString(Keys.KEY_LOK1_OD);
                        String lok1close = innerObject.getString(Keys.KEY_LOK1_DO);
                        String lok2open = innerObject.getString(Keys.KEY_LOK2_OD);
                        String lok2close = innerObject.getString(Keys.KEY_LOK2_DO);
                        String menuHTML = innerObject.getString(Keys.KEY_MENU_HTML);


                        model.setDay(day);
                        model.setData(data);
                        model.setDayName(dayName);
                        model.setLok1open(lok1open);
                        model.setLok1close(lok1close);
                        model.setLok2open(lok2open);
                        model.setLok2close(lok2close);
                        model.setMenuHTML(menuHTML);
                        list.add(model);
                    }
                }
                Log.e(TAG,"aaa");



                // pobranie pól obiektu JSON i wyświetlenie ich na ekranie
            //    ((TextView) findViewById(R.id.response_id)).setText("id: "
             //           + json.optString("id"));
            //    ((TextView) findViewById(R.id.response_name)).setText("name: "
            //            + json.optString("name"));

            } catch (Exception e) {
                // obsłuż wyjątek
                Log.d(MainActivity.class.getSimpleName(), e.toString());
            }
        }
    }

    // konwersja z InputStream do String
    public static String streamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try {

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            reader.close();

        } catch (IOException e) {
            // obsłuż wyjątek
            Log.d(MainActivity.class.getSimpleName(), e.toString());
        }

        return stringBuilder.toString();
    }


}
