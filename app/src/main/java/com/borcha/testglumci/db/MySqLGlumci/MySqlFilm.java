package com.borcha.testglumci.db.MySqLGlumci;

import android.content.Context;
import android.content.Intent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.borcha.testglumci.db.MyDbHelp;
import com.borcha.testglumci.db.dbmodel.Film;
import com.borcha.testglumci.db.dbmodel.Glumac;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.stmt.query.In;


/**
 * Created by borcha on 02.06.17..
 */

public class MySqlFilm extends MyDbHelp {

    private Context cont;
    private Film film;
    private int id=0;



    /**
     * Konstruktor za unos. Nap. Ukoliko je sa Id-om ima moguce dodatne operacije kao sto su: <br> Update ili Delete.
     * @param _cont

     */
    public MySqlFilm(Context _cont){
        super(_cont);
        this.cont=_cont;

    }

    /**
     * Konstruktor sa Id-om je ukoliko saljemo u cilju update ili brisanja podatka.
     * @param _cont
     * @param _Film
     */
    public MySqlFilm(Context _cont, Film _Film) {
        super(_cont);
        this.cont = _cont;
        this.film=_Film;

    }


    //*************************operaciej nad bazom *****************************************************

    /**
     * Update jela
     */
    public void prepraviFilm() {

       int rez= 0;

        try {
            rez = getDaoFilm().update(film);
            //PrepraviKategoriju.OnPrepraviKategoriju(rez);
        } catch (SQLException e) {
            e.printStackTrace();
        }




    }

    /**
     * Brisanje jela
     */
    public void obrisiFilm()  {
        int rez= 0;
            try{
                rez=getDaoFilm().delete(this.film);
                //ObrisiKategoriju.OnObrisiKategoriju(rez);


            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    /**
     * Unos novog Filma
     * @param _Film
     */
    public void snimiNoviFilm(Film _Film) {

        if(!_Film.equals(null)) {
            //TODO. Uraditi Sql upit za delete
            int rez = 0;
            try {
                rez = getDaoFilm().create(_Film);


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    //Vraca listu svih objekata Jelo
    public List<Film> getSviFilmovi() {
        List<Film> lista=new ArrayList<>();
        try {
            lista=getDaoFilm().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    //Trazi vrednost jela po ID zapisu
    public Film getFilmPoId(int _id) {
        Film Film=null;
        try {
            Film= getDaoFilm().queryForId(_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Film;
    }



    //Trazi vrednost jela po ID zapisu
    public List<Film> getFilmoviPoGlumcu(Glumac _glumac) {
        List<Film> filmovi=null;
        try {

            QueryBuilder<Film,Integer> query=getDaoFilm().queryBuilder();
            Where<Film,Integer> where=query.where().idEq(getDaoGlumac(),_glumac);
            filmovi= where.query();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmovi;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Film getFilm() {
        return this.film;
    }

    public void setFilm(Film Film) {
        this.film = Film;
    }


    public int getBrojFilm(){
        int br=0;
        try {
            br=getDaoFilm().queryForAll().size();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return br;
    }




}
