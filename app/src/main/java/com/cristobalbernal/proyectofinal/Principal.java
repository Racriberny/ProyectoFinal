package com.cristobalbernal.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Locale;

public class Principal extends AppCompatActivity {
    private Button button;
    private static final String[] lenguaje = {"Selecione idioma!!", "Castellano", "Ingles"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        button = findViewById(R.id.boton);
        Spinner spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(Principal.this, android.R.layout.simple_spinner_item, lenguaje);
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(stringArrayAdapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String posicion = parent.getItemAtPosition(position).toString();
                if (posicion.equals("Ingles")) {
                    setLocal(Principal.this, "en");
                    finish();
                    startActivity(getIntent());
                } else if (posicion.equals("Castellano")) {
                    setLocal(Principal.this, "es");
                    finish();
                    startActivity(getIntent());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Principal.this, MainActivity.class));
            }
        });
    }

    public void setLocal(Activity local, String lenguaje) {
        Locale locale = new Locale(lenguaje);
        locale.setDefault(locale);
        Resources resources = local.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }
}