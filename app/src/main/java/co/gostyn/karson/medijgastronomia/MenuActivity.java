package co.gostyn.karson.medijgastronomia;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.gostyn.karson.medijgastronomia.adapter.MenuObjectAdapter;
import co.gostyn.karson.medijgastronomia.objects.Keys;
import co.gostyn.karson.medijgastronomia.objects.MenuObject;
import co.gostyn.karson.medijgastronomia.parser.JSONParser;
import co.gostyn.karson.medijgastronomia.utils.InternetConnection;


public class MenuActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<MenuObject> list;
    private String data;
    private MenuObjectAdapter adapter2;
    public MenuObjectAdapter adapter1;

    private TabLayout tabLayout;
    private ViewPager viewPager;
private MenuObject abc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);



        abc = ((App)getApplication()).getMenuObjectMon();

        Log.d(MenuActivity.class.getSimpleName(),"ABC list: "+ abc);



        /**
         * Array List for Binding Data from JSON to this List
         */
        list = new ArrayList<>();
        /**
         * Binding that List to Adapter
         */
        adapter2 = new MenuObjectAdapter(this, list);

        /**
         * Getting List and Setting List Adapter
         */
       // listView = (ListView) findViewById(R.id.listView);
      // listView.setAdapter(adapter2);
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Snackbar.make(findViewById(R.id.parentLayout), list.get(position).getNazwa() + " => " + list.get(position).getOpis1(), Snackbar.LENGTH_LONG).show();
            }
        });*/

        //jezeli jest polaczenie to pobiera JSona i tworzy liste pozycji menu
        if (InternetConnection.checkConnection(getApplicationContext())) {
           // new GetDataTask().execute();
        } else {
            Snackbar.make(findViewById(R.id.parentLayout), "Internet Connection Not Available", Snackbar.LENGTH_LONG).show();
        }


        /**
         * Just to know onClick and Printing Hello Toast in Center.

         Toast toast = Toast.makeText(getApplicationContext(), "Click on FloatingActionButton to Load JSON", Toast.LENGTH_LONG);
         toast.setGravity(Gravity.CENTER, 0, 0);
         toast.show();

         FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
         fab.setOnClickListener(new View.OnClickListener()

         {
         @Override public void onClick(@NonNull View view) {

         ///Checking Internet Connection

         if (InternetConnection.checkConnection(getApplicationContext())) {
         new GetDataTask().execute();
         } else {
         Snackbar.make(view, "Internet Connection Not Available", Snackbar.LENGTH_LONG).show();
         }
         }
         });*/
    }

    /**
     * Creating Get Data Task for Getting Data From Web
     */



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MenuFragment(), "ONE");
        adapter.addFragment(new MenuFragment(), "TWO");
        adapter.addFragment(new MenuFragment(), "THREE");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
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

    class GetDataTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(MenuActivity.this);
            dialog.setTitle("Proszę czekać...");
            dialog.setMessage("Pobieram Menu");
            dialog.show();
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {

            /**
             * Getting JSON Object from Web Using okHttp
             */
            JSONObject jsonObject = JSONParser.getDataFromWeb();

            try {
                /**
                 * Check Whether Its NULL???
                 */
                if (jsonObject != null) {
                    /**
                     * Check Length...
                     */
                    if (jsonObject.length() > 0) {
                        /**
                         * Getting Array named "menu" From MAIN Json Object
                         */
                        JSONArray array = jsonObject.getJSONArray(Keys.KEY_MENU);

                        /**
                         * Check Length of Array...
                         */
                        int lenArray = array.length();
                        if (lenArray > 0) {
                            for (int jIndex = 0; jIndex < lenArray; jIndex++) {

                                /**
                                 * Creating Every time New Object
                                 * and
                                 * Adding into List
                                 */
                                MenuObject model = new MenuObject();

                                /**
                                 * Getting Inner Object from contacts array...
                                 * and
                                 * From that We will get Name of that Contact
                                 *
                                 */
                                JSONObject innerObject = array.getJSONObject(jIndex);
                                String day = innerObject.getString(Keys.KEY_DAY);
                                String data= innerObject.getString(Keys.KEY_DATA);
                                String dayName= innerObject.getString(Keys.KEY_DAY_NAME);
                                String lok1open = innerObject.getString(Keys.KEY_LOK1_OD);
                                String lok1close = innerObject.getString(Keys.KEY_LOK1_DO);
                                String lok2open = innerObject.getString(Keys.KEY_LOK2_OD);
                                String lok2close = innerObject.getString(Keys.KEY_LOK2_DO);
                                // String menuHTML = innerObject.getString(Keys.KEY_MENU_HTML);




                                //Log.e("KARSON", name + " - " + email);
                                // String image = innerObject.getString(Keys.KEY_PROFILE_PIC);

                                /**
                                 * Getting Object from Object "phone"
                                 JSONObject phoneObject = innerObject.getJSONObject(Keys.KEY_PHONE);
                                 String phone = phoneObject.getString(Keys.KEY_MOBILE);
                                 */
                                model.setDay(day);
                                model.setData(data);
                                model.setDayName(dayName);
                                model.setLok1open(lok1open);
                                model.setLok1close(lok1close);
                                model.setLok2open(lok2open);
                                model.setLok2close(lok2close);
                                // model.setMenuHTML(menuHTML);

                                /**
                                 * Adding name and phone concatenation in List...
                                 */

                                // list.add(model);

                            }
                        }
                    }
                } else {

                }
            } catch (JSONException je) {
                Log.i(JSONParser.TAG, "" + je.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            /**
             * Checking if List size if more than zero then
             * Update ListView
             */
            if (list.size() > 0) {
                // adapter2.notifyDataSetChanged();
            } else {
                Snackbar.make(findViewById(R.id.parentLayout), "No Data Found", Snackbar.LENGTH_LONG).show();
            }
        }
    }

}


