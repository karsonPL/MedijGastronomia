package co.gostyn.karson.medijgastronomia;

import android.app.Application;

import java.util.ArrayList;

import co.gostyn.karson.medijgastronomia.objects.MenuObject;

/**
 * Created by karol on 2017-06-07.
 */

public class App extends Application {

    private MenuObject menuObjectMon;
    private ArrayList<MenuObject> arrayMenuPon;

    @Override
    public void onCreate() {
        super.onCreate();

        menuObjectMon = new MenuObject();
        arrayMenuPon = new ArrayList<>();


    }

    public MenuObject getMenuObjectMon() {
        return menuObjectMon;
    }

    public ArrayList<MenuObject> getArrayMenuPon() {
        return arrayMenuPon;
    }
}
