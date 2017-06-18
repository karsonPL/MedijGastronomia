package co.gostyn.karson.medijgastronomia;

import android.app.Application;
import android.os.Build;

import com.jaredrummler.android.device.DeviceName;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

import co.gostyn.karson.medijgastronomia.objects.MenuObject;

/**
 * Created by karol on 2017-06-07.
 */

public class App extends Application {

    private static final String APP_VER = "1_0_beta";
     private static final String URL = "http://83.144.104.86/medij/";
     private static final String URL_O = "http://83.144.104.86/medij/api.php?typ=o&day=0&ver="+APP_VER+"&ip="+getLocalIpAddress();
     private static final String URL_S = "http://83.144.104.86/medij/api.php?typ=s&day=0&ver="+APP_VER+"&ip="+getLocalIpAddress();
    //private static final String URL = "http://test.gostyn.co/";
    //private static final String URL_O = "http://test.gostyn.co/api.php?typ=o&day=0&ver=" + APP_VER + "&ip=" + getDeviceName().replace(" ", "_");
    //private static final String URL_S = "http://test.gostyn.co/api.php?typ=s&day=0&ver=" + APP_VER + "&ip=" + DeviceName.getDeviceName().replace(" ", "_");


    private ArrayList<MenuObject> arrayMenuO;
    private ArrayList<MenuObject> arrayMenuS;
    private Boolean isLoadedMenuOS = false;
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

        // String deviceName = android.os.Build.MODEL;
        // String deviceMan = android.os.Build.MANUFACTURER;
        //String mDeviceName = DeviceName.getDeviceName();

        //Log.e("TAG_KAR", "onCreate: "+deviceName );


    }


    public ArrayList<MenuObject> getArrayMenuO() {
        return arrayMenuO;
    }

    public ArrayList<MenuObject> getArrayMenuS() {
        return arrayMenuS;
    }

    public Boolean getIsLoadedMenuOS() {
        return isLoadedMenuOS;
    }

    public void setIsLoadedMenuOS(Boolean isLoadedMenuOS) {
        this.isLoadedMenuOS = isLoadedMenuOS;
    }

    public static String getAppVer() { return APP_VER;   }

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

    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {

                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }


    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

}
