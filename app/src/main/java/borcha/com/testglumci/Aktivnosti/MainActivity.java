package borcha.com.testglumci.Aktivnosti;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.Date;
import java.util.List;

import borcha.com.testglumci.Adapteri.AdapterGlumci;
import borcha.com.testglumci.R;
import borcha.com.testglumci.db.MySqLGlumci.MySqlGlumac;
import borcha.com.testglumci.db.dbmodel.Glumac;


public class MainActivity extends AppCompatActivity {

    private ListView lsGlumci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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

        Glumac startGlumac=new Glumac();
        startGlumac.setPrezime("Damon");
        startGlumac.setIme("Matt");
        startGlumac.setDatumRodjenja(new Date());
        startGlumac.setBiografija("Metju Pejdž Dejmon (engl. Matthew Paige Damon) američki je glumac i scenarista, rođen 8. oktobra 1970. godine u Kembridžu (Masačusets).");

       /* Film film1=new Film();
        film1.setNaziv("Film 1");
        film1.setZanr("Akcioni");//iz arrays

        Film film2=new Film();
        film1.setNaziv("Film 2");
        film1.setZanr("Horor");//iz arrays

        startGlumac.addFilm(film1);
        startGlumac.addFilm(film2);*/

        MySqlGlumac dbglumac=new MySqlGlumac(this);
        dbglumac.setGlumac(startGlumac);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
        lsGlumci=(ListView)findViewById(R.id.lvGlumci);

        lsGlumci.setAdapter(adGlumci);


    }
}
