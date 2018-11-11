package com.bios.sistemas.android.preferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        /* Agregar fragmento programaticamente
        1) Comentar el elemento fragment del layout
        2) Descomentar el elemento FrameLayout id 'fragment_sett_conta' del layout
        3) Usar el siguiente codigo para cologar el SettingsFragment en el FrameLayout:

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_sett_conta, new SettingsFragment());
        fragmentTransaction.commit();

         */
    }
}
