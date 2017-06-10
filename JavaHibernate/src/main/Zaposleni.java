package main;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zaposleni")
public class Zaposleni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zaposleni_id")
    private int id;
    @Column(name = "ime")
    private String ime;
    @Column(name = "godine")
    private int godine;
    @Column(name = "adresa")
    private String adresa;
    @Column(name = "zarada")
    private String zarada;

    public int getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public int getGodine() {
        return godine;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getZarada() {
        return zarada;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setGodine(int godine) {
        this.godine = godine;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setZarada(String zarada) {
        this.zarada = zarada;
    }

    public Zaposleni() {
    }

    public Zaposleni(String ime, int godine, String adresa, String zarada) {
        this.ime = ime;
        this.godine = godine;
        this.adresa = adresa;
        this.zarada = zarada;
    }
    
       @Override
    public String toString() {
        return "  ID : "+id + "\n  IME : " + ime + "\n  GODINE : " + godine +"\n  ADRESA : " + adresa + "\n  ZARADA : " + zarada+"\n";
    }
}
