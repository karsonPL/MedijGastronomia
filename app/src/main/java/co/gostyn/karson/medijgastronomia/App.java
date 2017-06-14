package co.gostyn.karson.medijgastronomia;

import android.app.Application;

import java.util.ArrayList;

import co.gostyn.karson.medijgastronomia.objects.MenuObject;

/**
 * Created by karol on 2017-06-07.
 */

public class App extends Application {

    private static final String APP_VER = "1.0";
    private static final String URL = "http://83.144.104.86/medij/";
    private static final String URL_O = "http://83.144.104.86/medij/api.php?for=0&typ=o&day=0&ver=1";
    private static final String URL_S = "http://83.144.104.86/medij/api.php?for=0&typ=s&day=0&ver=1";
    //private static final String URL_O = "http://test.gostyn.co/api.php?for=0&typ=o&day=0&ver="+APP_VER;
    //private static final String URL_O = "http://test.gostyn.co/api.php?for=0&typ=s&day=0&ver="+APP_VER;


    private ArrayList<MenuObject> arrayMenuO;
    private ArrayList<MenuObject> arrayMenuS;
    private Boolean isLoadedMenuO = false;
    private Boolean isLoadedMenuS = false;
    private Boolean isInternet = false;
    private int menuToolbarName;
    private int otherMenuPressOnToolbar;
    private String whatMenuType;
    private int isDayOfWeek;


    @Override
    public void onCreate() {
        super.onCreate();

        arrayMenuO = new ArrayList<>();
        arrayMenuS = new ArrayList<>();


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

    public static String getUrlO() {
        return URL_O;
    }

    public static String getUrlS() {
        return URL_S;
    }

    public Boolean getIsInternet() {
        return isInternet;
    }

    public void setIsInternet(Boolean internet) {
        isInternet = internet;
    }

    public int getMenuToolbarName() {
        return menuToolbarName;
    }

    public void setMenuToolbarName(int menuToolbarName) {
        this.menuToolbarName = menuToolbarName;
    }

    public int getOtherMenuPressOnToolbar() {
        return otherMenuPressOnToolbar;
    }

    public void setOtherMenuPressOnToolbar(int otherMenuPressOnToolbar) {
        this.otherMenuPressOnToolbar = otherMenuPressOnToolbar;
    }

    public String getWhatMenuType() {
        return whatMenuType;
    }

    public void setWhatMenuType(String whatMenuType) {
        this.whatMenuType = whatMenuType;
    }

    public int getIsDayOfWeek() {
        return isDayOfWeek;
    }

    public void setIsDayOfWeek(int isDayOfWeek) {
        this.isDayOfWeek = isDayOfWeek;
    }
}
