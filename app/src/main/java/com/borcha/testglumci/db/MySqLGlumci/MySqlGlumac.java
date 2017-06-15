package com.borcha.testglumci.db.MySqLGlumci;

import android.app.NotificationManager;
import android.content.Context;
import android.widget.Toast;

import com.borcha.testglumci.Aktivnosti.PodesavanjaActivty;
import com.borcha.testglumci.pomocne.myNotification;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

import com.borcha.testglumci.db.MyDbHelp;
import com.borcha.testglumci.db.dbmodel.Film;
import com.borcha.testglumci.db.dbmodel.Glumac;
import com.borcha.testglumci.pomocne.infoPoruka;


/**
 * Created by borcha on 02.06.17..
 */

public class MySqlGlumac extends MyDbHelp {

    private boolean toasUklj;
    private boolean notifUkl;
    private Context cont;
    private Glumac glumac;
    private int id=0;
    NotificationManager mNotificationManager;

    /**
     * Konstruktor za unos. Nap. Ukoliko je sa Id-om ima moguce dodatne operacije kao sto su: <br> Update ili Delete.
     * @param _cont
     */
    public MySqlGlumac(Context _cont) {
        super(_cont);
        this.cont=_cont;

        PodesavanjaActivty podesavanja=new PodesavanjaActivty();
        notifUkl= podesavanja.jelNotifikacionaPorukaUkljucena();
        toasUklj=podesavanja.jelToastPorukaUkljucena();

         mNotificationManager = (NotificationManager)cont.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    /**
     * Konstruktor sa Id-om je ukoliko saljemo u cilju update ili brisanja podatka.
     * @param _cont
     * @param _id
     */
    public MySqlGlumac(Context _cont, int _id) {
        super(_cont);
        this.cont=_cont;
        this.id=_id;

        PodesavanjaActivty podesavanja=new PodesavanjaActivty();
        notifUkl= podesavanja.jelNotifikacionaPorukaUkljucena();
        toasUklj=podesavanja.jelToastPorukaUkljucena();

        mNotificationManager = (NotificationManager)cont.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public MySqlGlumac(Context _cont, Glumac _glumac) {
        super(_cont);
        this.cont=_cont;
        this.glumac=_glumac;

        PodesavanjaActivty podesavanja=new PodesavanjaActivty();
        notifUkl= podesavanja.jelNotifikacionaPorukaUkljucena();
        toasUklj=podesavanja.jelToastPorukaUkljucena();

        mNotificationManager = (NotificationManager)cont.getSystemService(Context.NOTIFICATION_SERVICE);
    }



    //*************************operaciej nad bazom *****************************************************


    public void updateGlumac() {

        int rez=0;

        if(getId()!=0){

            try {
                rez=getDaoGlumac().updateId(glumac,getId());

                if(rez==1){
                   uslovnaNotifikacija("Ispravka glumca","Ispravka glumca " + glumac.getPrezime() + ", " +  glumac.getIme() + " uspesna");
                    uslovnaToastPoruka("Ispravka glumca","Ispravka glumca " + glumac.getPrezime() + ", " +  glumac.getIme() + " uspesna");
                }else{
                    uslovnaNotifikacija("Ispravka glumca","Ispravka glumca " + glumac.getPrezime() + ", " +  glumac.getIme() + " neuspesna. Greska!");
                    uslovnaToastPoruka("Ispravka glumca","Ispravka glumca " + glumac.getPrezime() + ", " +  glumac.getIme() + " neuspesna. Greska!");
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }


        }else{
            infoPoruka.newInstance(cont,"Poruka o gresci","Ne postoji ID zapisa!. Ne mozete prepraviti podatak za Glumac");

        }

    }

    private void uslovnaToastPoruka(String naslov, String poruka) {
        if(toasUklj){
            Toast.makeText(cont,naslov + " - " + poruka,Toast.LENGTH_LONG).show();
        }
    }

    private void uslovnaNotifikacija(String naslov, String poruka) {

        if(notifUkl){

            myNotification notifikacija=new myNotification(cont,naslov,poruka);
            mNotificationManager.notify(13,notifikacija.build());
        }
    }

    /**
     * Brisanje jela
     */
    public void obrisiGlumac() {
        int rez= 0;

        try {
                rez = getDaoGlumac().delete(this.glumac);
            if(rez==1){
                uslovnaNotifikacija("Brisanje glumca","Brisanje glumca " + glumac.getPrezime() + ", " +  glumac.getIme() + " uspesna");
                uslovnaToastPoruka("Brisanje glumca","Brisanje glumca " + glumac.getPrezime() + ", " +  glumac.getIme() + " uspesna");
            }else{
                uslovnaNotifikacija("Brisanje glumca","Brisanje glumca " + glumac.getPrezime() + ", " +  glumac.getIme() + " neuspesna. Greska!");
                uslovnaToastPoruka("Brisanje glumca","Brisanje glumca " + glumac.getPrezime() + ", " +  glumac.getIme() + " neuspesna. Greska!");
            }


                //ObrisiGlumac.OnObrisiGlumac(rez);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    /**
     * Unos novog jela
     * @param _Glumac
     */
    public void snimiNovoGlumac(Glumac _Glumac) {

        if(!_Glumac.equals(null)){
            //TODO. Uraditi Sql upit za delete
            int rez= 0;
            try {
                rez = getDaoGlumac().create(_Glumac);

                if(rez==1){
                    uslovnaNotifikacija("Unos glumca","Unos glumca " + glumac.getPrezime() + ", " +  glumac.getIme() + " uspesna");
                    uslovnaToastPoruka("Unos glumca","Unos glumca " + glumac.getPrezime() + ", " +  glumac.getIme() + " uspesna");
                }else{
                    uslovnaNotifikacija("Unos glumca","Unos glumca " + glumac.getPrezime() + ", " +  glumac.getIme() + " neuspesna. Greska!");
                    uslovnaToastPoruka("Unos glumca","Unos glumca " + glumac.getPrezime() + ", " +  glumac.getIme() + " neuspesna. Greska!");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }


        }else{
            infoPoruka.newInstance(cont,"Poruka o gresci","Objekat Glumac ima  null vrednsot");
        }



    }


    //Vraca listu svih objekata Glumac
    public List<Glumac> getSviGlumci()  {
        List<Glumac> glumci=null;
        try {
            glumci= getDaoGlumac().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return glumci;
    }

    //Trazi vrednost jela po ID zapisu
    public Glumac getGlumacPoId(int _id)  {

        try {
            return getDaoGlumac().queryForId(_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  Vraca listu jela po kategoriji
     */

    public List<Glumac> getGlumaciPioZanru(Film _film)  {
        List<Glumac> glumci=null;
        try {
            QueryBuilder upit = getDaoGlumac().queryBuilder();
            Where<Glumac,Integer> where=upit.where().idEq(getDaoFilm(),_film);
            glumci=where.query();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return glumci;
    }

    public void setGlumac(Glumac Glumac) {
        this.glumac = Glumac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrojGlumaca() {
        int br = 0;
        try {
            br = getDaoGlumac().queryForAll().size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return br;
    }


}
