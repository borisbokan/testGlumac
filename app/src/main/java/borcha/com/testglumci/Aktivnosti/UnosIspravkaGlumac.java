package borcha.com.testglumci.Aktivnosti;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Date;

import borcha.com.testglumci.db.MySqLGlumci.MySqlGlumac;
import borcha.com.testglumci.db.dbmodel.Glumac;
import borcha.com.testglumci.R;

/**
 * Created by androiddevelopment on 13.6.17..
 */

public class UnosIspravkaGlumac extends AppCompatActivity implements View.OnClickListener {

    public static final int TIPOPERACIJE_NOVO=0;//Ukoliko dolazimo sa novim ulazom
    public static final int TIPOPERACIJE_ISPRAVI=1;//Ukoliko dolazimo da ispravimo podatak

    EditText etxtPrezimeIme,etxtIme;
    DatePicker datumRodjenja;
    Button btnSnimi,btnOdustajem;

    private int tip_ope=0;
    private int idGlumac;
    private Glumac glumac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unos_ispravka_glumca);

        tip_ope=getIntent().getIntExtra("operacija",0);
        idGlumac=getIntent().getIntExtra("id_glumac",0);

        etxtPrezimeIme=(EditText)findViewById(R.id.etxtPrezime_unosispravka);
        etxtIme=(EditText)findViewById(R.id.etxtPrezime_unosispravka);
        datumRodjenja=(DatePicker)findViewById(R.id.dpDatumRodjenja_unosispravka);

        btnSnimi=(Button)findViewById(R.id.btnSnimi_unosispravka);
        btnOdustajem=(Button)findViewById(R.id.btnOdustajem_unosispravka);

        btnSnimi.setOnClickListener(this);
        btnOdustajem.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnSnimi_unosispravka:

                if(tip_ope==TIPOPERACIJE_NOVO){
                    snimi();

                }else{
                    ispravi();
                }
                break;


            case R.id.btnOdustajem_unosispravka:

                 finish();

                break;

            default:

                break;


        }
    }


    private void getGlumac(int _id){

        MySqlGlumac dbglumac=new MySqlGlumac(this);
        this.glumac=dbglumac.getGlumacPoId(_id);

    }

    private void ispravi() {
         getGlumac(idGlumac);

         MySqlGlumac dbglumac=new MySqlGlumac(this);
         dbglumac.setGlumac(this.glumac);
         dbglumac.updateGlumac();


    }

    private void snimi() {
         final Glumac novglumac=new Glumac();
        novglumac.setPrezime(this.etxtPrezimeIme.getText().toString());
        novglumac.setIme(this.etxtIme.getText().toString());
        int god=datumRodjenja.getYear();
        int mes=datumRodjenja.getMonth();
        int dan=datumRodjenja.getDayOfMonth();

        novglumac.setDatumRodjenja(new Date(god,mes,dan));

        MySqlGlumac dbglumac=new MySqlGlumac(this);
        dbglumac.setGlumac(novglumac);

    }
}
