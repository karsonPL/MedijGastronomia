package co.gostyn.karson.medijgastronomia;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.gostyn.karson.medijgastronomia.adapter.MenuObjectAdapter;
import co.gostyn.karson.medijgastronomia.objects.Keys;
import co.gostyn.karson.medijgastronomia.objects.MenuObject;
import co.gostyn.karson.medijgastronomia.parser.JSONParser;


/**
 * Created by Karol on 2017-06-06.
 */

public class MenuFragment extends Fragment {

    private ArrayList<MenuObject> list;
    private String data;
    private MenuObjectAdapter adapter2;

   // @Bind(R.id.tvMenuFragment)
    TextView tvMenuFragment;
    @Bind(R.id.listView)
    ListView listView;
    MenuObject abc;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.menu_fragment, container, false);
        ButterKnife.bind(this, view);


        list = new ArrayList<>();



        adapter2 = new MenuObjectAdapter(container.getContext(), list);
        listView.setAdapter(adapter2);
        new GetDataTask2().execute();





        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //tvMenuFragment.setText("ddf");



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }



    class GetDataTask2 extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;



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
                                String rodzaj = innerObject.getString(Keys.KEY_RODZAJ);
                                String nazwa = innerObject.getString(Keys.KEY_NAZWA);
                                String opis1 = innerObject.getString(Keys.KEY_OPIS1);
                                String opis2 = innerObject.getString(Keys.KEY_OPIS2);
                                String ilosc = innerObject.getString(Keys.KEY_ILOSC);
                                String cena = innerObject.getString(Keys.KEY_CENA);

                                //Log.e("KARSON", name + " - " + email);
                                // String image = innerObject.getString(Keys.KEY_PROFILE_PIC);

                                /**
                                 * Getting Object from Object "phone"
                                 JSONObject phoneObject = innerObject.getJSONObject(Keys.KEY_PHONE);
                                 String phone = phoneObject.getString(Keys.KEY_MOBILE);
                                 */
                                model.setRodzaj(rodzaj);
                                model.setNazwa(nazwa);
                                model.setOpis1(opis1);
                                model.setOpis2(opis2);
                                model.setIlosc(ilosc);
                                model.setCena(cena);

                                /**
                                 * Adding name and phone concatenation in List...
                                 */
                                if (!rodzaj.contains("czynne") && !rodzaj.contains("data")) {
                                    list.add(model);
                                } else if (rodzaj.contains("data")) {
                                    data = nazwa;
                                }
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


    }
}
