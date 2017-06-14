package borcha.com.testglumcibor.Aktivnosti;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import borcha.com.testglumcibor.R;

/**
 * Created by androiddevelopment on 13.6.17..
 */

public class UnosIspravkaFilma extends AppCompatActivity {

    public static final int TIPOPERACIJE_NOVO=0;//Ukoliko dolazimo sa novim ulazom
    public static final int TIPOPERACIJE_ISPRAVI=1;//Ukoliko dolazimo da ispravimo podatak

    private int tip_ope=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unos_ispravka_glumca);

        tip_ope=getIntent().getExtras().getInt("operacija");





    }
}
