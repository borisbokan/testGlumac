package com.borcha.testglumci.db.dbmodel;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;


/**
 * Created by borcha on 20.05.17..
 */
@DatabaseTable(tableName = Glumac.tGLUMAC)
public class Glumac {

    public static final String tGLUMAC="tGlumac";
    public static final String tGLUMCI_id="id";
    public static final String tGLUMCI_ime="ime";
    public static final String tGLUMCI_prezime="prezime";
    public static final String tGLUMCI_biografija="biografija";
    public static final String tGLUMCI_datumrodjenja="datumrodjenja";
    public static final String tGLUMCI_rejting="rejting";


    @DatabaseField(columnName = tGLUMCI_id,generatedId = true)
    private int id;
    @DatabaseField(columnName = tGLUMCI_ime)
    private String ime;
    @DatabaseField(columnName = tGLUMCI_prezime)
    private String prezime;
    @DatabaseField(columnName = tGLUMCI_biografija)
    private String biografija;
    @DatabaseField(columnName = tGLUMCI_datumrodjenja)
    private Date datumRodjenja;
    @DatabaseField(columnName = tGLUMCI_rejting)
    private float rejting;

    @ForeignCollectionField(foreignFieldName = "glumac",eager = true)
    ForeignCollection<Film> filmovi;


    public Glumac() { }

    public Glumac( String _ime, String _prezime, Date _datumRodjenja, float _rejting) {
        this.ime = _ime;
        this.prezime = _prezime;
        this.datumRodjenja = _datumRodjenja;
        this.rejting=_rejting;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBiografija() {
        return biografija;
    }

    public void setBiografija(String biografija) {
        this.biografija = biografija;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }


    public float getRejting() {
        return rejting;
    }

    public void setRejting(float rejting) {
        this.rejting = rejting;
    }

    public ForeignCollection<Film> getFilmovi() {
        return filmovi;
    }

    public void setFilmovi(ForeignCollection<Film> filmovi) {
        this.filmovi = filmovi;
    }


    public String toString() {
        return "Glumac>> " + id + " - " + ime + " " + prezime + " " + datumRodjenja;
    }

}
