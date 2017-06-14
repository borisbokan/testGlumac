package borcha.com.testglumcibor.db.dbmodel;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by androiddevelopment on 13.6.17..
 */
@DatabaseTable(tableName= Film.tFilm)
public class Film {

    public static final String tFilm="film";
    public static final String tFilm_id="id";
    public static final String tFilm_naziv="naziv";
    public static final String tFilm_zanr="zanr";
    public static final String tFilm_glumac="glumac";


    @DatabaseField(columnName = tFilm_id,generatedId = true)
    private int id;
    @DatabaseField(columnName = tFilm_naziv)
    private String naziv;
    @DatabaseField(columnName = tFilm_zanr)
    private String zanr;

    @DatabaseField(columnName = tFilm_glumac,foreign = true,foreignAutoCreate = true,foreignAutoRefresh = true)
    private Glumac glumac;


    public Glumac getGlumac() {
        return glumac;
    }

    public void setGlumac(Glumac glumac) {
        this.glumac = glumac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }


}
