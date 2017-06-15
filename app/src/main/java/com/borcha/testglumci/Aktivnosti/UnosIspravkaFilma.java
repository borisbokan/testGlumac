package com.borcha.testglumci.Aktivnosti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.borcha.testglumci.R;

/**
 * Created by borcha on 15.06.17..
 */

public class UnosIspravkaFilma extends AppCompatActivity {

    public static final int TIPOPERACIJE_NOVO = 0;
    public static final int TIPOPERACIJE_ISPRAVI = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unos_ispravka_filma);
    }
}
