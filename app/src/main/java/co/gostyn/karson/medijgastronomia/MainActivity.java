package co.gostyn.karson.medijgastronomia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.gostyn.karson.medijgastronomia.objects.Keys;
import co.gostyn.karson.medijgastronomia.objects.MenuObject;
import co.gostyn.karson.medijgastronomia.utils.InternetConnection;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TAG_KARSON";

    private App app;

    @Bind(R.id.mainActivityLayout)
    RelativeLayout mainActivityLayout;
    @Bind(R.id.button_medij)
    ImageView buttonMedij;
    @Bind(R.id.button2)
    Button button2;
    @Bind(R.id.button1)
    Button button1;

    Intent intent;
    int tryAgain = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        app = (App) getApplication();   //instancja do zmiennych w App

        setMenuType(); //ustawienie w zaleznosci od pory dnia, tygodnia

        if (InternetConnection.checkConnection(getApplicationContext())) { //sprawdzenie polaczenia z Internetem
            app.setIsInternet(true);
        } else {
            Snackbar.make(mainActivityLayout, R.string.msg_no_Internet, Snackbar.LENGTH_LONG).show();
        }

        //Log.e(TAG, app.getIsLoadedMenuO().toString());
        getDateFromUrl(); //pobranie JSONa z danymi z serwera i wladowanie do tablel


        //buttonMedij.setClickable(true);

    }


    @OnClick({R.id.button2, R.id.button1})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.button2:

                if (app.getIsLoadedMenuO() && app.getIsLoadedMenuS()) {
                    intent = new Intent(this, LokaleInfoActivity.class);
                    startActivity(intent);
                } else {
                    if (tryAgain > 2) { getDateFromUrl(); tryAgain=0;} else { tryAgain++; }
                    Snackbar.make(mainActivityLayout, "Nie pobrano danych, spróbuj później!", Snackbar.LENGTH_SHORT).show();
                }
                break;
            case R.id.button1:
                if (app.getIsLoadedMenuO() && app.getIsLoadedMenuS()) {
                    intent = new Intent(this, MenuActivity.class);
                    startActivity(intent);
                } else {
                    if (tryAgain > 2) { getDateFromUrl(); tryAgain=0;} else { tryAgain++; }
                    Snackbar.make(mainActivityLayout, "Nie pobrano danych, spróbuj później!", Snackbar.LENGTH_SHORT).show();
                }
                break;

            case R.id.button_medij:
                app.setIsLoadedMenuO(false);
                break;
        }
    }

    private void getDateFromUrl() {
        if (!app.getIsLoadedMenuO()) {
            new GetJSONfromUrl().execute(App.getUrlO(), "o");
            //if (app.getLoadedMenuOok()) { app.setIsLoadedMenuO(true); }
        }
        if (!app.getIsLoadedMenuS()) {
            new GetJSONfromUrl().execute(App.getUrlS(), "s");
            //if (app.getLoadedMenuSok()) { app.setIsLoadedMenuS(true); }
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
            dialog.setMessage("Pobieranie danych! Czekaj...");
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
                connection.setConnectTimeout(7000); //7 sekund na probe polaczenia

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
                    if (params[1] == "o") app.setIsLoadedMenuO(true);
                    if (params[1] == "s") app.setIsLoadedMenuS(true);
                }
                return null;

            } catch (Exception e) {
                // obsłuż wyjątek
                //Log.d(MainActivity.class.getSimpleName(), e.toString());
                //Log.d(TAG, "blad "+e.toString());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            //Log.e(TAG, "result: " + app.getIsLoadedMenuO());
            dialog.dismiss();
            if (app.getIsLoadedMenuO() && app.getIsLoadedMenuS()) {
                Snackbar.make(mainActivityLayout, "Pobrano dane!", Snackbar.LENGTH_LONG).show();
            } else {
                //Snackbar.make(mainActivityLayout, "????Nie pobrano danych, spróbuj później!", Snackbar.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
            //Log.e(TAG, "onCancelled: "+s );
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

    private void setMenuType() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
        timeFormat.setTimeZone(TimeZone.getTimeZone("Poland"));
        Integer time = Integer.parseInt(timeFormat.format(currentDate));

        if (time > 1130 && time < 1800) {
            app.setWhatMenuType("o");
            app.setMenuToolbarName(R.string.title_activity_menu_obiad);
            app.setOtherMenuPressOnToolbar(R.string.txt_other_menu_type_sniadanie);

        } else {
            app.setWhatMenuType("s");
            app.setMenuToolbarName(R.string.title_activity_menu_sniadanie);
            app.setOtherMenuPressOnToolbar(R.string.txt_other_menu_type_obiad);
        }
        // ustawienie dnia tygodznia - pn = 0 ... n = 6
        int day = 0;
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1: //niedziela
                day = 6;
                break;
            case 2: //Pn- So
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                day = calendar.get(Calendar.DAY_OF_WEEK) - 2;
                break;
        }
        app.setIsDayOfWeek(day);

    }

}
