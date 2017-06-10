package co.gostyn.karson.medijgastronomia.parser;

import android.support.annotation.NonNull;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class __JSONParser {

    public static final String URL_O = "http://83.144.104.86/medij/api_generujHTML.php?for=0&typ=o&day=0&ver=1";
    public static final String URL_S = "http://83.144.104.86/medij/api_generujHTML.php?for=0&typ=s&day=0&ver=1";

    public static final String TAG = "TAG_KARSON";

     private static Response response;

    /**
     * Get Table Booking Charge
     *
     * @return JSON Object
     */
    public static JSONObject getDataFromWeb() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(URL_O)
                    .build();
            response = client.newCall(request).execute();
            Log.e(TAG, "KKK" + response.body().string());

            return new JSONObject(response.body().string());


        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "karson" + e.getLocalizedMessage());
        }
        return null;
    }

    /**
     * Get Particular Record
     *
     * @param userId UserId
     * @return JSONObject

    public static JSONObject getDataById(int userId) {

        try {
            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormEncodingBuilder()
                    .add(KEY_USER_ID, Integer.toString(userId))
                    .build();

            Request request = new Request.Builder()
                    .url(MAIN_URL)
                    .post(formBody)
                    .build();

            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());

        } catch (IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }*/
}
