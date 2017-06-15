package com.borcha.testglumci.Aktivnosti;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import com.borcha.testglumci.Adapteri.AdapterGlumci;
import com.borcha.testglumci.R;
import com.borcha.testglumci.db.MySqLGlumci.MySqlFilm;
import com.borcha.testglumci.db.MySqLGlumci.MySqlGlumac;
import com.borcha.testglumci.db.dbmodel.Film;
import com.borcha.testglumci.db.dbmodel.Glumac;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lsGlumci;
    private Glumac selGlumac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lsGlumci=(ListView)findViewById(R.id.lvGlumci);
        lsGlumci.setOnItemClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inNoviGlumac=new Intent(getBaseContext(),UnosIspravkaGlumac.class);
                inNoviGlumac.putExtra("operacija", UnosIspravkaGlumac.TIPOPERACIJE_NOVO);
                startActivity(inNoviGlumac);

            }
        });




        inicirajPocetneVrednosti();
        ucitajListuGlumaca();
    }

    //Napraviti bazu i pocetne vrednosti
    private void inicirajPocetneVrednosti() {

        MySqlGlumac dbglumac=new MySqlGlumac(this);

        if(dbglumac.getBrojGlumaca()<1){
            Glumac startGlumac=new Glumac();
            startGlumac.setPrezime("Damon");
            startGlumac.setIme("Matt");
            startGlumac.setDatumRodjenja(new Date());
            startGlumac.setBiografija("Metju Pejdž Dejmon (engl. Matthew Paige Damon) američki je glumac i scenarista, rođen 8. oktobra 1970. godine u Kembridžu (Masačusets).");

            Film film1=new Film();
            film1.setNaziv("Film 1");
            film1.setZanr("Akcioni");//iz arrays
            film1.setGlumac(startGlumac);

            Film film2=new Film();
            film1.setNaziv("Film 2");
            film1.setZanr("Horor");//iz arrays
            film2.setGlumac(startGlumac);
            MySqlFilm dbfilm=new MySqlFilm(this);
            dbfilm.snimiNoviFilm(film1);
            dbfilm.snimiNoviFilm(film2);



            dbglumac.setGlumac(startGlumac);

            Toast.makeText(this,"Test" + dbglumac.getBrojGlumaca(),Toast.LENGTH_LONG).show();
        }





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.osnovni_meni, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {

           Intent inPodesa=new Intent(this,PodesavanjaActivty.class);
           startActivity(inPodesa);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void ucitajListuGlumaca(){

        MySqlGlumac dbglumac=new MySqlGlumac(this);
        List<Glumac> glumci=dbglumac.getSviGlumci();
        AdapterGlumci adGlumci=new AdapterGlumci(this,glumci);


        lsGlumci.setAdapter(adGlumci);


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selGlumac=(Glumac)parent.getItemAtPosition(position);

        Intent intDetlji=new Intent(this,DetaljiGlumac.class);
        intDetlji.putExtra("id_glumac",selGlumac.getId());
        startActivity(intDetlji);
    }
}
