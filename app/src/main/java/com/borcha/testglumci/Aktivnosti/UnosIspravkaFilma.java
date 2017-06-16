package com.borcha.testglumci.Aktivnosti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.borcha.testglumci.R;
import com.borcha.testglumci.db.MySqLGlumci.MySqlFilm;
import com.borcha.testglumci.db.MySqLGlumci.MySqlGlumac;
import com.borcha.testglumci.db.dbmodel.Film;
import com.borcha.testglumci.db.dbmodel.Glumac;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by borcha on 15.06.17..
 */

public class UnosIspravkaFilma extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    public static final int TIPOPERACIJE_NOVO = 0;
    public static final int TIPOPERACIJE_ISPRAVI = 1;

    EditText etxtNaziv,etxtGodina;
    Button btnOdustajem,btnSnimi;
    Spinner spZanr;
    Film film;
    Glumac glumac;
    private int idFilm;
    private int tipOpe;
    private String selZanr;
    private int selPosZanra;
    private int idGlumac;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.unos_ispravka_filma);

        tipOpe=getIntent().getIntExtra("tip_ope",0);
        idFilm=getIntent().getIntExtra("id_film",0);
        idGlumac=getIntent().getIntExtra("id_glumac",0);



        if(tipOpe==TIPOPERACIJE_ISPRAVI && idFilm!=0){

            getFilmPoId(idFilm);
            getGlumacPoId(idGlumac);
            pripremiZaIspravku();
        }

        etxtNaziv=(EditText)findViewById(R.id.etxtNaziv_unoispfilm);
        etxtGodina=(EditText)findViewById(R.id.etxtGodina_unoispfilm);

        spZanr=(Spinner)findViewById(R.id.spZanr_unosiprfilm);

        ArrayAdapter adZanr=new ArrayAdapter(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.zanr));
        spZanr.setAdapter(adZanr);

        spZanr.setOnItemSelectedListener(this);


        btnOdustajem=(Button)findViewById(R.id.btnOdustajem_unoispfilm);
        btnSnimi=(Button)findViewById(R.id.btnSnimi_unoispfilm);

        btnSnimi.setOnClickListener(this);
        btnOdustajem.setOnClickListener(this);

    }

    private void pripremiZaIspravku() {

          etxtNaziv.setText(this.film.getNaziv());
          etxtGodina.setText(this.film.getGodina());
          spZanr.setSelection(selktovanaPoziPoSadrzaju(this.film.getZanr()));

    }

    private void getFilmPoId(int _idFilma) {

        MySqlFilm dbfilm=new MySqlFilm(this);
        this.film= dbfilm.getFilmPoId(_idFilma);

    }

    private void getGlumacPoId(int _id){

        MySqlGlumac dbglumac=new MySqlGlumac(this);
        this.glumac=dbglumac.getGlumacPoId(_id);

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnOdustajem_unoispfilm:
                moveTaskToBack(true);
                finish();
                break;


            case R.id.btnSnimi_unoispfilm:

                if(tipOpe==TIPOPERACIJE_NOVO){
                    snimi();
                }else{
                    ispravi();
                }

                break;


            default:

                break;
        }
    }

    private void ispravi() {
        MySqlFilm dbfilm=new MySqlFilm(this);
        dbfilm.setFilm(this.film);
        dbfilm.prepraviFilm();
    }

    private void snimi() {

        Film novFilm=new Film();
        novFilm.setNaziv(etxtNaziv.getText().toString());
        novFilm.setZanr(selZanr);
        novFilm.setGodina(etxtGodina.getText().toString());
        novFilm.setGlumac(this.glumac);
        MySqlFilm dbfilm=new MySqlFilm(this);
        dbfilm.snimiNoviFilm(novFilm);

    }

    private int selktovanaPoziPoSadrzaju(String _sadrzaj){
        String[] zanrovi=getResources().getStringArray(R.array.zanr);
        int pozicija;
        int brojac=0;
        for (String stavka : zanrovi) {
               if(_sadrzaj.contentEquals(stavka)){
                    pozicija=brojac;
               }
               brojac++;
        }

        return  brojac;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selZanr=(String)parent.getItemAtPosition(position);
        selPosZanra=position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
         spZanr.setSelection(0);
    }
}
