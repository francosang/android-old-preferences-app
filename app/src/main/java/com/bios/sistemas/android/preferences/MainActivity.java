package com.bios.sistemas.android.preferences;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    private EditText editTextNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencia a las preferencias
        prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        // EditText para el nombre
        this.editTextNombre = (EditText) findViewById(R.id.editText);

        // Boton para actualizar nombre
        findViewById(R.id.buttonName).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserName();
            }
        });

        // Boton para pedir password
        findViewById(R.id.buttonPass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedirPass();
            }
        });

        // Boton para pedir password
        findViewById(R.id.buttonInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verInfoCompartida();
            }
        });
    }

    private void verInfoCompartida() {
        // obtengo los datos ompartidos
        Set<String> datosCompartidos = prefs.getStringSet("shared_information", null);

        LinearLayout linearLayout = armarVistasParaMostrarDatosCompartidos(datosCompartidos);

        new AlertDialog.Builder(MainActivity.this)
                .setView(linearLayout)
                .setTitle("Datos compartidos")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    private LinearLayout armarVistasParaMostrarDatosCompartidos(@Nullable Set<String> datosCompartidos) {

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        if (datosCompartidos == null || datosCompartidos.size() == 0) {
            TextView textViewDato = new TextView(this);
            textViewDato.setText("No hay datos compartidos");

            linearLayout.addView(textViewDato);
            return linearLayout;
        }

        for (String dato : datosCompartidos) {

            TextView textViewDato = new TextView(this);
            textViewDato.setText(dato);

            linearLayout.addView(textViewDato);
        }

        return linearLayout;
    }

    // Obtengo el nombre del EditText y lo guardo en una preferencia
    private void updateUserName() {
        String nuevoNombre = editTextNombre.getText().toString();

        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("name", nuevoNombre);
        edit.apply();

        setTitle(nuevoNombre);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Obtengo el nombre de las preferencias
        String name = prefs.getString("name", "Nombre no existe...");

        // Setteo el nombre como titulo de la actividad
        setTitle(name);

        // Coloco el nombre en el EditText
        editTextNombre.setText(name);
    }

    // Si la preferencia pide password abro un dialogo para pedir password al usuario
    void pedirPass() {
        String string = getApplicationContext().getString(R.string.setting_ask_pass);

        boolean aBoolean = prefs.getBoolean(string, true);

        if (aBoolean) {

            EditText editText = new EditText(this);

            new AlertDialog.Builder(MainActivity.this)
                    .setView(editText)
                    .setTitle("Contraseña")
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();

        } else {
            Toast.makeText(this, "Password no configurado", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_add:

                Toast.makeText(this, "Nuevo", Toast.LENGTH_SHORT).show();

                break;
            case R.id.menu_main_settings:

                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);

                break;
            case R.id.menu_main_about:

                Toast.makeText(this, "Acerca", Toast.LENGTH_SHORT).show();

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
