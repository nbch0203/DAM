package com.example.proyectodepruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] elementos = {"Toledo", "Ciudad Real",
                "Cuenca", "Guadalajara", "Albacete"};

        ArrayAdapter<String> adaptador;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner sp = (Spinner) findViewById(R.id.spinner);
        adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, elementos);
        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adaptador);
        sp.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Callback cuando se selecciona un elemento del Spinner
        TextView txtResultado = findViewById(R.id.txtResultado);
        Spinner sp = (Spinner) findViewById(R.id.spinner);

        txtResultado.setText("Se ha seleccionado "+sp.getSelectedItem().toString());

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Callback cuando se no se selecciona un elemento del Spinner
        TextView txtResultado = findViewById(R.id.txtResultado);
        txtResultado.setText("No se ha seleccionado nada");
    }
}