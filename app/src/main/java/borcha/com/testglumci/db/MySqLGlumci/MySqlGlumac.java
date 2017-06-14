package borcha.com.testglumci.db.MySqLGlumci;

import android.content.Context;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

import borcha.com.testglumci.db.MyDbHelp;
import borcha.com.testglumci.db.dbmodel.Film;
import borcha.com.testglumci.db.dbmodel.Glumac;
import borcha.com.testglumci.pomocne.infoPoruka;


/**
 * Created by borcha on 02.06.17..
 */

public class MySqlGlumac extends MyDbHelp {

    private Context cont;
    private Glumac glumac;
    private int id=0;


    /**
     * Konstruktor za unos. Nap. Ukoliko je sa Id-om ima moguce dodatne operacije kao sto su: <br> Update ili Delete.
     * @param _cont
     */
    public MySqlGlumac(Context _cont) {
        super(_cont);
        this.cont=_cont;
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
    }


    //*************************operaciej nad bazom *****************************************************


    public void updateGlumac() {

        int rez=0;

        if(getId()!=0){

            try {
                 rez=getDaoGlumac().updateId(glumac,getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }else{
            infoPoruka.newInstance(cont,"Poruka o gresci","Ne postoji ID zapisa!. Ne mozete prepraviti podatak za Glumac");

        }

    }

    /**
     * Brisanje jela
     */
    public void obrisiGlumac() {
        int rez= 0;

        try {
                rez = getDaoGlumac().delete(this.glumac);
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
                infoPoruka.newInstance(cont,"Poruka o snimanju","Uspeh"+ rez);
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
