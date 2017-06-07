package co.gostyn.karson.medijgastronomia.objects;

import static android.R.attr.id;

/**
 * Created by karol on 2017-06-06.
 */

public class MenuObject {

    private String day;
    private String data;
    private String czynne;
    private String dayName;
    private String lok1open;
    private String lok1close;
    private String lok2open;
    private String lok2close;
    private String menuHTML;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCzynne() {
        return czynne;
    }

    public void setCzynne(String czynne) {
        this.czynne = czynne;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getLok1open() {
        return lok1open;
    }

    public void setLok1open(String lok1open) {
        this.lok1open = lok1open;
    }

    public String getLok1close() {
        return lok1close;
    }

    public void setLok1close(String lok1close) {
        this.lok1close = lok1close;
    }

    public String getLok2open() {
        return lok2open;
    }

    public void setLok2open(String lok2open) {
        this.lok2open = lok2open;
    }

    public String getLok2close() {
        return lok2close;
    }

    public void setLok2close(String lok2close) {
        this.lok2close = lok2close;
    }

    public String getMenuHTML() {
        return menuHTML;
    }

    public void setMenuHTML(String menuHTML) {
        this.menuHTML = menuHTML;
    }
}
