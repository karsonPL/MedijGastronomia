package co.gostyn.karson.medijgastronomia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.gostyn.karson.medijgastronomia.objects.Keys;
import co.gostyn.karson.medijgastronomia.objects.MenuObject;

public class MainActivity extends AppCompatActivity {

    public static final String URL_O = "http://83.144.104.86/medij/api.php?for=0&typ=o&day=0&ver=1";

//    public static final String URL_O = "http://test.gostyn.co/api.php?for=0&typ=o&day=0&ver=1";
    public static final String URL_S = "http://test.gostyn.co/api.php?for=0&typ=s&day=0&ver=1";
    public static final String TAG = "TAG_KARSON";
    private App app;

    @Bind(R.id.button_medij)
    ImageView buttonMedij;
    @Bind(R.id.button2)
    Button button2;
    @Bind(R.id.button1)
    Button button1;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        app = (App) getApplication();

        Log.e(TAG, app.getIsLoadedMenuO().toString());

        if (!app.getIsLoadedMenuO()) {
            new GetJSONfromUrl().execute(URL_O,"o");
           // app.setIsLoadedMenuO(true);
        }
        if (!app.getIsLoadedMenuS()) {
            new GetJSONfromUrl().execute(URL_S,"s");
            app.setIsLoadedMenuS(true);
        }


        buttonMedij.setClickable(true);

    }


    @OnClick({R.id.button2, R.id.button1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button2:
                intent = new Intent(this, MenuActivity.class);
                startActivity(intent);

                break;
            case R.id.button1:
                intent = new Intent(this, Menu2Activity.class);
                startActivity(intent);
                break;

            case R.id.button_medij:
                app.setIsLoadedMenuO(false);
                break;
        }

    }

    private class GetJSONfromUrl extends AsyncTask<String, Void, String> {

        // okienko dialogowe, które każe użytkownikowi czekać
        private ProgressDialog dialog = new ProgressDialog(MainActivity.this);

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
        protected String doInBackground(String... params) {

            try {
                // zakładamy, że jest tylko jeden URL
                URL url = new URL(params[0]);
                URLConnection connection = url.openConnection();

                // pobranie danych do InputStream
                InputStream in = new BufferedInputStream(
                        connection.getInputStream());


                //wypelnienie tabel
                JSONObject json = new JSONObject(streamToString(in));

                JSONArray array = json.getJSONArray(Keys.KEY_MENU);

                int lenArray = array.length();
                //Log.e(TAG,"dlugosc tablcy "+lenArray);

                if (lenArray > 0) {
                    for (int jIndex = 0; jIndex < lenArray; jIndex++) {

                        MenuObject model = new MenuObject();


                        JSONObject innerObject = array.getJSONObject(jIndex);
                        String day = innerObject.getString(Keys.KEY_DAY);
                        String data = innerObject.getString(Keys.KEY_DATA);
                        String czynne = innerObject.getString(Keys.KEY_CZYNNE);
                        String dayName = innerObject.getString(Keys.KEY_DAY_NAME);
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

                        if (params[1] == "o") app.getArrayMenuO().add(model);
                        if (params[1] == "s") app.getArrayMenuS().add(model);
                    }
                }


                return null;

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


            try {



            } catch (Exception e) {
                // obsłuż wyjątek
                Log.d(MainActivity.class.getSimpleName(), e.toString());
            }
            dialog.dismiss();
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
