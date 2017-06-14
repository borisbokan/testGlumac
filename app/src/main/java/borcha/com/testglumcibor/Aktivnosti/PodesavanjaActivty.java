package borcha.com.testglumcibor.Aktivnosti;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;

import borcha.com.testglumcibor.R;


/**
 * Created by androiddevelopment on 16.5.17..
 */

public class PodesavanjaActivty extends PreferenceActivity {


    boolean toastPoruka=true;
    boolean notifikacionaPoruka=true;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.podesavanja);

        //CheckBoxPreference chb=(CheckBoxPreference) findViewById()

    }


    public boolean isToastPoruka() {
        return toastPoruka;
    }

    public void setToastPoruka(boolean toastPoruka) {
        this.toastPoruka = toastPoruka;
    }

    public boolean isNotifikacionaPoruka() {
        return notifikacionaPoruka;
    }

    public void setNotifikacionaPoruka(boolean notifikacionaPoruka) {
        this.notifikacionaPoruka = notifikacionaPoruka;
    }

}
