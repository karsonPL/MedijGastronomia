package co.gostyn.karson.medijgastronomia;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

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

import co.gostyn.karson.medijgastronomia.objects.Keys;
import co.gostyn.karson.medijgastronomia.objects.MenuObject;

/**
 * Created by karol on 2017-06-07.
 */

public class App extends Application {
    public static final String URL_O = "http://83.144.104.86/medij/api_generujHTML.php?for=0&typ=o&day=0&ver=1";
    public static final String URL_S = "http://83.144.104.86/medij/api_generujHTML.php?for=0&typ=s&day=0&ver=1";
    public static final String TAG = "TAG_KARSON";
    //public MenuObject menuObject;
    public ArrayList<MenuObject> arrayMenu;

    @Override
    public void onCreate() {
        super.onCreate();

        //menuObject = new MenuObject();
        arrayMenu = new ArrayList<>();
        new GetJSONfromUrl().execute(URL_O);

        // Log.e(TAG,app.arrayMenu.get(0).getCzynne());
        Log.e(TAG,"aaa"+getArrayMenu());

    }

   // public MenuObject getMenuObjectMon() {
   //     return menuObject;
   // }

    public ArrayList<MenuObject> getArrayMenu() {
        return arrayMenu;
    }

    private class GetJSONfromUrl extends AsyncTask<String, Void, String> {

        // okienko dialogowe, które każe użytkownikowi czekać
        //private ProgressDialog dialog = new ProgressDialog(App.this);

        // metoda wykonywana jest zaraz przed główną operacją (doInBackground())
        // mamy w niej dostęp do elementów UI
        @Override
        protected void onPreExecute() {
            // wyświetlamy okienko dialogowe każące czekać
         //   dialog.setMessage("Czekaj...");
          //  dialog.show();

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


            //App app = (App)getApplication();
            // chowamy okno dialogowe
          //  dialog.dismiss();
            //Log.e(TAG,"aafa");
            try {
                // reprezentacja obiektu JSON w Javie
                JSONObject json = new JSONObject(result);

                JSONArray array = json.getJSONArray(Keys.KEY_MENU);

                int lenArray = array.length();
                //Log.e(TAG,"dlugosc tablcy "+lenArray);

                if (lenArray > 0) {
                    for (int jIndex = 0; jIndex < lenArray; jIndex++) {

                        MenuObject model = new MenuObject();


                        JSONObject innerObject = array.getJSONObject(jIndex);
                        String day = innerObject.getString(Keys.KEY_DAY);
                        String data= innerObject.getString(Keys.KEY_DATA);
                        String czynne= innerObject.getString(Keys.KEY_CZYNNE);
                        String dayName= innerObject.getString(Keys.KEY_DAY_NAME);
                        String lok1open = innerObject.getString(Keys.KEY_LOK1_OD);
                        String lok1close = innerObject.getString(Keys.KEY_LOK1_DO);
                        String lok2open = innerObject.getString(Keys.KEY_LOK2_OD);
                        String lok2close = innerObject.getString(Keys.KEY_LOK2_DO);
                        String menuHTML = innerObject.getString(Keys.KEY_MENU_HTML);


                        model.setDay(day);
                        model.setData(data);
                        model.setCzynne(czynne);
                        model.setDayName(dayName);
                        model.setLok1open(lok1open);
                        model.setLok1close(lok1close);
                        model.setLok2open(lok2open);
                        model.setLok2close(lok2close);
                        model.setMenuHTML(menuHTML);
                        getArrayMenu().add(model);
                    }
                }
                //Log.e(TAG,"aaa"+app.arrayMenu.get(0).getCzynne());



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
