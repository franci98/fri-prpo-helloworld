package si.fri.prpo.servlet;

public class Uporabnik extends Entiteta {

    public String ime;
    public String priimek;
    public String uporabniskoIme;

    public Uporabnik(String ime, String priimek, String uporabniskoIme) {
        super();
        this.ime = ime;
        this.priimek = priimek;
        this.uporabniskoIme = uporabniskoIme;
    }

    public String toString() {
        return ime + " " + priimek + " " + uporabniskoIme;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getUporabniskoIme() {
        return uporabniskoIme;
    }

    public void setUporabniskoIme(String uporabniskoIme) {
        this.uporabniskoIme = uporabniskoIme;
    }
}
