package co.gostyn.karson.medijgastronomia.objects;

/**
 * Created by karol on 2017-06-06.
 */

public class MenuObject {

    private int id;
    private String rodzaj;
    private String nazwa;
    private String opis1;
    private String opis2;
    private String ilosc;
    private String cena;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis1() {
        return opis1;
    }

    public void setOpis1(String opis1) {
        this.opis1 = opis1;
    }

    public String getOpis2() {
        return opis2;
    }

    public void setOpis2(String opis2) {
        this.opis2 = opis2;
    }

    public String getIlosc() {
        return ilosc;
    }

    public void setIlosc(String ilosc) {
        this.ilosc = ilosc;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }
}
