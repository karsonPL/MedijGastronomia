package co.gostyn.karson.medijgastronomia;

import android.app.Application;

import java.util.ArrayList;

import co.gostyn.karson.medijgastronomia.objects.MenuObject;

/**
 * Created by karol on 2017-06-07.
 */

public class App extends Application {

    private static final String URL = "http://83.144.104.86/medij/";
    private static final String URL_O = "http://192.168.0.2/medij/api.php?for=0&typ=o&day=0&ver=1";

    //    private static final String URL_O = "http://test.gostyn.co/api.php?for=0&typ=o&day=0&ver=1";
    private static final String URL_S = "http://test.gostyn.co/api.php?for=0&typ=s&day=0&ver=1";

    private ArrayList<MenuObject> arrayMenuO;
    private ArrayList<MenuObject> arrayMenuS;
    private Boolean isLoadedMenuO = false;
    private Boolean isLoadedMenuS = false;



    @Override
    public void onCreate() {
        super.onCreate();

        arrayMenuO = new ArrayList<>();
        arrayMenuS = new ArrayList<>();
        //new GetJSONfromUrl().execute(URL_O);

        // Log.e(TAG,app.arrayMenuO.get(0).getCzynne());
       // Log.e(TAG,"aaa"+getArrayMenu());

    }



    public ArrayList<MenuObject> getArrayMenuO() {
        return arrayMenuO;
    }
    public ArrayList<MenuObject> getArrayMenuS() {
        return arrayMenuS;
    }

    public Boolean getIsLoadedMenuO() {
        return isLoadedMenuO;
    }

    public void setIsLoadedMenuO(Boolean isLoadedMenuO) {
        this.isLoadedMenuO = isLoadedMenuO;
    }

    public Boolean getIsLoadedMenuS() {
        return isLoadedMenuS;
    }

    public void setIsLoadedMenuS(Boolean isLoadedMenuS) {
        this.isLoadedMenuS = isLoadedMenuS;
    }

    public static String getURL() {
        return URL;
    }


}
