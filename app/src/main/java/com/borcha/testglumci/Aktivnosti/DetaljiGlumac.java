package com.borcha.testglumci.Aktivnosti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.borcha.testglumci.R;
import com.borcha.testglumci.db.MySqLGlumci.MySqlFilm;
import com.borcha.testglumci.db.dbmodel.Film;
import com.borcha.testglumci.db.dbmodel.Glumac;

import java.util.List;

/**
 * Created by borcha on 15.06.17..
 */

public class DetaljiGlumac extends AppCompatActivity {


    private int idGlumac=0;
    TextView txvPrezimeIme,txvDatumRodjenja,txvBiografija;
    ListView lsvFilmovi;
    Glumac glumac;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalji_glumca);
        idGlumac=getIntent().getIntExtra("id_glumac",0);
        savedInstanceState.putInt("id_glumac",idGlumac);

        txvPrezimeIme=(TextView)findViewById(R.id.txvPrezimeIme_detalji);
        txvDatumRodjenja=(TextView)findViewById(R.id.txvDatumRodjenja_detalji);
        txvBiografija=(TextView)findViewById(R.id.txvBiografija_detalji);
        lsvFilmovi=(ListView)findViewById(R.id.lsvFilmovaGlumca_detalji);

        setujPodatkeGlumcaNaOsnovuID();


    }

    private void setujPodatkeGlumcaNaOsnovuID() {

        if(idGlumac>0){

            txvPrezimeIme.setText(glumac.getPrezime() + ", " + glumac.getIme());
            txvBiografija.setText(glumac.getBiografija());
            txvDatumRodjenja.setText(glumac.getDatumRodjenja().toString());

            ArrayAdapter<Film> adFilmovi=new ArrayAdapter<Film>(this,android.R.layout.simple_list_item_1,getListaFilmova(glumac));
            lsvFilmovi.setAdapter(adFilmovi);
        }
    }

    private List<Film> getListaFilmova(Glumac _glumac) {

        MySqlFilm dbfilmovi=new MySqlFilm(this);
        List<Film> lista=dbfilmovi.getFilmoviPoGlumcu(_glumac);

        return lista;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_detalji_glumac,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.dodaj_glumca:
                Toast.makeText(this,"Kliknuo dodaj  lumaca",Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);


            case R.id.ispravi_glumca:

                Toast.makeText(this,"Kliknuo ispravi glumaca",Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);


            case R.id.obrisi_glumca:

                Toast.makeText(this,"Kliknuo obrisi glumaca",Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);


            default:

                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}